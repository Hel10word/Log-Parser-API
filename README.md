# Log Parser

一个用于解析各种格式日志的API，现在仅支持单行日志解析，可以解析成多种格式。

- 支持Syslog、Apache Log 等标准格式的日志解析。
- 支持 Log4j与Log4j2格式的日志，并根据用户传入的 `Pattern` 解析。
- 提供 Json、Map、POJO等格式的输出，并能指定筛选条件。



# Using

- git clone project 后 通过  **`mvn install`**  将其安装到本地 Maven Repository 然后便可通过 如下配置使用

Maven：

```xml
<dependency>
    <groupId>com.boraydata.logparser</groupId>
    <artifactId>Log-Parser</artifactId>
    <version>0.1</version>
</dependency>
```

- 也可以下载 Jar 包,导入 lib 中使用

# Example

1. 通过 `LogParserFactory` **构造一个Parser**，`ParserType`中枚举了一些支持的日志格式。


```java
LogParser parser = LogParserFactory.create(ParserType.Syslog);
```


2. 通过 Parser 解析 日志，输出为多种格式。

```java
String json = parser.toJson(sysLog);
```

```java
Map<String, Object> map = parser.toMap(sysLog);
```

```java
SyslogEntity syslogEntity = parser.toEntity(sysLog, SyslogEntity.class);
```



**仅需两步，便能完成日志的解析。**

[API 方法介绍](#features)




---

- Syslog Example

```java
String sysLog = "Jul  5 16:00:57 boray01 conmon[15913]: cluster 2021-07-05T16:00:56.793298+0000 mgr.boray01.eamgxr";
String[] argsArray = new String[]{PatternConstant.LOG_TIME,"hostname","pid",PatternConstant.LOG_MESSAGE};

LogParser parser = LogParserFactory.create(ParserType.Syslog);

String jsons = parser.toJson(sysLog);
String json = parser.toJson(sysLog,argsArray);

System.out.println(jsons);
System.out.println(json);

```
out：

```json
{"timestamp":"Jul  5 16:00:57","MONTH":"Jul","MONTHDAY":"5","TIME":"16:00:57","HOUR":"16","MINUTE":"00","SECOND":"57","hostname":"boray01","IPORHOST":"boray01","program":"conmon","pid":"15913","message":" cluster 2021-07-05T16:00:56.793298+0000 mgr.boray01.eamgxr"}
{"timestamp":"Jul  5 16:00:57","hostname":"boray01","pid":"15913","message":" cluster 2021-07-05T16:00:56.793298+0000 mgr.boray01.eamgxr"}
```



- Log4j Example

```java
String Log4jStr = "2021-07-14 13:27:05 [INFO] com.zbf.log.Main: log4j2";
String Log4jPatter = "%d{yyyy-MM-dd HH:mm:ss} [%p] %c: %m%n";

LogParser parser = LogParserFactory.create(ParserType.Log4j,Log4jPatter);

Map<String, Object> map = parser.toMap(Log4jStr);
Log4jEntity log4jEntity = parser.toEntity(Log4jStr, Log4jEntity.class);

System.out.println(map);
System.out.println(log4jEntity.toString());
```

out：

```json
{timestamp=2021-07-14 13:27:05, YEAR=2021, MONTHNUM=07, MONTHDAY=14, HOUR=13, MINUTE=27, SECOND=05, ISO8601_TIMEZONE=null, logleve=INFO, classname=com.zbf.log.Main, message=log4j2}
Log4jEntity(classname=com.zbf.log.Main, logleve=INFO, message=log4j2, timestamp=2021-07-14 13:27:05)
```



# Attention

1. 创建好的 Parser 只能处理一种类型的日志，如果要处理其他类型，请创建新的 Parser。
2. 创建 Log4j 格式的 Parser 时，**请传入配置 Log4j 日志的 Pattern 表达式！！！！！**



# Features

## 创建 Parser 方法

|                             | 创建一个 Log Parser                                          |
| --------------------------- | ------------------------------------------------------------ |
| Syslog 对应的 Parser        | `LogParserFactory.create(ParserType.Syslog)`                 |
| Apache log 对应的 Parser    | `LogParserFactory.create(ParserType.ApacheLog)`              |
| Log4j、Log4j2 对应的 Parser | `LogParserFactory.create(ParserType.Log4j,pattern)`          |
| **额外支持**                | **请自行指定 Grok 表达式**                                   |
| **支持 Grok 语法的 Parser** | **`LogParserFactory.create(ParserType.CustomizeLog,pattern)`** |

- 最后一个 Parser 的功能与 ELK 中 Logstash 使用的 Grok 插件功能一致，创建该 Parser 请自行准备并检验 Grok 表达式，创建好的 Parser 与其他使用无异。
	- [Grok 语法官网介绍](https://www.elastic.co/guide/en/logstash/current/plugins-filters-grok.html)
	- [Grok 语法在线检验](tps://grokdebug.herokuapp.com/)



## Parser 结果输出

这儿为了展示不同的结果，只截取了部分输入与输出内容；**实际使用时，请传入完整的日志格式！！！！！**

- `String logStr = "boray01 conmon[15913]";`
- `String[] args = new String[]{"program","pid"};`
- `Entity.class 中包含的字段: {String program、String pid }`

| 方法名                      | 传入参数                 | 输出结果                                                  | 结果类型              |
| --------------------------- | ------------------------ | --------------------------------------------------------- | --------------------- |
| `toJson(String)`            | `logStr`                 | `{"hostname":"boray01","program":"conmon","pid":"15913"}` | `String`              |
| `toJson(String, String[])`  | `logStr` ,`args`         | `{"program":"conmon","pid":"15913"}`                      | `String`              |
| `toMap(String)`             | `logStr`                 | `{hostname=boray01, program=conmon, pid=15913}`           | `Map<String, Object>` |
| `toMap(String, String[])`   | `logStr` ,`args`         | `{program=conmon, pid=15913}`                             | `Map<String, Object>` |
| `toEntity(String,Class<T>)` | `logStr` ,`Entity.class` | `SyslogEntity(program=conmon, pid=15913)`                 | T                     |



- 由于是先将 Matcher 中的匹配结果取出存为 Map 结构，然后 toJson（基与`jackson-databind`依赖）、toEntity（通过反射的方式，创建传入的POJO，然后将值写入并返回）这两种都是基于先有了Map数据后的二次输出格式。**因此转换效率为：toMap() > toEntity() > toJson()**

# Other



> 本项目是基与 [java-grok](https://github.com/thekrakken/java-grok) 二次开发而来，由于作者，长时间未更新，于是我借来二次开发与完善。



- [任务与总结](./任务与总结.md)：这是做这个API的动机，收到该任务，于是进行了任务的分析、资料的整理，到最后的需求落地，虽然自己经验不足，但是觉得这次收获良多，于是发上来，希望自己以后能砥砺前行。加油
- [java-grok分析](./java-grok源码解析.md)：对Java-Grok API 进行了解析流程的分析，与个人总结。



### 项目结构介绍：

### `main` 目录中

- **core** 包 ：主要用来处理Grok语法，将其转为相应的正则表达式，然后通过Java正则API中的 `Pattern` 将字符串 解析为 `Matcher` ，并用自定义的Match对象对结果进行解析与封装，使结果多元化输出。
- **exception** 包 ：用来处理异常
- parser 包 ：使用工厂模式来规格化API
	1. `LogParser` : 定义了Parser中常用的方法
	2. `AbstractMatch` : 将一些常用的操作 抽取成抽象类
	3. `LogParserFactory` : 用来负责响应Parser的初始化工作
- **util** 包 ：用来处理 Log4j 的 Pattern 表达式、列举已经支持的 Parser、定义一些常量，方便使用



### `resources/patterns` 目录中

- 包含了一些常用的正则表达式，可以理解为 Grok 语法的底层支持，具体参考请参见 [patterns](https://github.com/elastic/logstash/tree/v1.4.2/patterns)



### `test` 目录中

由于这个是 测试包，只介绍一部分，包含了各个接口的测试用例，创建 100w 的日志文件等，使用API时可以当作参考。

- **core** 包：分析源码时对 各个类进行的测试

- **entity** 包 ：用于 `toEntity` 输出，定义了一些日志的 POJO；
- **parser** 包 ：包含了不同 Parser 的所有输出结果的测试，测试了 3 种 Log4j 格式的日志。
- **TestLogSource** 包 ：根据相关数据，指定目录，生成 100W 的日志文件。
- **main** 包 ：用来测试，指定目录的日志文件，提供使用 Stream 并行流 的方式，进行测试；也提供基于线程池的方式测试。

