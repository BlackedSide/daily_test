package com.qunar.data.analysis.storage;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qunar.data.analysis.bean.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 10:42 AM
 * description
 */
public class MatchStorage {

    public static final Map<String, AppReleaseInfo> appReleaseMap = Maps.newHashMap();
    public static final Map<String, String> appJdkMap = Maps.newHashMap();
    public static final Map<String, String> appDomainMap = Maps.newHashMap();
    public static final Map<String, DubboSideEnum> appDubboSideMap = Maps.newHashMap();
    public static final Map<String, DubboMatchInfo> dubboServiceMatchMap = Maps.newHashMap();

    static {
        collectAppRelease();
        collectAppJdk();
        collectAppDomains();
//        collectAppDubboSide();
    }

    private static void collectAppRelease() {
        String input = "/Users/fanny/Qunar/技术先进性跑数/app_code_3.csv";
        EasyExcel.read(input, AppReleaseInfo.class, new ReadListener<AppReleaseInfo>() {
            @Override
            public void invoke(AppReleaseInfo data, AnalysisContext analysisContext) {
                appReleaseMap.put(data.getAppcode(), data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
    }

    private static void collectAppJdk() {
        try {
            String input = "/Users/fanny/Qunar/技术先进性跑数/appcode_jdk.txt";
            FileInputStream inputStream = new FileInputStream(input);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] appcode_jdk = line.split(",");
                if (appcode_jdk.length != 2) {
                    continue;
                }
                if (StringUtils.isBlank(appcode_jdk[0]) || StringUtils.isBlank(appcode_jdk[1])) {
                    continue;
                }
                appJdkMap.put(appcode_jdk[0], appcode_jdk[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void collectAppDomains() {
        String input = "/Users/fanny/Qunar/技术先进性跑数/domain_appcode_relation.csv";
        EasyExcel.read(input, AppcodeDomainInfo.class, new ReadListener<AppcodeDomainInfo>() {
            @Override
            public void invoke(AppcodeDomainInfo data, AnalysisContext analysisContext) {
                appDomainMap.put(data.getAppcode(), data.getDomain());
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
    }

    private static final String input1 = "/Users/fanny/Qunar/技术先进性跑数/dubbo_cn1_info.csv";
    private static final String input2 = "/Users/fanny/Qunar/技术先进性跑数/dubbo_cn2_info.csv";
    private static final String input3 = "/Users/fanny/Qunar/技术先进性跑数/dubbo_cn5_info.csv";
    private static final String input4 = "/Users/fanny/Qunar/技术先进性跑数/dubbo_cn6_info.csv";
    private static final String input5 = "/Users/fanny/Qunar/技术先进性跑数/dubbo_cna_info.csv";
    private static final List<String> dubboInputList = Lists.newArrayList(input1, input2, input3, input4, input5);

    /**
     * 统计维度有误，暂时废弃
     */
    @Deprecated
    private static void collectAppDubboSide() {
        for (String input : dubboInputList) {
            EasyExcel.read(input, DubboInfo.class, new ReadListener<DubboInfo>() {
                @Override
                public void invoke(DubboInfo data, AnalysisContext analysisContext) {
                    DubboSideEnum existDubboSide = appDubboSideMap.get(data.getAppcode());
                    DubboSideEnum dataSide = DubboSideEnum.parseDetail(data.getSide());
                    if (existDubboSide == null) {
                        appDubboSideMap.put(data.getAppcode(), dataSide);
                        return;
                    }
                    if (existDubboSide.equals(DubboSideEnum.BOTH)) {
                        return;
                    }
                    if (!existDubboSide.equals(dataSide)) {
                        appDubboSideMap.put(data.getAppcode(), DubboSideEnum.BOTH);
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                }
            }).sheet().doRead();
        }
    }

    private static void collectDubboServiceMatch() {
        for (String input : dubboInputList) {
            EasyExcel.read(input, DubboInfo.class, new ReadListener<DubboInfo>() {
                @Override
                public void invoke(DubboInfo data, AnalysisContext analysisContext) {
                    DubboMatchInfo dubboMatchInfo = dubboServiceMatchMap.get(data.getService());
                    if (dubboMatchInfo == null) {
                        dubboMatchInfo = DubboMatchInfo.builder()
                                .service(data.getService())
                                .build();
                        setDubboMathInfo(dubboMatchInfo, data);
                        dubboServiceMatchMap.put(data.getService(), dubboMatchInfo);
                    } else {
                        setDubboMathInfo(dubboMatchInfo, data);
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                }
            }).sheet().doRead();
        }
    }

    private static void setDubboMathInfo(DubboMatchInfo dubboMatchInfo, DubboInfo dubboInfo) {
        DubboSideEnum dubboSideEnum = DubboSideEnum.parseDetail(dubboInfo.getSide());
        if (dubboSideEnum == null) {
            return;
        }
        switch (dubboSideEnum) {
            case PROVIDER:
                dubboMatchInfo.getProviders().add(dubboInfo.getAppcode());
                break;
            case CONSUMER:
                dubboMatchInfo.getConsumers().add(dubboInfo.getAppcode());
                break;
        }
    }

    private static void generateDubboMatchExcel() {
        String output = "/Users/fanny/Qunar/技术先进性跑数/dubbo_match.csv";
        List<DubboMatchExcelInfo> result = Lists.newArrayList();
        for (Map.Entry<String, DubboMatchInfo> entry : dubboServiceMatchMap.entrySet()) {
            result.add(DubboMatchExcelInfo.parseFromDubboMatchInfo(entry.getValue()));
        }
        EasyExcel.write(output, DubboMatchExcelInfo.class).sheet().doWrite(result);
    }

    public static void main(String[] args) {
//        collectDubboServiceMatch();
////        Set<String> test = Sets.newHashSet();
////        System.out.println(test);
//        generateDubboMatchExcel();
        Set<String> result = Sets.newHashSet();
        for (Map.Entry<String, String> entry : MatchStorage.appJdkMap.entrySet()) {
            if ("17".equals(entry.getValue())) {
                result.add(entry.getKey());
            }
        }
        System.out.println(result.toString().substring(1, result.toString().length() - 1).replaceAll(" ", ""));
    }
}
