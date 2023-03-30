# 简易OpenAI提问工具

### 背景
最近使用ChatGPT网页版的免费版本，受制于免费版对于性能的限制，使用期限会产生频繁断线的问题，非常影响使用体验。了解到OpenAI开放的API有免费试用的额度，决定基于OpenAI的API写一个简单的基于终端的Java小程序，并解析返回结果写入markdown文件中来阅读答案。

### Features

- OpenAI接口相对稳定，但也存在服务端主动断开连接的可能，因此设置了请求的自动重试；
- 基于终端的标准输入输出，结果写入markdown文件中，解决代码块可读性问题；
- 由于使用的是OpenAI单次请求的接口，无法实现聊天内容联系上下文的功能。

### 使用方法

1. 在OpenAI官网申请Token；
2. Clone源码，并使用Maven打包成jar包；
3. 本地需要具备JDK1.8+的Java运行时环境；
4. 运行jar程序：

```bash
java -jar open_ai_test-1.0-SNAPSHOT.jar "你申请到的OpenAI Token" "答案想要保存到的本地目录（必须是已创建的目录）"
```

5. 退出程序命令：`exit`