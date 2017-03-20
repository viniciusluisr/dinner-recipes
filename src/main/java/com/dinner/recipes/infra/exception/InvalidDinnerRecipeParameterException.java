package com.dinner.recipes.infra.exception;

import java.util.Map;

public class InvalidDinnerRecipeParameterException extends GeneralException {

    public InvalidDinnerRecipeParameterException(String message) {
        super(message);
    }

    public InvalidDinnerRecipeParameterException(Throwable cause) {
        super(cause);
    }

    public InvalidDinnerRecipeParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDinnerRecipeParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidDinnerRecipeParameterException(Map<String, String> errorMsgs, String message, Throwable cause) {
        super(errorMsgs, message, cause);
    }

    public InvalidDinnerRecipeParameterException(Map<String, String> errorMsgs, String message) {
        super(errorMsgs, message);
    }
}