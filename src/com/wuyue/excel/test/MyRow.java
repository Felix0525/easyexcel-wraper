package com.wuyue.excel.test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.wuyue.excel.ExcelRow;
import com.wuyue.excel.NotDuplicate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

@Getter
@Setter
@ToString
public class MyRow extends ExcelRow {

    @ExcelProperty(index = 0)
    @NotDuplicate
    private String name;

    @ExcelProperty(index = 1)
    @Email
    private String email;

}
