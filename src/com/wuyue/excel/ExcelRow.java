package com.wuyue.excel;

import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExcelRow extends BaseRowModel {

    private int rowNum;

    private int validteCode;

    private String validateMessage;

}
