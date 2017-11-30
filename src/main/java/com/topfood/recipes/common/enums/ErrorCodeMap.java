package com.topfood.recipes.common.enums;

import java.util.HashMap;
import java.util.Map;

import static com.topfood.recipes.common.enums.ErrorCodes.OK;
import static com.topfood.recipes.common.enums.ErrorCodes.REG_USER_ALREADY_EXISTS;
import static com.topfood.recipes.common.enums.ErrorCodes.TOO_OFTEN_LIKES;

public class ErrorCodeMap {
    public static Map<ErrorCodes, String> errors;
    static {
        errors = new HashMap<>();
    errors.put(REG_USER_ALREADY_EXISTS, "User already exists");
    errors.put(OK, "");
    errors.put(TOO_OFTEN_LIKES, "User can't do more than 1 like in 2 min");
    }
}
