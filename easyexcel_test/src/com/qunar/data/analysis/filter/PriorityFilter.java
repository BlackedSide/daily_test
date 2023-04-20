package com.qunar.data.analysis.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.qunar.data.analysis.bean.BaseAppcodeInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 2:25 PM
 * description
 */
public class PriorityFilter implements BaseFilter {

    private static final Map<String, String> onlinePriorityMap = Maps.newHashMap();

    static {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url("http://stability.corp.qunar.com/api/appCode/getCodeInfo")
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            JSONArray resultArr = new ObjectMapper().readValue(response.body().string(), JSONObject.class).getJSONArray("data");
            for (JSONObject obj : resultArr.toJavaList(JSONObject.class)) {
                onlinePriorityMap.put(obj.getString("appCode"), obj.getString("codeGrade"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean isMatch(BaseAppcodeInfo baseAppcodeInfo) {
        String appcode = baseAppcodeInfo.getAppcode();
        return "P1".equals(onlinePriorityMap.get(appcode)) || "P2".equals(onlinePriorityMap.get(appcode));
    }
}
