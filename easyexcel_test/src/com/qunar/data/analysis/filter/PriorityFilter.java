package com.qunar.data.analysis.filter;

import com.qunar.data.analysis.bean.BaseAppcodeInfo;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 2:25 PM
 * description
 */
public class PriorityFilter implements BaseFilter {

    @Override
    public Boolean isMatch(BaseAppcodeInfo baseAppcodeInfo) {
        return "P1".equals(baseAppcodeInfo.getLevel()) || "P2".equals(baseAppcodeInfo.getLevel());
    }
}
