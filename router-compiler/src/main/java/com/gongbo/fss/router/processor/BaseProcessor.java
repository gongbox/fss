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
 */
public abstract class BaseProcessor extends AbstractProcessor {
    Filer mFiler;
    Logger logger;
    Types types;
    Elements elementUtils;
    TypeUtils typeUtils;
    String prefix, suffix;
    String groupPrefix, groupSuffix;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        mFiler = processingEnv.getFiler();
        types = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        typeUtils = new TypeUtils(types, elementUtils);
        logger = new Logger(processingEnv.getMessager());

        Map<String, String> options = processingEnv.getOptions();
        prefix = getOption(options, "prefix", "navigateTo", false);
        suffix = getOption(options, "suffix", "", true);
        groupPrefix = getOption(options, "groupPrefix", "", false);
        groupSuffix = getOption(options, "groupSuffix", "RouteApi", true);
    }

    private String getOption(Map<String, String> options, String key, String defaultValue, boolean capital) {
        String value;
        if (options.containsKey(key)) {
            value = options.get(key);
            if (value == null) {
                value = "";
            }
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
