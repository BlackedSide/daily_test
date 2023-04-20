package com.qunar.data.analysis.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jinpeng.fan
 * @date 2023/4/20 11:30 AM
 * description
 */
@Getter
@Setter
@EqualsAndHashCode
public class AppDomainConsumerInfo {

    @ExcelProperty("appcode")
    private String appcode;

    @ExcelProperty("domain")
    private String domain;

    @ExcelProperty("consumers")
    private String consumers;
}
