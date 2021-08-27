package jackson;

import com.google.common.base.Strings;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class JacksonTest {

    @Test
    public void mapToJson() throws IOException {
        Date nowTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String nowTimeString = simpleDateFormat.format(nowTime);
        String jsonString = "{\"admin-update-time\":\"" + nowTimeString + "\"}";
        System.out.println(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(jsonString, Map.class);
        for (String key : map.keySet()) {
            if (key.equals("admin-update-time")) {
                map.put(key, new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
            }
        }
        String nowJson = mapper.writeValueAsString(map);
        System.out.println(nowJson);
    }
}
