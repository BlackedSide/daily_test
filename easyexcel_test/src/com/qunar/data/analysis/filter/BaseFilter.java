package com.qunar.data.analysis.filter;

import com.qunar.data.analysis.bean.BaseAppcodeInfo;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 11:16 AM
 * description
 */
public interface BaseFilter {

    Boolean isMatch(BaseAppcodeInfo baseAppcodeInfo);
}
