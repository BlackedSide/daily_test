package com.qunar.data.analysis;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.*;
import com.qunar.data.analysis.bean.AppDomainConsumerInfo;
import com.qunar.data.analysis.bean.AppcodeDomainInfo;
import com.qunar.data.analysis.bean.ReleaseCountEnum;
import com.qunar.data.analysis.bean.StatisticMonthEnum;
import com.qunar.data.analysis.handler.BaseHandler;
import com.qunar.data.analysis.handler.JavaDubboMeshStatisticHandler;
import com.qunar.data.analysis.handler.JavaHttpMeshStatisticHandler;
import com.qunar.data.analysis.handler.JdkBuReleaseStatisticHandler;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.collections.MultiMap;
import org.apache.zookeeper.server.SnapshotFormatter;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 11:08 AM
 * description
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        ZkSnapshotAnalyzer zkSnapshotAnalyzer = new ZkSnapshotAnalyzer();
//        zkSnapshotAnalyzer.handle("/Users/fanny/Downloads/zksnapshot/snapshot_cn6.log", "/Users/fanny/Downloads/zksnapshot/dubbo_cn6_info.csv");
//        BaseHandler handler = new JavaDubboMeshStatisticHandler();
//        handler.readFile();
//        handler.printResult();
        collectHttpMeshConsumers();
    }

    private static void printJdk() {
        BaseHandler p1p2jdkBuReleaseStatisticHandler = new JdkBuReleaseStatisticHandler(ReleaseCountEnum.OVER_ZERO, true, true, StatisticMonthEnum.HALF_YEAR);
        p1p2jdkBuReleaseStatisticHandler.readFile();
        p1p2jdkBuReleaseStatisticHandler.printResult();
    }

    private static void printHttpMesh() {
        BaseHandler httpMeshHandler = new JavaHttpMeshStatisticHandler();
        httpMeshHandler.readFile();
        httpMeshHandler.printResult();
    }

    private static void formatterZkSnapshot() throws Exception {
        String output = "/Users/fanny/Downloads/zksnapshot/snapshot_cna.log";
        PrintStream ps = new PrintStream(new FileOutputStream(output));
        System.setOut(ps);
        String[] args = new String[1];
        args[0] = "/Users/fanny/Downloads/zksnapshot/snapshot.cna";
        SnapshotFormatter.main(args);
    }

    private static void collectHttpMeshConsumers() {
        List<AppDomainConsumerInfo> result = Lists.newArrayList();
        Set<String> addedApps = Sets.newHashSet();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();
        String input = "/Users/fanny/Qunar/技术先进性跑数/domain_appcode_relation.csv";
        EasyExcel.read(input, AppcodeDomainInfo.class, new ReadListener<AppcodeDomainInfo>() {
            @Override
            public void invoke(AppcodeDomainInfo data, AnalysisContext analysisContext) {
                String url = "http://pbservice.corp.qunar.com/api/app/relation/listByAppCodeWithCallPoint.json?appcode=" + data.getAppcode();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Set<String> relyMeList = Sets.newHashSet();
                try (Response response = client.newCall(request).execute()) {
                    assert response.body() != null;
                    JSONArray resultArr = new ObjectMapper().readValue(response.body().string(), JSONObject.class).getJSONObject("data").getJSONObject("http").getJSONArray("relyMe");
                    for (JSONObject obj : resultArr.toJavaList(JSONObject.class)) {
                        relyMeList.add(obj.getString("appcode"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (addedApps.contains(data.getAppcode())) {
                    return;
                }
                AppDomainConsumerInfo info = new AppDomainConsumerInfo();
                info.setAppcode(data.getAppcode());
                info.setDomain(data.getDomain());
                String relyMe = relyMeList.toString();
                info.setConsumers(relyMe.substring(1, relyMe.length() - 1));
                result.add(info);
                addedApps.add(data.getAppcode());
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
        String output = "/Users/fanny/Qunar/技术先进性跑数/app_domain_consumers_new.csv";
        EasyExcel.write(output, AppDomainConsumerInfo.class).sheet().doWrite(result);
    }

    private void test() {

    }
}
