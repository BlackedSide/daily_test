package com.qunar.data.analysis.bean;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 2:46 PM
 * description
 */
public enum ReleaseCountEnum {
    /**
     * >= 0
     */
    TOTAL,
    /**
     * > 0
     */
    OVER_ZERO,
    /**
     * >= 10
     */
    OVER_TEN,
    /**
     * >= 5
     */
    OVER_FIVE,
    /**
     * 0 < count < 5
     */
    BETWEEN_ZERO_TO_FIVE,
    /**
     * == 0
     */
    EQUAL_ZERO
}
