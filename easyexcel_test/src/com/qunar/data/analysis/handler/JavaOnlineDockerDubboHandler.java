package com.qunar.data.analysis.handler;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.qunar.data.analysis.bean.BaseAppcodeInfo;
import com.qunar.data.analysis.bean.DubboMatchExcelInfo;
import com.qunar.data.analysis.filter.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * @author jinpeng.fan
 * @date 2023/2/10 3:36 PM
 * description
 */
public class JavaOnlineDockerDubboHandler extends BaseHandler {

    private final Set<String> javaOnlineDockerApps = Sets.newHashSet();

    private final FilterActor filterActor;

    public JavaOnlineDockerDubboHandler() {
        List<BaseFilter> filters = Lists.newArrayList();
        filters.add(new JavaFilter());
        filters.add(new OnlineFilter());
        filters.add(new DockerFilter());
        filterActor = new FilterActor(filters);
    }

    @Override
    public void handle(BaseAppcodeInfo baseAppcodeInfo) {
        if (filterActor.match(baseAppcodeInfo)) {
            javaOnlineDockerApps.add(baseAppcodeInfo.getAppcode());
        }
    }

    @Override
    public void printResult() {

    }

    public Set<String> getJavaOnlineDockerApps() {
        return javaOnlineDockerApps;
    }

    public Set<String> collectAppCodes() {
        Set<String> result = Sets.newHashSet();
        JavaOnlineDockerDubboHandler handler = new JavaOnlineDockerDubboHandler();
        handler.readFile();
        // 此时，this的javaOnlineDockerApps的集合是空，被填充的是该新建的handler对象中的javaOnlineDockerApps集合
        javaOnlineDockerApps.addAll(handler.getJavaOnlineDockerApps());
        String input = "/Users/fanny/Qunar/技术先进性跑数/dubbo_match.csv";
        EasyExcel.read(input, DubboMatchExcelInfo.class, new ReadListener<DubboMatchExcelInfo>() {
            @Override
            public void invoke(DubboMatchExcelInfo data, AnalysisContext analysisContext) {
                filterData(result, data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
        return result;
    }

    private void filterData(Set<String> result, DubboMatchExcelInfo data) {
        if (StringUtils.isBlank(data.getProviders()) || StringUtils.isBlank(data.getConsumers())) {
            return;
        }
        Set<String> providers = Sets.newHashSet(data.getProviders().split(","));
        Set<String> consumers = Sets.newHashSet(data.getConsumers().split(","));
        Set<String> filterProviders = Sets.newHashSet();
        Set<String> filterConsumers = Sets.newHashSet();
        for (String provider : providers) {
            if (javaOnlineDockerApps.contains(provider)) {
                filterProviders.add(provider);
            }
        }
        for (String consumer : consumers) {
            if (javaOnlineDockerApps.contains(consumer)) {
                filterConsumers.add(consumer);
            }
        }
        if (!filterProviders.isEmpty() && !filterConsumers.isEmpty()) {
            result.addAll(filterProviders);
            result.addAll(filterConsumers);
        }
    }
}
