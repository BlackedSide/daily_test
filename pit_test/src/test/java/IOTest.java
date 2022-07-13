import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * @author jinpeng.fan
 * @date 2022/4/22 2:21 PM
 * description
 */
public class IOTest {

    /**
     * 查看文件是否有所需写入的内容，若有，则跳过，若无，则写入
     */
    @Test
    public void test() {
        File file = new File("/Users/fanny/Qunar/Github/daily_test/pit_test/src/test/java/a.txt");
        String str = "webapp/health.html";
        System.out.println(writeIfNonPresent(file, str));
    }

    public static boolean writeIfNonPresent(File toWriteFile, String toWriteString) {
        if (!toWriteFile.exists()) {
            System.out.println("文件不存在！");
            return false;
        } else {
            try {
                List<String> lines = Files.readAllLines(Paths.get(toWriteFile.getAbsolutePath()));
                for (String line : lines) {
                    if (Objects.equals(line, toWriteString)) {
                        System.out.println("要输入的行已存在，跳过执行。");
                        return true;
                    }
                }
                BufferedWriter output = new BufferedWriter(new FileWriter(toWriteFile, true));
                output.write("\n" + toWriteString + "\n");
                output.close();
            } catch (IOException e) {
                System.out.println("写入文件失败！异常信息=>" + e.getMessage());
                e.printStackTrace();
            }
            return true;
        }
    }
}
