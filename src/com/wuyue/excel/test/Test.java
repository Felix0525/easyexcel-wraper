package com.wuyue.excel.test;

import com.wuyue.excel.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\1.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        List<MyRow> rows = ExcelUtil.getInstance().read(fileInputStream, MyRow.class);
        System.out.println(rows);
    }

}
