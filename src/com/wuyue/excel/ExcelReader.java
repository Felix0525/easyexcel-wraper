package com.wuyue.excel;

import com.alibaba.excel.metadata.Sheet;
import lombok.Builder;

import java.io.InputStream;
import java.util.List;

@Builder
public class ExcelReader {

    private InputStream inputStream;

    /**
     * sheet位置，从1开始
     */
    private int sheetNo;

    /**
     * 开始读取行的位置，从0开始
     */
    private int headLineMun;

    /**
     * 读取excel
     *
     * @param tClass 目标类型
     * @param <T>
     * @return
     */
    public <T extends ExcelRow> List<T> read(Class<T> tClass) {
        BaseEventListener<T> listener = new BaseEventListener<>(tClass);
        listener.setHeadLineMun(headLineMun);
        com.alibaba.excel.ExcelReader excelReader = new com.alibaba.excel.ExcelReader(inputStream, null, listener, true);
        excelReader.read(new Sheet(sheetNo, headLineMun, tClass));
        return listener.getRows();
    }

}
