package com.qunar.data.analysis.filter;

import com.qunar.data.analysis.bean.BaseAppcodeInfo;

/**
 * @author jinpeng.fan
 * @date 2023/2/10 5:40 PM
 * description
 */
public class NoPriorityFilter implements BaseFilter {
    @Override
    public Boolean isMatch(BaseAppcodeInfo baseAppcodeInfo) {
        return "P3".equals(baseAppcodeInfo.getLevel()) || "P4".equals(baseAppcodeInfo.getLevel());
    }
}
