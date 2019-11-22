package com.fss.router.utils;

/**
 * Some consts used in processors
 *
 * @author Alex <a href="mailto:zhilong.liu@aliyun.com">Contact me.</a>
 * @version 1.0
 * @since 16/8/24 20:18
 */
public class Consts {
    // Generate
    public static final String SEPARATOR = "$$";
    public static final String PROJECT = "FssRouter";
    public static final String TAG = PROJECT + "::";
    public static final String PACKAGE_OF_GENERATE_FILE = "com.alibaba.android.arouter.routes";
    public static final String PACKAGE_OF_GENERATE_DOCS = "com.alibaba.android.arouter.docs";

    // System interface
    public static final String ACTIVITY = "android.app.Activity";
    public static final String FRAGMENT = "android.app.Fragment";
    public static final String FRAGMENT_V4 = "android.support.v4.app.Fragment";
    public static final String SERVICE = "android.app.Service";
    public static final String PARCELABLE = "android.os.Parcelable";

    // Java type
    private static final String LANG = "java.lang";
    public static final String BYTE = LANG + ".Byte";
    public static final String SHORT = LANG + ".Short";
    public static final String INTEGER = LANG + ".Integer";
    public static final String LONG = LANG + ".Long";
    public static final String FLOAT = LANG + ".Float";
    public static final String DOUBEL = LANG + ".Double";
    public static final String BOOLEAN = LANG + ".Boolean";
    public static final String CHAR = LANG + ".Character";
    public static final String STRING = LANG + ".String";
    public static final String SERIALIZABLE = "java.io.Serializable";

    // Custom interface
    private static final String FACADE_PACKAGE = "com.fss.router";

    // Log
    static final String PREFIX_OF_LOGGER = PROJECT + "::Compiler ";

}
