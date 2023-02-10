package com.qunar.data.analysis.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 10:33 AM
 * description
 */
@Getter
@Setter
@EqualsAndHashCode
public class BaseAppcodeInfo {

    @ExcelProperty("app_code")
    private String appcode;

    @ExcelProperty("app_type")
    private String appType;

    @ExcelProperty("线上容器实例数")
    private Integer dockerCount;

    @ExcelProperty("能否下线")
    private String canOffline;

    @ExcelProperty("level")
    private String level;

    @ExcelProperty("三级部门")
    private String thirdBu;

    @ExcelProperty("四级部门")
    private String forthBu;

    @ExcelProperty("负责人")
    private String owner;
}
