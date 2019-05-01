package com.wuyue.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.groups.Default;
import java.lang.reflect.Field;
import java.util.*;

public class BaseEventListener<T extends ExcelRow> extends AnalysisEventListener<T> {

    @Getter
    private List<T> rows;

    private Class<T> tClass;

    private Field[] fields;

    private Map<String, Set<String>> fieldMap;

    public BaseEventListener(Class<T> tClass) {
        this.tClass = tClass;
        fields = tClass.getDeclaredFields();
        rows = new ArrayList<>();
        fieldMap = new HashMap<>();
    }

    @Override
    public void invoke(T r, AnalysisContext analysisContext) {
        for (Field field : fields) {
            String name = field.getName();
            String val = null;
            try {
                field.setAccessible(true);
                val = (String) field.get(r);
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (field.isAnnotationPresent(NotDuplicate.class)) {
                if (fieldMap.containsKey(name)) {
                    if (fieldMap.get(name).contains(val)) {
                        NotDuplicate notDuplicate = field.getAnnotation(NotDuplicate.class);
                        r.setValidteCode(notDuplicate.code());
                        r.setValidateMessage(notDuplicate.message());
                        break;
                    } else {
                        fieldMap.get(name).add(val);
                    }
                } else {
                    Set<String> vals = new HashSet<>();
                    vals.add(val);
                    fieldMap.put(name, vals);
                }
            }
        }
        if (r.getValidteCode() != 0) {
            Set<ConstraintViolation<T>> validateSet = ExcelValidator.getValidator().validate(r, Default.class);
            if (validateSet != null && !validateSet.isEmpty()) {
                ConstraintViolation<T> constraint = validateSet.stream().findAny().orElse(null);
                r.setValidteCode(-1);
                r.setValidateMessage(constraint.getMessage());
            }
        }
        r.setRowNum(analysisContext.getCurrentRowNum());
        rows.add(r);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

}
