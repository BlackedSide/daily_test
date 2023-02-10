package com.qunar.data.analysis.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 7:22 PM
 * description
 */
@Getter
@Setter
@EqualsAndHashCode
public class AppcodeDomainInfo {

    @ExcelProperty("appcode")
    private String appcode;

    @ExcelProperty("domain")
    private String domain;
}
