package com.topfood.recipes.common.enums;

import java.util.HashMap;
import java.util.Map;

import static com.topfood.recipes.common.enums.ErrorCodes.OK;
import static com.topfood.recipes.common.enums.ErrorCodes.REG_USER_ALREADY_EXISTS;
import static com.topfood.recipes.common.enums.ErrorCodes.TOO_OFTEN_LIKES;

public class ErrorCodeMap {
    public static Map<ErrorCodes, String> errors = new HashMap<>();
    static {
        errors = new HashMap<>();
    errors.put(REG_USER_ALREADY_EXISTS, "Пользователь с таким именем уже существует");
    errors.put(OK, "");
    errors.put(TOO_OFTEN_LIKES, "Вы не можете оценивать один рецепт чаще 1 раза в 2 минуты");
    }
}
