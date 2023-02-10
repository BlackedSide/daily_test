package com.qunar.data.analysis.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jinpeng.fan
 * @date 2023/2/9 4:26 PM
 * description
 */
@Getter
@Setter
@EqualsAndHashCode
public class DubboInfo {

    @ExcelProperty("side")
    private String side;

    @ExcelProperty("service")
    private String service;

    @ExcelProperty("appcode")
    private String appcode;
}
