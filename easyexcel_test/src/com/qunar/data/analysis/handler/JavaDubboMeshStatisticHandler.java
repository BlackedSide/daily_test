package com.qunar.data.analysis.handler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.data.analysis.bean.BaseAppcodeInfo;
import com.qunar.data.analysis.bean.ReleaseCountEnum;
import com.qunar.data.analysis.bean.StatisticMonthEnum;
import com.qunar.data.analysis.bean.output.BuDubboMeshStatistic;
import com.qunar.data.analysis.filter.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author jinpeng.fan
 * @date 2023/2/10 4:27 PM
 * description
 */
public class JavaDubboMeshStatisticHandler extends BaseHandler {

    private final Map<String, BuDubboMeshStatistic> dubboMeshStatisticMap = Maps.newHashMap();

    private final FilterActor canDubboMeshFilter;
    private final FilterActor p1p2Filter;
    private final FilterActor p1p2Over5Filter;
    private final FilterActor p1p2Between0to5Filter;
    private final FilterActor p1p2Equal0Filter;

    public JavaDubboMeshStatisticHandler() {
        DubboMeshFilter dubboMeshFilter = new DubboMeshFilter();
        canDubboMeshFilter = new FilterActor(Lists.newArrayList(dubboMeshFilter));
        p1p2Filter = new FilterActor(Lists.newArrayList(dubboMeshFilter, new PriorityFilter()));
        p1p2Over5Filter = new FilterActor(Lists.newArrayList(dubboMeshFilter, new PriorityFilter(), new ReleaseFilter(ReleaseCountEnum.OVER_FIVE, StatisticMonthEnum.HALF_YEAR)));
        p1p2Between0to5Filter = new FilterActor(Lists.newArrayList(dubboMeshFilter, new PriorityFilter(), new ReleaseFilter(ReleaseCountEnum.BETWEEN_ZERO_TO_FIVE, StatisticMonthEnum.HALF_YEAR)));
        p1p2Equal0Filter = new FilterActor(Lists.newArrayList(dubboMeshFilter, new PriorityFilter(), new ReleaseFilter(ReleaseCountEnum.EQUAL_ZERO, StatisticMonthEnum.HALF_YEAR)));
    }

    @Override
    public void handle(BaseAppcodeInfo baseAppcodeInfo) {
        BuDubboMeshStatistic buDubboMeshStatistic = dubboMeshStatisticMap.get(baseAppcodeInfo.getOwner());
        if (buDubboMeshStatistic == null) {
            buDubboMeshStatistic = BuDubboMeshStatistic.builder()
                    .owner(baseAppcodeInfo.getOwner())
                    .thirdBu(baseAppcodeInfo.getThirdBu())
                    .forthBu(baseAppcodeInfo.getForthBu())
                    .build();
            setMap(buDubboMeshStatistic, baseAppcodeInfo);
            dubboMeshStatisticMap.put(baseAppcodeInfo.getOwner(), buDubboMeshStatistic);
        } else {
            setMap(buDubboMeshStatistic, baseAppcodeInfo);
        }
    }

    @Override
    public void printResult() {
        for (Map.Entry<String, BuDubboMeshStatistic> entry : dubboMeshStatisticMap.entrySet()) {
            System.out.print(entry.getValue().toString());
        }
        System.out.println(nullLevelCount);
    }

    private Integer nullLevelCount = 0;

    private void setMap(BuDubboMeshStatistic buDubboMeshStatistic, BaseAppcodeInfo baseAppcodeInfo) {
        String appcode = baseAppcodeInfo.getAppcode();
        if (canDubboMeshFilter.match(baseAppcodeInfo)) {
            if (StringUtils.isBlank(baseAppcodeInfo.getLevel())) {
                nullLevelCount++;
            }
            buDubboMeshStatistic.getCanDubboMeshAppCodes().add(appcode);
        }
        if (p1p2Filter.match(baseAppcodeInfo)) {
            buDubboMeshStatistic.getP1p2Apps().add(appcode);
        }
        if (p1p2Over5Filter.match(baseAppcodeInfo)) {
            buDubboMeshStatistic.getP1p2Over5Apps().add(appcode);
        }
        if (p1p2Between0to5Filter.match(baseAppcodeInfo)) {
            buDubboMeshStatistic.getP1p2Between0to5Apps().add(appcode);
        }
        if (p1p2Equal0Filter.match(baseAppcodeInfo)) {
            buDubboMeshStatistic.getP1p2Equal0Apps().add(appcode);
        }
    }
}
