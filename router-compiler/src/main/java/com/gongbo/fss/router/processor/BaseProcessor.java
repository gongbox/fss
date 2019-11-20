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
    protected Filer mFiler;
    protected Logger logger;
    protected Types types;
    protected Elements elementUtils;
    protected TypeUtils typeUtils;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        mFiler = processingEnv.getFiler();
        types = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        typeUtils = new TypeUtils(types, elementUtils);
        logger = new Logger(processingEnv.getMessager());
    }

    protected String getOption(Map<String, String> options, String key, String defaultValue, boolean capital) {
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
