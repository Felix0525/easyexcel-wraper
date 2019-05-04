package com.wuyue.excel;

import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 基础“行”对象
 */
@ToString
public class ExcelRow extends BaseRowModel {

    public static final int SUCCESS_CODE = 0;

    public static final int FAILED_CODE = 2;

    /**
     * 所属行数，从0开始
     */
    @Getter
    @Setter
    private int rowNum = SUCCESS_CODE;

    /**
     * 校验码，当承载“行”的对象有设置注解，且校验不通过时，会将结果放置于此字段
     */
    @Getter
    @Setter
    private int validateCode;

    /**
     * 校验消息，业务尽量使用校验码做判断
     */
    @Getter
    @Setter
    private String validateMessage;

}
