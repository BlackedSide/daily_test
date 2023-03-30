package com.qunar.data.analysis.filter;

import com.qunar.data.analysis.bean.BaseAppcodeInfo;

/**
 * @author jinpeng.fan
 * @date 2023/2/28 3:47 PM
 * description
 */
public class KvmFilter implements BaseFilter {
    @Override
    public Boolean isMatch(BaseAppcodeInfo baseAppcodeInfo) {
        return baseAppcodeInfo.getKvmCount() > 0;
    }
}
