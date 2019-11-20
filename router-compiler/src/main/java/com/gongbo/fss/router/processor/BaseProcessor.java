package com.gongbo.fss.router.processor;

import com.gongbo.fss.router.utils.Logger;
import com.gongbo.fss.router.utils.TypeUtils;
import com.google.common.base.Strings;

import java.util.Map;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.gongbo.fss.router.utils.StringUtils.capitalizeString;


/**
 * Base Processor
 */
public abstract class BaseProcessor extends AbstractProcessor {
    Filer mFiler;
    Logger logger;
    Types types;
    Elements elementUtils;
    TypeUtils typeUtils;
    String navigatePrefix, navigateSuffix;
    String buildIntentPrefix, buildIntentSuffix;
    String apiPrefix, apiSuffix;
    String groupPrefix, groupSuffix;
    String defaultGroupName;
    String packageName;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        mFiler = processingEnv.getFiler();
        types = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        typeUtils = new TypeUtils(types, elementUtils);
        logger = new Logger(processingEnv.getMessager());

        Map<String, String> options = processingEnv.getOptions();
        navigatePrefix = getOption(options, "NAVIGATE_PREFIX", "navigateTo", false);
        navigateSuffix = getOption(options, "NAVIGATE_SUFFIX", "", true);
        buildIntentPrefix = getOption(options, "BUILD_INTENT_PREFIX", "buildIntentFor", false);
        buildIntentSuffix = getOption(options, "BUILD_INTENT_SUFFIX", "", true);
        apiPrefix = getOption(options, "API_PREFIX", "I", false);
        apiSuffix = getOption(options, "API_SUFFIX", "RouteApi", true);
        groupPrefix = getOption(options, "GROUP_PREFIX", "", false);
        groupSuffix = getOption(options, "GROUP_SUFFIX", "", true);
        defaultGroupName = getOption(options, "DEFAULT_GROUP", "default", false);
        if (defaultGroupName == null || defaultGroupName.isEmpty()) {
            defaultGroupName = "default";
        }
        packageName = getOption(options, "ROUTE_PACKAGE", "com.gongbo.fss.router", false);
    }

    private String getOption(Map<String, String> options, String key, String defaultValue, boolean capital) {
        String value;
        if (options.containsKey(key)) {
            value = options.get(key);
            value = Strings.nullToEmpty(value);
        } else {
            value = defaultValue;
        }
        if (capital) {
            capitalizeString(value);
        }
        return value;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
