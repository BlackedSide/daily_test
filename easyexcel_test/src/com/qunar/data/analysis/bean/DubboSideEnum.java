package com.qunar.data.analysis.bean;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jinpeng.fan
 * @date 2023/2/9 5:40 PM
 * description
 */
public enum DubboSideEnum {

    /**
     * provider
     */
    PROVIDER("provider"),

    /**
     * consumer
     */
    CONSUMER("consumer"),

    /**
     * 既是provider又是consumer
     */
    BOTH("both");

    private final String detail;

    DubboSideEnum(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public static DubboSideEnum parseDetail(String detail) {
        for (DubboSideEnum dubboSideEnum : DubboSideEnum.values()) {
            if (StringUtils.equals(detail, dubboSideEnum.getDetail())) {
                return dubboSideEnum;
            }
        }
        return null;
    }
}
