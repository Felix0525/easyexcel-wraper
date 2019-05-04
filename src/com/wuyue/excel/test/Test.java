package com.wuyue.excel.test;

import com.wuyue.excel.ExcelReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\1.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        List<MyRow> rows = ExcelReader.builder()
                .inputStream(fileInputStream)
                .sheetNo(1)
                .headLineMun(1)
                .build()
                .read(MyRow.class);
        System.out.println(rows);
        rows.forEach(row -> {
            // 行号，如果要提示实际excel行号，应该要加上headLineMun的值
            System.out.print("Row number:" + row.getRowNum());
            // 校验结果代码(0为正常)
            System.out.print(", validate code:" + row.getValidateCode());
            // 校验结果内容
            System.out.println(", message:" + row.getValidateMessage());
        });
    }

}
