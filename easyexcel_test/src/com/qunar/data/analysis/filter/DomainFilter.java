package com.qunar.data.analysis.filter;

import com.qunar.data.analysis.bean.BaseAppcodeInfo;
import com.qunar.data.analysis.storage.MatchStorage;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 7:32 PM
 * description
 */
public class DomainFilter implements BaseFilter {

    @Override
    public Boolean isMatch(BaseAppcodeInfo baseAppcodeInfo) {
        return MatchStorage.appDomainMap.get(baseAppcodeInfo.getAppcode()) != null;
    }
}
