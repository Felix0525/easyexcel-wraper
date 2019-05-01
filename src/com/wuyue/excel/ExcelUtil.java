package com.wuyue.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;

import java.io.InputStream;
import java.util.List;

public class ExcelUtil {

    private static ExcelUtil ourInstance = new ExcelUtil();

    public static ExcelUtil getInstance() {
        return ourInstance;
    }

    private ExcelUtil() {
    }

    public <T extends ExcelRow> List<T> read(InputStream inputStream, Class<T> tClass) {
        BaseEventListener<T> listener = new BaseEventListener<>(tClass);
        ExcelReader excelReader = new ExcelReader(inputStream, null, listener, true);
        excelReader.read(new Sheet(1, 1, tClass));
        return listener.getRows();
    }

}
