package com.dinner.recipes.infra.exception;

import java.util.Map;

public class InvalidRecipeTitleException extends GeneralException {

    public InvalidRecipeTitleException(String message) {
        super(message);
    }

    public InvalidRecipeTitleException(Throwable cause) {
        super(cause);
    }

    public InvalidRecipeTitleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRecipeTitleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidRecipeTitleException(Map<String, String> errorMsgs, String message, Throwable cause) {
        super(errorMsgs, message, cause);
    }

    public InvalidRecipeTitleException(Map<String, String> errorMsgs, String message) {
        super(errorMsgs, message);
    }
}