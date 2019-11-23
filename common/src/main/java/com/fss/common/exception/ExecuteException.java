package com.fss.common.exception;

public class ExecuteException extends RuntimeException {

    public ExecuteException(Throwable cause) {
        if (cause != null) {
            cause.printStackTrace();
        }
    }
}
