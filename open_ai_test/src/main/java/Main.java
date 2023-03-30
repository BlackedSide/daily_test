import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author jinpeng.fan
 * @date 2023/3/29 4:19 PM
 * description
 */
public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("输入的参数格式有误……");
            System.exit(0);
        }
        String token = args[0];
        String targetFolder = args[1];
        OpenAiService openAiService = new OpenAiService(token, targetFolder);
        System.out.println("欢迎使用BlackedSide出品OpenAI简易工具~");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            System.out.println("请输入你的问题：");
            while ((input = reader.readLine()) != null) {
                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("感谢使用~");
                    System.exit(0);
                }
                openAiService.generateResult(input);
                System.out.println("请输入你的问题：");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
