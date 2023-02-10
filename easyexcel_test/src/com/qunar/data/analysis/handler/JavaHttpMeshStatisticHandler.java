package com.qunar.data.analysis.handler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.data.analysis.bean.BaseAppcodeInfo;
import com.qunar.data.analysis.bean.ReleaseCountEnum;
import com.qunar.data.analysis.bean.StatisticMonthEnum;
import com.qunar.data.analysis.bean.output.BuHttpMeshStatistic;
import com.qunar.data.analysis.filter.*;

import java.util.List;
import java.util.Map;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 7:15 PM
 * description
 */
public class JavaHttpMeshStatisticHandler extends BaseHandler {

    private final Map<String, BuHttpMeshStatistic> httpMeshStatisticMap = Maps.newHashMap();

    private final FilterActor dockerJavaFilter;
    private final FilterActor dockerJavaDomainFilter;
    private final FilterActor p1p2Filter;
    private final FilterActor p1p2Over10Filter;
    private final FilterActor p1p2Over5Filter;
    private final FilterActor p1p2Between0to5Filter;
    private final FilterActor p1p2Equal0Filter;

    public JavaHttpMeshStatisticHandler() {
        dockerJavaFilter = new FilterActor(Lists.newArrayList(new JavaFilter(), new DockerFilter(), new OnlineFilter()));
        dockerJavaDomainFilter = new FilterActor(Lists.newArrayList(new JavaFilter(), new DockerFilter(), new OnlineFilter(), new DomainFilter()));
        p1p2Filter = new FilterActor(Lists.newArrayList(new JavaFilter(), new DockerFilter(), new OnlineFilter(), new DomainFilter(), new PriorityFilter()));
        p1p2Over10Filter = new FilterActor(Lists.newArrayList(new JavaFilter(), new DockerFilter(), new OnlineFilter(), new DomainFilter(), new PriorityFilter(), new ReleaseFilter(ReleaseCountEnum.OVER_TEN, StatisticMonthEnum.HALF_YEAR)));
        p1p2Over5Filter = new FilterActor(Lists.newArrayList(new JavaFilter(), new DockerFilter(), new OnlineFilter(), new DomainFilter(), new PriorityFilter(), new ReleaseFilter(ReleaseCountEnum.OVER_FIVE, StatisticMonthEnum.HALF_YEAR)));
        p1p2Between0to5Filter = new FilterActor(Lists.newArrayList(new JavaFilter(), new DockerFilter(), new OnlineFilter(), new DomainFilter(), new PriorityFilter(), new ReleaseFilter(ReleaseCountEnum.BETWEEN_ZERO_TO_FIVE, StatisticMonthEnum.HALF_YEAR)));
        p1p2Equal0Filter = new FilterActor(Lists.newArrayList(new JavaFilter(), new DockerFilter(), new OnlineFilter(), new DomainFilter(), new PriorityFilter(), new ReleaseFilter(ReleaseCountEnum.EQUAL_ZERO, StatisticMonthEnum.HALF_YEAR)));
    }

    @Override
    public void handle(BaseAppcodeInfo baseAppcodeInfo) {
//        BuHttpMeshStatistic buHttpMeshStatistic = httpMeshStatisticMap.get(baseAppcodeInfo.getOwner());
        BuHttpMeshStatistic buHttpMeshStatistic = httpMeshStatisticMap.get(baseAppcodeInfo.getThirdBu());
        if (buHttpMeshStatistic == null) {
            buHttpMeshStatistic = BuHttpMeshStatistic.builder()
                    .owner(baseAppcodeInfo.getOwner())
                    .thirdBu(baseAppcodeInfo.getThirdBu())
                    .forthBu(baseAppcodeInfo.getForthBu())
                    .build();
            setMap(buHttpMeshStatistic, baseAppcodeInfo);
//            httpMeshStatisticMap.put(baseAppcodeInfo.getOwner(), buHttpMeshStatistic);
            httpMeshStatisticMap.put(baseAppcodeInfo.getThirdBu(), buHttpMeshStatistic);
        } else {
            setMap(buHttpMeshStatistic, baseAppcodeInfo);
        }
    }

    @Override
    public void printResult() {
        for (Map.Entry<String, BuHttpMeshStatistic> entry : httpMeshStatisticMap.entrySet()) {
            System.out.print(entry.getValue().toString());
        }
    }

    private void setMap(BuHttpMeshStatistic buHttpMeshStatistic, BaseAppcodeInfo baseAppcodeInfo) {
        String appcode = baseAppcodeInfo.getAppcode();
        if (dockerJavaFilter.match(baseAppcodeInfo)) {
            buHttpMeshStatistic.getDockerJavaApps().add(appcode);
        }
        if (dockerJavaDomainFilter.match(baseAppcodeInfo)) {
            buHttpMeshStatistic.getDockerDomainJavaApps().add(appcode);
        }
        if (p1p2Filter.match(baseAppcodeInfo)) {
            buHttpMeshStatistic.getP1p2DockerDomainJavaApps().add(appcode);
        }
        if (p1p2Over10Filter.match(baseAppcodeInfo)) {
            buHttpMeshStatistic.getP1p2DockerDomainJavaAppsOver10().add(appcode);
        }
        if (p1p2Over5Filter.match(baseAppcodeInfo)) {
            buHttpMeshStatistic.getP1p2DockerDomainJavaAppsOver5().add(appcode);
        }
        if (p1p2Between0to5Filter.match(baseAppcodeInfo)) {
            buHttpMeshStatistic.getP1p2DockerDomainJavaAppsBetween0to5().add(appcode);
        }
        if (p1p2Equal0Filter.match(baseAppcodeInfo)) {
            buHttpMeshStatistic.getP1p2DockerDomainJavaAppsEqual0().add(appcode);
        }
    }
}
