package com.qunar.data.analysis.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jinpeng.fan
 * @date 2023/2/10 2:49 PM
 * description
 */
@Getter
@Setter
@EqualsAndHashCode
public class DubboMatchExcelInfo {

    @ExcelProperty("service")
    private String service;

    @ExcelProperty("providers")
    private String providers;

    @ExcelProperty("consumers")
    private String consumers;

    public static DubboMatchExcelInfo parseFromDubboMatchInfo(DubboMatchInfo dubboMatchInfo) {
        String providers = dubboMatchInfo.getProviders().toString().substring(1, dubboMatchInfo.getProviders().toString().length() - 1).replaceAll(" ", "");
        String consumers = dubboMatchInfo.getConsumers().toString().substring(1, dubboMatchInfo.getConsumers().toString().length() - 1).replaceAll(" ", "");
        DubboMatchExcelInfo dubboMatchExcelInfo = new DubboMatchExcelInfo();
        dubboMatchExcelInfo.setService(dubboMatchInfo.getService());
        dubboMatchExcelInfo.setProviders(providers);
        dubboMatchExcelInfo.setConsumers(consumers);
        return dubboMatchExcelInfo;
    }
}
