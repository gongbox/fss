package com.gongbo.fss.router.processor;

import com.gongbo.fss.router.annotation.DefaultExtra;
import com.gongbo.fss.router.annotation.Extra;
import com.gongbo.fss.router.annotation.Route;
import com.gongbo.fss.router.annotation.RouteActivity;
import com.gongbo.fss.router.annotation.RouteApi;
import com.gongbo.fss.router.annotation.RouteExtra;
import com.gongbo.fss.router.annotation.RouteService;
import com.gongbo.fss.router.annotation.Routes;
import com.gongbo.fss.router.entity.RouteInfo;
import com.gongbo.fss.router.utils.FieldUtils;
import com.gongbo.fss.router.utils.TypeUtils;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import static com.gongbo.fss.router.utils.StringUtils.capitalizeString;
import static com.gongbo.fss.router.utils.StringUtils.formatApiFieldName;
import static com.gongbo.fss.router.utils.StringUtils.formatToStaticField;
import static com.gongbo.fss.router.utils.StringUtils.getFieldValue;
import static com.gongbo.fss.router.utils.StringUtils.joinString;

/**
 * A processor used for find route.
 */
@AutoService(Processor.class)
public class RouteProcessor extends BaseProcessor {

    private static final String APIS_PACKAGE = "com.gongbo.fss.router.apis";
    private static final String ROUTE_API_PACKAGE = "com.gongbo.fss.router";
    private static final String FSS_ROUTE_API_NAME = "FssRouteApi";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        logger.info(">>> RouteProcessor init. <<<");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<String>();
        //添加需要支持的注解
        annotationTypes.add(Route.class.getCanonicalName());
        annotationTypes.add(Routes.class.getCanonicalName());
        annotationTypes.add(RouteApi.class.getCanonicalName());
        return annotationTypes;
    }

    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (CollectionUtils.isNotEmpty(annotations)) {
            Map<String, List<RouteInfo>> routeInfoMap = new HashMap<String, List<RouteInfo>>();
            Set<String> groups = new HashSet<String>();

            Set<? extends Element> routeElements = roundEnv.getElementsAnnotatedWith(Route.class);
            Set<? extends Element> routesElements = roundEnv.getElementsAnnotatedWith(Routes.class);

            Set<Element> elements = new HashSet<Element>();
            elements.addAll(routeElements);
            elements.addAll(routesElements);

            try {
                logger.info(">>> Found routes, start... <<<");

                for (Element element : elements) {
                    if (element.getKind() != ElementKind.CLASS) { //判断是否为类，如果不是class，抛出异常
                        continue;
                    }

                    //获取到这个类
                    TypeElement typeElement = (TypeElement) element;

                    Route route = element.getAnnotation(Route.class);
                    Routes routes = element.getAnnotation(Routes.class);

                    if (route != null) {
                        getRouteInfos(routeInfoMap, route.group()).add(new RouteInfo(typeElement, route));
                    }
                    if (routes != null && routes.value().length > 0) {
                        for (Route routeItem : routes.value()) {
                            getRouteInfos(routeInfoMap, routeItem.group()).add(new RouteInfo(typeElement, routeItem));
                        }
                    }
                }

                groups = routeInfoMap.keySet();

                this.parseRoutes(routeInfoMap);
            } catch (Exception e) {
                logger.error(e);
            }

            Set<? extends Element> routeApiElements = roundEnv.getElementsAnnotatedWith(RouteApi.class);
            try {
                logger.info(">>> Found routeApis, start... <<<");
                Set<TypeElement> typeElementList = new HashSet<TypeElement>();
                if (routeApiElements != null) {
                    //获取被注解的元素
                    for (Element element : routeApiElements) {
                        if (element.getKind() != ElementKind.INTERFACE) { //判断是否为类，如果是class，抛出异常
                            continue;
                        }

                        //获取到这个类
                        TypeElement typeElement = (TypeElement) element;
                        typeElementList.add(typeElement);
                    }
                }
                logger.info(">>> parseRouteApis <<<");
                this.parseRouteApis(typeElementList, groups);
            } catch (Exception e) {
                logger.error(e);
            }
            return true;
        }

        return false;
    }

    private void parseRoutes(Map<String, List<RouteInfo>> routeInfoMap) throws IOException {
        if (MapUtils.isNotEmpty(routeInfoMap)) {
            // prepare the type an so on.

            logger.info(">>> Found routes, size is " + routeInfoMap.size() + " <<<");

            for (Map.Entry<String, List<RouteInfo>> entry : routeInfoMap.entrySet()) {
                String group = entry.getKey();
                String apiFileName = group.isEmpty() ? "IDefault" : "I" + capitalizeString(group);
                apiFileName = groupPrefix + apiFileName + groupSuffix;
                List<RouteInfo> routeInfos = entry.getValue();

                parseRouteApi(apiFileName, routeInfos);
            }

        }

    }

    private void parseRouteApi(String apiFileName, List<RouteInfo> routeInfos) throws IOException {
        List<FieldSpec> fieldSpecs = new ArrayList<FieldSpec>();
        List<MethodSpec> methodSpecs = new ArrayList<MethodSpec>();
        for (RouteInfo routeInfo : routeInfos) {
            for (Route route : routeInfo.routes) {
                RouteExtra[] routeExtras = route.routeExtras();
                DefaultExtra[] defaultExtras = route.defaultExtras();

                int type = -1;
                TypeMirror activityType = elementUtils.getTypeElement("android.app.Activity").asType();
                TypeMirror serviceType = elementUtils.getTypeElement("android.app.Service").asType();

                TypeMirror destinationType;
                if (!route.destination().isEmpty()) {
                    destinationType = elementUtils.getTypeElement(route.destination()).asType();
                } else {
                    destinationType = routeInfo.typeElement.asType();
                }

                if (types.isSubtype(destinationType, activityType)) {
                    type = 0;
                } else if (types.isSubtype(destinationType, serviceType)) {
                    type = 1;
                }

                List<AnnotationSpec> methodAnnotationSpecs = new ArrayList<AnnotationSpec>();
                if (type == 0) {
                    AnnotationSpec.Builder builder = AnnotationSpec.builder(RouteActivity.class);

                    if (!route.destination().isEmpty()) {
                        builder.addMember("value", "$T.class", TypeUtils.getType(route.destination()));
                    } else if (!route.action().isEmpty()) {
                        builder.addMember("action", "\"" + route.action() + "\"");
                    } else {
                        builder.addMember("value", "$T.class", routeInfo.typeElement);
                    }

                    if (route.requestCode() > 0) {
                        String name = "REQUEST_CODE_TO_" + formatToStaticField(routeInfo.typeElement.getSimpleName().toString());

                        name = FieldUtils.getFieldName(fieldSpecs, name);

                        builder.addMember("requestCode", name);

                        FieldSpec fieldSpec = FieldSpec.builder(TypeName.INT, name,
                                Modifier.FINAL, Modifier.PUBLIC, Modifier.STATIC)
                                .initializer(route.requestCode() + "")
                                .build();
                        fieldSpecs.add(fieldSpec);
                    }
                    if (route.category().length > 0) {

                        builder.addMember("category", joinString("{", "}", route.category(), ","));
                    }
                    if (route.flags().length > 0) {

                        builder.addMember("flags", joinString("{", "}", route.flags(), ","));
                    }

                    if (route.enterAnim() > 0) {
                        builder.addMember("enterAnim", "" + route.enterAnim());
                    }
                    if (route.exitAnim() > 0) {
                        builder.addMember("exitAnim", "" + route.exitAnim());
                    }

                    AnnotationSpec methodAnnotationSpec = builder.build();
                    methodAnnotationSpecs.add(methodAnnotationSpec);
                } else if (type == 1) {
                    AnnotationSpec.Builder builder = AnnotationSpec.builder(RouteService.class);
                    if (!route.destination().isEmpty()) {
                        builder.addMember("value", "$T.class", TypeUtils.getType(route.destination()));
                    } else if (!route.action().isEmpty()) {
                        builder.addMember("action", "\"" + route.action() + "\"");
                    } else {
                        builder.addMember("value", "$T.class", routeInfo.typeElement);
                    }

                    AnnotationSpec methodAnnotationSpec = builder.build();
                    methodAnnotationSpecs.add(methodAnnotationSpec);
                }


                for (DefaultExtra defaultExtra : defaultExtras) {
                    AnnotationSpec.Builder builder = AnnotationSpec.builder(DefaultExtra.class);
                    builder.addMember("name", "\"" + defaultExtra.name() + "\"");

                    if (!defaultExtra.defaultValue().isEmpty()) {
                        builder.addMember("defaultValue", "\"" + defaultExtra.defaultValue() + "\"");
                    }

                    String extraString = defaultExtra.toString();
                    String paramType = extraString.substring(extraString.indexOf("type=") + 5, extraString.indexOf(","));
                    TypeName typeName = TypeUtils.getType(paramType);

                    builder.addMember("type", "$T.class", typeName);

                    methodAnnotationSpecs.add(builder.build());
                }

                List<ParameterSpec> parameterSpecs = new ArrayList<ParameterSpec>();
                parameterSpecs.add(ParameterSpec.builder(ClassName.bestGuess("android.content.Context"), "context").build());

                StringBuilder paramDesc = new StringBuilder();

                for (RouteExtra routeExtra : routeExtras) {
                    String paramType = getFieldValue(routeExtra.toString(), "type");

                    AnnotationSpec annotationSpec = AnnotationSpec.builder(Extra.class)
                            .addMember("value", "\"" + routeExtra.name() + "\"")
                            .build();


                    TypeName typeName = TypeUtils.getType(paramType);
                    String paramName = routeExtra.paramName().isEmpty() ? routeExtra.name() : routeExtra.paramName();
                    ParameterSpec parameterSpec = ParameterSpec.builder(typeName, paramName)
                            .addAnnotation(annotationSpec)
                            .build();
                    parameterSpecs.add(parameterSpec);

                    if (!routeExtra.desc().isEmpty()) {
                        paramDesc.append("@").append(paramName).append(" ").append(routeExtra.desc());
                    }
                }

                if (route.withResultCallBack()) {
                    ClassName className = ClassName.get("com.gongbo.fss.router.api.callback", "OnActivityResult");

                    ParameterSpec parameterSpec = ParameterSpec.builder(className, "onActivityResult")
                            .build();
                    parameterSpecs.add(parameterSpec);
                }

                String name;
                TypeName returnType;
                if (!route.navigation()) {
                    returnType = ClassName.get("android.content", "Intent");
                    name = route.name().isEmpty() ? "buildIntentFor" + routeInfo.typeElement.getSimpleName() : route.name();
                } else {
                    returnType = TypeName.VOID;
                    name = route.name().isEmpty() ? prefix + routeInfo.typeElement.getSimpleName() + suffix : route.name();
                }

                String paramDoc = paramDesc.toString();
                if (!paramDoc.isEmpty()) {
                    paramDoc = "\n" + paramDoc + "\n";
                }

                MethodSpec methodSpec = MethodSpec.methodBuilder(name)
                        .addAnnotations(methodAnnotationSpecs)
                        .addJavadoc(route.desc())
                        .addJavadoc(paramDoc)
                        .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                        .addParameters(parameterSpecs)
                        .returns(returnType)
                        .build();

                methodSpecs.add(methodSpec);
            }
        }

        TypeSpec typeSpec = TypeSpec
                .interfaceBuilder(apiFileName)
                .addModifiers(Modifier.PUBLIC)
                .addMethods(methodSpecs)
                .addFields(fieldSpecs)
                .build();

        JavaFile.builder(APIS_PACKAGE, typeSpec).

                build().

                writeTo(mFiler);

    }

    private void parseRouteApis(Set<TypeElement> elements, Set<String> groups) throws IOException {
//        if (CollectionUtils.isNotEmpty(elements)) {
        // prepare the type an so on.

        logger.info(">>> Found routeApis, size is " + elements.size() + " <<<");
        List<FieldSpec> fieldSpecs = new ArrayList<FieldSpec>();
        List<MethodSpec> methodSpecs = new ArrayList<MethodSpec>();

        ClassName fssRouteManagerClassName = ClassName.bestGuess("com.gongbo.fss.router.api.manager.RouteManager");

        //遍历自定义Api接口，生成对应的字段及get方法
        for (TypeElement element : elements) {

            //获取名字
            RouteApi routeApi = element.getAnnotation(RouteApi.class);

            String apiName;
            if (!routeApi.name().isEmpty()) {
                apiName = capitalizeString(routeApi.name());
            } else {
                apiName = formatApiFieldName(element.getSimpleName().toString());
            }

//            logger.info(">>>>>>>>>>>>>>>>>>>>>>>：" + apiName);
            String apiFieldName = formatToStaticField(apiName);

            //FssRouteApi中添加对应的字段
            FieldSpec fieldSpec = FieldSpec.builder(TypeName.get(element.asType()), apiFieldName, Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .initializer("$T.createRouteApi($T.class)", fssRouteManagerClassName, TypeName.get(element.asType()))
                    .build();
            fieldSpecs.add(fieldSpec);

//            logger.info(">>>FssRouteApi中添加对应的字段：" + apiFieldName);

            //FssRouteApi中添加对应的get方法
            MethodSpec methodSpec = MethodSpec.methodBuilder("get" + apiName)
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(fieldSpec.type)
                    .addStatement("return " + fieldSpec.name)
                    .build();
            methodSpecs.add(methodSpec);

//            logger.info(">>>FssRouteApi中添加对应的get方法：" + "get" + apiName);
        }

        //遍历所有group
        for (String group : groups) {
            //group名字转换
            String groupApiName = group.isEmpty() ? "Default" : capitalizeString(group);

            groupApiName = groupPrefix + groupApiName + groupSuffix;

            String groupApiFieldName = formatToStaticField(groupApiName);

//            logger.info(">>>>>>>>>>>>>>>>>>>>>>>：" + groupApiName);

            ClassName groupRouteApiImpl = ClassName.get("com.gongbo.fss.router.apis", "I" + groupApiName);

            //FssRouteApi中添加对应的字段
            FieldSpec fieldSpec = FieldSpec.builder(groupRouteApiImpl, groupApiFieldName, Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .initializer("$T.createRouteApi($T.class)", fssRouteManagerClassName, groupRouteApiImpl)
                    .build();
            fieldSpecs.add(fieldSpec);

//            logger.info(">>>FssRouteApi中添加对应的字段：" + groupApiFieldName);

            //FssRouteApi中添加对应的get方法
            MethodSpec methodSpec = MethodSpec.methodBuilder("get" + groupApiName)
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(fieldSpec.type)
                    .addStatement("return " + fieldSpec.name)
                    .build();
            methodSpecs.add(methodSpec);

//            logger.info(">>>FssRouteApi中添加对应的get方法：" + "get" + groupApiName);
        }

        //添加静态代码块初始化字段
//            CodeBlock.Builder builder = CodeBlock.builder();
//            for (FieldSpec fieldSpec : fieldSpecs) {
//                builder.addStatement(fieldSpec.value + " = $T.createRouteApi($T.class)", fssRouteManagerClassName, fieldSpec.type);
//            }

        //构造FssRouteApi
        TypeSpec typeSpec = TypeSpec
                .classBuilder(FSS_ROUTE_API_NAME)
                .addModifiers(Modifier.PUBLIC)
                .addFields(fieldSpecs)
                .addMethods(methodSpecs)
//                    .addStaticBlock(builder.build())
                .build();

        JavaFile.builder(ROUTE_API_PACKAGE, typeSpec).build().writeTo(mFiler);
//        }
    }

    public static List<RouteInfo> getRouteInfos(Map<String, List<RouteInfo>> map, String group) {
        if (map.containsKey(group)) {
            return map.get(group);
        }

        List<RouteInfo> routeInfos = new ArrayList<RouteInfo>();
        map.put(group, routeInfos);
        return routeInfos;
    }

}