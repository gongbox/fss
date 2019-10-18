package com.gongbo.fss.router.processor;

import com.gongbo.fss.router.utils.Logger;
import com.gongbo.fss.router.utils.TypeUtils;

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
 *
 * @author zhilong [Contact me.](mailto:zhilong.lzl@alibaba-inc.com)
 * @version 1.0
 * @since 2019-03-01 12:31
 */
public abstract class BaseProcessor extends AbstractProcessor {
    Filer mFiler;
    Logger logger;
    Types types;
    Elements elementUtils;
    TypeUtils typeUtils;
    String prefix, suffix;
    String groupSuffix;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        mFiler = processingEnv.getFiler();
        types = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        typeUtils = new TypeUtils(types, elementUtils);
        logger = new Logger(processingEnv.getMessager());

        Map<String, String> options = processingEnv.getOptions();
        if (options.containsKey("prefix")) {
            prefix = options.get("prefix");
            if (prefix == null) {
                prefix = "";
            }
        } else {
            prefix = "navigateTo";
        }

        if (options.containsKey("suffix")) {
            suffix = options.get("suffix");
            if (suffix == null) {
                suffix = "";
            }
            suffix = capitalizeString(suffix);
        } else {
            suffix = "";
        }
        if (options.containsKey("groupSuffix")) {
            groupSuffix = options.get("groupSuffix");
            if (groupSuffix == null) {
                groupSuffix = "";
            }
            groupSuffix = capitalizeString(groupSuffix);
        } else {
            groupSuffix = "RouteApi";
        }


    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
