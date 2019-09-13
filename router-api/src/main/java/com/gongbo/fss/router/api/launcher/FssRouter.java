package com.gongbo.fss.router.api.launcher;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import com.gongbo.fss.router.api.util.Consts;
import com.gongbo.fss.router.api.callback.NavigationCallback;
import com.gongbo.fss.router.api.exception.InitException;
import com.gongbo.fss.router.api.facade.Postcard;
import com.gongbo.fss.router.api.template.ILogger;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ARouter facade
 *
 * @author Alex <a href="mailto:zhilong.liu@aliyun.com">Contact me.</a>
 * @version 1.0
 * @since 16/8/16 14:36
 */
public final class FssRouter {
    // Key of raw uri
    public static final String RAW_URI = "NTeRQWvye18AkPd6G";
    public static final String AUTO_INJECT = "wmHzgDrsfdsdfs4241";

    private volatile static FssRouter instance = null;
    private volatile static boolean hasInit = false;
    public static ILogger logger;

    private FssRouter() {
    }

    /**
     * Init, it must be call before used router.
     */
    public static void init(Application application) {
        if (!hasInit) {
            logger = _FssRouter.logger;
            _FssRouter.logger.info(Consts.TAG, "FssRouter init start.");
            hasInit = _FssRouter.init(application);

            if (hasInit) {
                _FssRouter.afterInit();
            }

            _FssRouter.logger.info(Consts.TAG, "FssRouter init over.");
        }
    }

    /**
     * Get instance of router. A
     * All feature U use, will be starts here.
     */
    public static FssRouter getInstance() {
        if (!hasInit) {
            throw new InitException("FssRouter::Init::Invoke init(context) first!");
        } else {
            if (instance == null) {
                synchronized (FssRouter.class) {
                    if (instance == null) {
                        instance = new FssRouter();
                    }
                }
            }
            return instance;
        }
    }

    public static synchronized void openDebug() {
        _FssRouter.openDebug();
    }

    public static boolean debuggable() {
        return _FssRouter.debuggable();
    }

    public static synchronized void openLog() {
        _FssRouter.openLog();
    }

    public static synchronized void printStackTrace() {
        _FssRouter.printStackTrace();
    }

    public static synchronized void setExecutor(ThreadPoolExecutor tpe) {
        _FssRouter.setExecutor(tpe);
    }

    public synchronized void destroy() {
        _FssRouter.destroy();
        hasInit = false;
    }

    /**
     * The interface is not stable enough, use 'FssRouter.inject();';
     */
    @Deprecated
    public static synchronized void enableAutoInject() {
        _FssRouter.enableAutoInject();
    }

    @Deprecated
    public static boolean canAutoInject() {
        return _FssRouter.canAutoInject();
    }

    /**
     * The interface is not stable enough, use 'FssRouter.inject();';
     */
    @Deprecated
    public static void attachBaseContext() {
        _FssRouter.attachBaseContext();
    }

    public static synchronized void monitorMode() {
        _FssRouter.monitorMode();
    }

    public static boolean isMonitorMode() {
        return _FssRouter.isMonitorMode();
    }

    public static void setLogger(ILogger userLogger) {
        _FssRouter.setLogger(userLogger);
    }

    /**
     * Inject params and services.
     */
    public void inject(Object thiz) {
        _FssRouter.inject(thiz);
    }

    /**
     * Build the roadmap, draw a postcard.
     *
     * @param path Where you go.
     */
    public Postcard build(String path) {
        return _FssRouter.getInstance().build(path);
    }

    /**
     * Build the roadmap, draw a postcard.
     *
     * @param path  Where you go.
     * @param group The group of path.
     */
    @Deprecated
    public Postcard build(String path, String group) {
        return _FssRouter.getInstance().build(path, group);
    }

    /**
     * Build the roadmap, draw a postcard.
     *
     * @param url the path
     */
    public Postcard build(Uri url) {
        return _FssRouter.getInstance().build(url);
    }

    /**
     * Launch the navigation by type
     *
     * @param service interface of service
     * @param <T>     return type
     * @return instance of service
     */
    public <T> T navigation(Class<? extends T> service) {
        return _FssRouter.getInstance().navigation(service);
    }

    /**
     * Launch the navigation.
     *
     * @param mContext    .
     * @param postcard    .
     * @param requestCode Set for startActivityForResult
     * @param callback    cb
     */
    public Object navigation(Context mContext, Postcard postcard, int requestCode, NavigationCallback callback) {
        return _FssRouter.getInstance().navigation(mContext, postcard, requestCode, callback);
    }
}