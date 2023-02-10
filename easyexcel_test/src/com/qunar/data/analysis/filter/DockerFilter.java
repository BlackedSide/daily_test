package com.qunar.data.analysis.filter;

import com.qunar.data.analysis.bean.BaseAppcodeInfo;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 11:48 AM
 * description
 */
public class DockerFilter implements BaseFilter {

    @Override
    public Boolean isMatch(BaseAppcodeInfo baseAppcodeInfo) {
        return baseAppcodeInfo.getDockerCount() > 0;
    }
}
