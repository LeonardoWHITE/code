package com.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 数据校验
 * */
public class BindingResultHelper {
    public static Map<String, String> toErrorMap(BindingResult bindingResult) {
        //判断数据是否校验成功
        //校验失败则将？？？存入map映中
        if (bindingResult.hasErrors()) {
            List<FieldError> errorList = bindingResult.getFieldErrors();
            Map<String, String> map = new HashMap<>();
            for (FieldError fieldError : errorList) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return map;
        }
        //校验成功则返回一个空map
        return Collections.emptyMap();
    }
}
