package com.wuyue.excel.test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.wuyue.excel.ExcelRow;
import com.wuyue.excel.validate.NotDuplicate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@ToString
public class MyRow extends ExcelRow {

    @ExcelProperty(index = 0)
    @NotDuplicate
    @NotBlank(message = "名称不能为空")
    private String name;

    @ExcelProperty(index = 1)
    @Email
    private String email;

}
