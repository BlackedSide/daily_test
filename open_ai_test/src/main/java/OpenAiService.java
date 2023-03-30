import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author jinpeng.fan
 * @date 2023/3/29 8:28 PM
 * description
 */
public class OpenAiService {

    private static final String OPEN_AI_URL = "https://api.openai.com/v1/chat/completions";

    private static final Retryer<String> RETRYER = RetryerBuilder.<String>newBuilder()
            .retryIfExceptionOfType(CanRetryException.class)
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();

    private final String token;
    private final String targetFolder;

    public OpenAiService(String token, String targetFolder) {
        this.token = String.format("Bearer %s", token);
        checkToken();
        this.targetFolder = checkTargetFolder(targetFolder);
    }

    public void generateResult(String requestMsg) {
        String target = generateFileTarget(requestMsg);
        try {
            Callable<String> callable = () -> getRawResponseFromOpenAi(requestMsg);
            String result = RETRYER.call(callable);
            result = String.format("# %s\\n\\n", requestMsg) + result;
            FileWriter writer = new FileWriter(target);
            writer.write(result.replaceAll("\\\\n", "\n"));
            writer.close();
            System.out.printf("结果已写入 %s 中，请使用Markdown编辑器查看~\n", target);
        } catch (Exception e) {
            System.out.println("发生了一些错误……");
            e.printStackTrace();
            System.out.println();
        }
    }

    private String getRawResponseFromOpenAi(String requestMsg) throws Exception {
        String requestBody = String.format("{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}]}", requestMsg);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, requestBody);
        Request request = new Request.Builder()
                .url(OPEN_AI_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 401) {
                throw new Exception("权限验证失败……");
            }
            if (!response.isSuccessful() || response.body() == null) {
                throw new CanRetryException("请求OpenAI失败或返回为空……");
            }
            JSONObject responseBody = new ObjectMapper().readValue(response.body().string(), JSONObject.class);
            JSONArray choices = responseBody.getJSONArray("choices");
            return choices.getJSONObject(0).getJSONObject("message").getString("content");
        } catch (Exception e) {
            if (e.getMessage().contains("权限验证失败……")) {
                throw new Exception("权限验证失败……");
            }
            System.out.println("请求OpenAI失败……");
            e.printStackTrace();
            throw new CanRetryException("请求OpenAI失败……");
        }
    }

    private String generateFileTarget(String request) {
        return targetFolder + "/" + request + ".md";
    }

    private void checkToken() {
        Callable<String> callable = () -> getRawResponseFromOpenAi("Hello, OpenAI.");
        try {
            RETRYER.call(callable);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private String checkTargetFolder(String targetFolder) {
        if (StringUtils.isBlank(targetFolder)) {
            System.out.println("未输入回答目标存储的文件目录……");
            System.exit(1);
        }
        if (targetFolder.endsWith("/")) {
            return targetFolder.substring(0, targetFolder.length() - 1);
        }
        return targetFolder;
    }
}
