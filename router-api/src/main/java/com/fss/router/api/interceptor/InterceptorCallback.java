package com.fss.router.api.interceptor;

import com.fss.router.api.model.RouteMeta;

public interface InterceptorCallback {
    /**
     * Continue process
     *
     */
    void onContinue(RouteMeta routeMeta);

    /**
     * Interrupt process, pipeline will be destroy when this method called.
     *
     * @param exception Reson of interrupt.
     */
    void onInterrupt(Throwable exception);
}
