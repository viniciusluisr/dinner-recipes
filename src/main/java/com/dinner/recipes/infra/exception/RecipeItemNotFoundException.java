package com.dinner.recipes.infra.exception;

import java.util.Map;

public class RecipeItemNotFoundException extends GeneralException {

    public RecipeItemNotFoundException(String message) {
        super(message);
    }

    public RecipeItemNotFoundException(Throwable cause) {
        super(cause);
    }

    public RecipeItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecipeItemNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RecipeItemNotFoundException(Map<String, String> errorMsgs, String message, Throwable cause) {
        super(errorMsgs, message, cause);
    }

    public RecipeItemNotFoundException(Map<String, String> errorMsgs, String message) {
        super(errorMsgs, message);
    }
}