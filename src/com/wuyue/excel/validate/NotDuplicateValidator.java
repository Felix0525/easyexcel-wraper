package com.wuyue.excel.validate;

import com.wuyue.excel.ExcelRow;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NotDuplicateValidator {

    private Map<String, Set<String>> fieldMap = new HashMap<>();

    public <T extends ExcelRow> boolean validate(Field field, String name, String value, T r) {
        if (field.isAnnotationPresent(NotDuplicate.class)) {
            if (fieldMap.containsKey(name)) {
                if (fieldMap.get(name).contains(value)) {
                    NotDuplicate notDuplicate = field.getAnnotation(NotDuplicate.class);
                    r.setValidateCode(notDuplicate.code());
                    r.setValidateMessage(notDuplicate.message());
                    return false;
                } else {
                    fieldMap.get(name).add(value);
                }
            } else {
                Set<String> values = new HashSet<>();
                values.add(value);
                fieldMap.put(name, values);
            }
        }
        return true;
    }

}
