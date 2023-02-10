package com.qunar.data.analysis.filter;

import com.qunar.data.analysis.bean.BaseAppcodeInfo;

import java.util.List;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 11:40 AM
 * description
 */
public class FilterActor {

    private final List<BaseFilter> filters;

    public FilterActor(List<BaseFilter> filters) {
        this.filters = filters;
    }

    public Boolean match(BaseAppcodeInfo baseAppcodeInfo) {
        boolean result = true;
        for (BaseFilter filter : filters) {
            result = result && filter.isMatch(baseAppcodeInfo);
        }
        return result;
    }
}
