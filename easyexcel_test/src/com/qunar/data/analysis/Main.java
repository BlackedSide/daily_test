package com.qunar.data.analysis;

import com.qunar.data.analysis.bean.ReleaseCountEnum;
import com.qunar.data.analysis.bean.StatisticMonthEnum;
import com.qunar.data.analysis.handler.BaseHandler;
import com.qunar.data.analysis.handler.JavaDubboMeshStatisticHandler;
import com.qunar.data.analysis.handler.JavaHttpMeshStatisticHandler;
import com.qunar.data.analysis.handler.JdkBuReleaseStatisticHandler;
import org.apache.zookeeper.server.SnapshotFormatter;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 11:08 AM
 * description
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        ZkSnapshotAnalyzer zkSnapshotAnalyzer = new ZkSnapshotAnalyzer();
//        zkSnapshotAnalyzer.handle("/Users/fanny/Downloads/zksnapshot/snapshot_cn6.log", "/Users/fanny/Downloads/zksnapshot/dubbo_cn6_info.csv");
        BaseHandler handler = new JavaDubboMeshStatisticHandler();
        handler.readFile();
        handler.printResult();
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
}
