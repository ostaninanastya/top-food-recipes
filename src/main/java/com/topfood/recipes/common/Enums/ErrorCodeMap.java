package com.topfood.recipes.common.Enums;

import java.util.Map;

import static com.topfood.recipes.common.Enums.ErrorCodes.OK;
import static com.topfood.recipes.common.Enums.ErrorCodes.REG_USER_ALREADY_EXISTS;

public class ErrorCodeMap {
    public static Map<ErrorCodes, String> errors;
    static {
    errors.put(REG_USER_ALREADY_EXISTS, "User already exists");
    errors.put(OK, "");
    }
}
