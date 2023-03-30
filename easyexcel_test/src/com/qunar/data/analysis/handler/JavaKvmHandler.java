package com.qunar.data.analysis.handler;

import com.google.common.collect.Lists;
import com.qunar.data.analysis.bean.BaseAppcodeInfo;
import com.qunar.data.analysis.filter.*;
import com.qunar.data.analysis.storage.MatchStorage;

import java.util.List;

/**
 * @author jinpeng.fan
 * @date 2023/2/28 3:45 PM
 * description
 */
public class JavaKvmHandler extends BaseHandler {

    private final FilterActor filterActor;

    private final List<String> jdk17KvmApps = Lists.newArrayList();

    public JavaKvmHandler() {
        List<BaseFilter> filters = Lists.newArrayList();
        filters.add(new JavaFilter());
        filters.add(new OnlineFilter());
        filters.add(new KvmFilter());
        filterActor = new FilterActor(filters);
    }

    @Override
    public void handle(BaseAppcodeInfo baseAppcodeInfo) {
        if (filterActor.match(baseAppcodeInfo) && MatchStorage.appJdkMap.get(baseAppcodeInfo.getAppcode()).equals("17")) {
            jdk17KvmApps.add(baseAppcodeInfo.getAppcode());
        }
    }

    @Override
    public void printResult() {
        System.out.println(jdk17KvmApps);
    }

    public static void main(String[] args) {
        JavaKvmHandler handler = new JavaKvmHandler();
        handler.readFile();
        handler.printResult();
    }
}
