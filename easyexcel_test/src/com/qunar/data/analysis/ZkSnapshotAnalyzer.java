package com.qunar.data.analysis;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.qunar.data.analysis.bean.DubboInfo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

/**
 * @author jinpeng.fan
 * @date 2023/2/9 4:22 PM
 * description
 */
public class ZkSnapshotAnalyzer {

    private final Set<String> providers = Sets.newHashSet();
    private final Set<String> consumers = Sets.newHashSet();
    private final List<DubboInfo> outputData = Lists.newArrayList();

    public void handle(String input, String output) {
        try {
            FileInputStream inputStream = new FileInputStream(input);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("/providers/dubbo")) {
                    String service = catchProviderService(line);
                    if (!providers.contains(service)) {
                        DubboInfo dubboInfo = new DubboInfo();
                        dubboInfo.setSide("provider");
                        dubboInfo.setAppcode(catchAppcode(line));
                        dubboInfo.setService(service);
                        providers.add(service);
                        outputData.add(dubboInfo);
                    }
                }
                if (line.contains("/consumers/consumer")) {
                    String service = catchConsumerService(line);
                    String appcode = catchAppcode(line);
                    String key = String.format("%s-%s", service, appcode);
                    if (!consumers.contains(key)) {
                        DubboInfo dubboInfo = new DubboInfo();
                        dubboInfo.setSide("consumer");
                        dubboInfo.setAppcode(appcode);
                        dubboInfo.setService(service);
                        consumers.add(key);
                        outputData.add(dubboInfo);
                    }
                }
            }
            EasyExcel.write(output, DubboInfo.class).sheet().doWrite(outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String catchProviderService(String line) {
        int index = line.indexOf("/providers/dubbo");
        String buf = line.substring(0, index);
        String[] buf2 = buf.split("/");
        return buf2[buf2.length - 1];
    }

    private static String catchConsumerService(String line) {
        int index = line.indexOf("/consumers/consumer");
        String buf = line.substring(0, index);
        String[] buf2 = buf.split("/");
        return buf2[buf2.length - 1];
    }

    private static String catchAppcode(String line) {
        int index = line.indexOf("qapp%3D") + 7;
        String buf = line.substring(index);
        String[] buf2 = buf.split("%");
        return buf2[0];
    }

    public static void main(String[] args) {
        String provider = "/hotelSearch-totoro-feedback-prod/com.qunar.hotel.feedback.api.rservice.NewFeedbackService/providers/dubbo%3A%2F%2F10.67.50.50%3A20800%2Fcom.qunar.hotel.feedback.api.rservice.NewFeedbackService%3Fanyhost%3Dtrue%26application%3Dh_hs_totoro%26deprecated%3Dfalse%26dubbo%3D2.0.2%26dynamic%3Dtrue%26env%3Dproddocker%26generic%3Dfalse%26interface%3Dcom.qunar.hotel.feedback.api.rservice.NewFeedbackService%26methods%3DqueryStatus%26pid%3D336%26qaccesslog%3D1%26qapp%3Dh_hs_totoro%26queues%3D1500%26release%3D4.0.74%26revision%3D1.0.24%26side%3Dprovider%26threads%3D1000%26timeout%3D5000%26timestamp%3D1675749380520%26version%3D1.0";
        System.out.println(catchProviderService(provider));
        System.out.println(catchAppcode(provider));
        String consumer = "/tc_public_service/com.qunar.tc.core.info.api.InfoDecryptService/consumers/consumer%3A%2F%2F10.67.223.192%2Fcom.qunar.tc.core.info.api.InfoDecryptService%3Factive%3Dfalse%26application%3Df_inter_gds%26async%3Dtrue%26category%3Dconsumers%26check%3Dfalse%26configured.mesh%3Dfalse%26dubbo%3D2.0.2%26hostname%3Dprod-merge-dep1-6b6cc4698b-9g6gt%26id%3Df_inter_gds%26interface%3Dcom.qunar.tc.core.info.api.InfoDecryptService%26methods%3DdecryptMail%2CdecryptPhoneNum%2CdecryptPassport%2CdecryptBankNo%2CdecryptOther%2CdecryptPersonalIdVal%2CdecryptPhone%2CdecryptPersonalId%2CqueryEncryptResult%2CdecryptNickname%2CanyDecrypt%2CdecryptAddress%2CdecryptAccount%2Cdecrypt%2CdecryptUsername%26name%3Df_inter_gds%26pid%3D258%26prefix%3Ddubbo.application%26qapp%3Df_inter_gds%26qloglevel%3D4%26release%3D4.0.74%26revision%3D2.0.1%26side%3Dconsumer%26sticky%3Dfalse%26timestamp%3D1675846570763%26valid%3Dtrue%26version%3D1.0.0";
        System.out.println(catchConsumerService(consumer));
        System.out.println(catchAppcode(consumer));
    }
}
