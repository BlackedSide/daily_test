package com.qunar.data.analysis.filter;

import com.qunar.data.analysis.bean.BaseAppcodeInfo;
import com.qunar.data.analysis.handler.JavaOnlineDockerDubboHandler;

import java.util.Set;

/**
 * @author jinpeng.fan
 * @date 2023/2/10 4:23 PM
 * description
 */
public class DubboMeshFilter implements BaseFilter {

    private final Set<String> canDubboMeshApps;

    public DubboMeshFilter() {
        JavaOnlineDockerDubboHandler handler = new JavaOnlineDockerDubboHandler();
        canDubboMeshApps = handler.collectAppCodes();
    }

    @Override
    public Boolean isMatch(BaseAppcodeInfo baseAppcodeInfo) {
        return canDubboMeshApps.contains(baseAppcodeInfo.getAppcode());
    }
}
