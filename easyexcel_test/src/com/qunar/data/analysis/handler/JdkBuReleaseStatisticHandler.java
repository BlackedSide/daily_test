package com.qunar.data.analysis.handler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qunar.data.analysis.bean.BaseAppcodeInfo;
import com.qunar.data.analysis.bean.ReleaseCountEnum;
import com.qunar.data.analysis.bean.StatisticMonthEnum;
import com.qunar.data.analysis.bean.output.BuJdkStatistic;
import com.qunar.data.analysis.filter.*;
import com.qunar.data.analysis.storage.MatchStorage;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 3:04 PM
 * description
 */
public class JdkBuReleaseStatisticHandler extends BaseHandler {

    private final Map<String, BuJdkStatistic> forthOwnerStatistic = Maps.newHashMap();

    private final FilterActor filterActor;

    public JdkBuReleaseStatisticHandler(ReleaseCountEnum releaseCountEnum, Boolean isOnline, Boolean isP1P2, StatisticMonthEnum statisticMonthEnum) {
        List<BaseFilter> filters = Lists.newArrayList();
        filters.add(new JavaFilter());
        if (isOnline) {
            filters.add(new OnlineFilter());
        }
        if (isP1P2) {
            filters.add(new PriorityFilter());
        }
        filters.add(new ReleaseFilter(releaseCountEnum, statisticMonthEnum));
        filterActor = new FilterActor(filters);
    }

    @Override
    public void handle(BaseAppcodeInfo baseAppcodeInfo) {
        if (!filterActor.match(baseAppcodeInfo)) {
            return;
        }
        BuJdkStatistic buJdkStatistic = forthOwnerStatistic.get(baseAppcodeInfo.getOwner());
        String jdk = MatchStorage.appJdkMap.get(baseAppcodeInfo.getAppcode());
        if (buJdkStatistic == null) {
            buJdkStatistic = BuJdkStatistic.builder()
                    .owner(baseAppcodeInfo.getOwner())
                    .thirdBu(baseAppcodeInfo.getThirdBu())
                    .forthBu(baseAppcodeInfo.getForthBu())
                    .build();
            setJdk(baseAppcodeInfo.getAppcode(), jdk, buJdkStatistic);
            forthOwnerStatistic.put(baseAppcodeInfo.getOwner(), buJdkStatistic);
        } else {
            setJdk(baseAppcodeInfo.getAppcode(), jdk, buJdkStatistic);
        }
    }

    @Override
    public void printResult() {
        for (Map.Entry<String, BuJdkStatistic> entry : forthOwnerStatistic.entrySet()) {
//            System.out.print(entry.getValue().toString());
            System.out.println(entry.getValue().getOwner() + "\t" + printDetail(entry.getValue()));
        }
    }

    private String printDetail(BuJdkStatistic buJdkStatistic) {
        Set<String> all = Sets.newHashSet();
        all.addAll(buJdkStatistic.getJdk11Apps());
        all.addAll(buJdkStatistic.getJdk1_8Apps());
        all.addAll(buJdkStatistic.getJdk1_7Apps());
        all.addAll(buJdkStatistic.getJdk1_6Apps());
        StringBuilder stringBuilder = new StringBuilder();
        for (String appcode : all) {
            stringBuilder.append(appcode).append(",");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    private void setJdk(String appcode, String jdk, BuJdkStatistic buJdkStatistic) {
        switch (jdk) {
            case "17":
                buJdkStatistic.getJdk17Apps().add(appcode);
                break;
            case "11":
                buJdkStatistic.getJdk11Apps().add(appcode);
                break;
            case "1.8":
                buJdkStatistic.getJdk1_8Apps().add(appcode);
                break;
            case "1.7":
                buJdkStatistic.getJdk1_7Apps().add(appcode);
                break;
            case "1.6":
                buJdkStatistic.getJdk1_6Apps().add(appcode);
        }
    }
}
