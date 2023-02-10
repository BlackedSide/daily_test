package com.qunar.data.analysis.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 11:59 AM
 * description
 */
@Getter
@Setter
@EqualsAndHashCode
public class AppReleaseInfo {

    @ExcelProperty("app_code")
    private String appcode;

    @ExcelProperty("app_tree")
    private String appTree;

    @ExcelProperty("level")
    private String level;

    @ExcelProperty("2022-1")
    private Integer Jan22;

    @ExcelProperty("2022-2")
    private Integer Feb22;

    @ExcelProperty("2022-3")
    private Integer Mar22;

    @ExcelProperty("2022-4")
    private Integer Apr22;

    @ExcelProperty("2022-5")
    private Integer May22;

    @ExcelProperty("2022-6")
    private Integer Jun22;

    @ExcelProperty("2022-7")
    private Integer July22;

    @ExcelProperty("2022-8")
    private Integer Aug22;

    @ExcelProperty("2022-9")
    private Integer Sep22;

    @ExcelProperty("2022-10")
    private Integer Oct22;

    @ExcelProperty("2022-11")
    private Integer Nov22;

    @ExcelProperty("2022-12")
    private Integer Dec22;

    @ExcelProperty("2023-1")
    private Integer Jan23;
}
