package com.dinner.recipes.infra.exception;

import java.util.Map;

public class RecipeNotFoundException extends GeneralException {

    public RecipeNotFoundException(String message) {
        super(message);
    }

    public RecipeNotFoundException(Throwable cause) {
        super(cause);
    }

    public RecipeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecipeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RecipeNotFoundException(Map<String, String> errorMsgs, String message, Throwable cause) {
        super(errorMsgs, message, cause);
    }

    public RecipeNotFoundException(Map<String, String> errorMsgs, String message) {
        super(errorMsgs, message);
    }
}