package com.qunar.data.analysis.filter;

import com.qunar.data.analysis.bean.BaseAppcodeInfo;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 11:49 AM
 * description
 */
public class OnlineFilter implements BaseFilter {

    @Override
    public Boolean isMatch(BaseAppcodeInfo baseAppcodeInfo) {
        return !"是".equals(baseAppcodeInfo.getCanOffline());
    }
}
