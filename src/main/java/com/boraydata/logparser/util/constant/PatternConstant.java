package com.boraydata.logparser.util.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/** Some rules that conform to grok grammar expressions and tools
 * @author bufan
 * @data 2021/7/14
 */
public class PatternConstant {

    public static final String LOG_TIME = "timestamp";
    public static final String LOG_IP = "clientip";
    public static final String LOG_URL = "url";
    public static final String LOG_MESSAGE = "message";
    public static final String LOG_LEVE = "logleve";
    public static final String CLASSNAME = "classname";
    public static final String METHOD = "method";


    //  "Jul  5 16:00:20 boray01 systemd[1]: logrotate.service: Failed with result 'exit-code'.\n"
    //  适配 syslog 的匹配                                            时间                     主机名             进程                  进程号                 输出消息
    public static final String PATTEN_SYSLOG = "%{SYSLOGTIMESTAMP:"+LOG_TIME+"} %{SYSLOGHOST:hostname} %{DATA:program}(?:\\[%{POSINT:pid}\\])?:%{GREEDYDATA:"+LOG_MESSAGE+"}";

    //  "192.168.64.101 - - [17/Nov/2020:20:33:13 +0800] \"GET / HTTP/1.1\" 200 11216\n"
    //  适配 Apache 的匹配                                   请求地址                email       用户                  时间                      方法          url地址       协议/版本               响应结果                字节数
    public static final String PATTEN_APACHE_LOG = "%{IPORHOST:"+LOG_IP+"} %{DATA:email} %{DATA:auth} \\[%{HTTPDATE:"+LOG_TIME+"}\\] \\\"%{WORD:"+METHOD+"} %{DATA:"+LOG_URL+"} %{DATA:httpversion}\\\" %{NUMBER:response} %{NUMBER:bytes}";



    public static Map<String,String> logReplaceInfo = new LinkedHashMap<>();
    static {
        logReplaceInfo.put("\\|","\\\\|");
        logReplaceInfo.put("\\[","\\\\[");
        logReplaceInfo.put("\\]","\\\\]");
        logReplaceInfo.put("\\\"","\\\\\"");
        logReplaceInfo.put("\\(","\\\\(");
        logReplaceInfo.put("\\)","\\\\)");
        logReplaceInfo.put("%%","\\%");
        logReplaceInfo.put("%style","");
        // 去除格式为 “{***}“  的内容
        logReplaceInfo.put("\\{[^\\{^\\}]*\\}", "");
        //替换 格式为 “%-22c”、“%20.30c”、"%.5c"、"%5c"   为   %c
        logReplaceInfo.put("%([0-9.-]+)", "\\\\s*%");
        logReplaceInfo.put("%m","%{GREEDYDATA:"+LOG_MESSAGE+"}");
        logReplaceInfo.put("%p","%{DATA:"+LOG_LEVE+"}\\\\s*");
        logReplaceInfo.put("%r","%{DATA:logtime}");
        logReplaceInfo.put("%c","%{GREEDYDATA:"+CLASSNAME+"}\\\\s*");
        logReplaceInfo.put("%d","%{TIMESTAMP_ISO8601:"+LOG_TIME+"}");
        logReplaceInfo.put("%l","%{DATA:loginfo}");
        logReplaceInfo.put("%t","%{DATA:threadname}");
        logReplaceInfo.put("%F","%{DATA:filename}");
        logReplaceInfo.put("%L","%{DATA:linenumber}");
        logReplaceInfo.put("%n","");
        logReplaceInfo.put("\\s{2,20}", "\\\\s+");
        logReplaceInfo.put(" ","\\\\s");
    }

    /**
     //    %{TIMESTAMP_ISO8601:date}\s+\[%{DATA:classname}\]\s+%{DATA:class}\s+%{DATA:logleve}
     #Log4J采用类似C语言中的printf函数的打印格式格式化日志信息，打印参数如下：
     #%m 输出代码中指定的消息                                                               %{DATA:message}
     #%p 输出优先级，即DEBUG,INFO,WARN,ERROR,FATAL                                         %{DATA:logleve}
     #%r 输出自应用启动到输出该log信息耗费的毫秒数                                             %{DATA:logtime}
     #%c 输出所属的类目,通常就是所在类的全名                                                  %{DATA:classname}
     #%t 输出产生该日志事件的线程名                                                          %{DATA:threadname}
     #%n 输出一个回车换行符，Windows平台为“\r\n”，Unix平台为“\n”
     #%d 输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式                     %{TIMESTAMP_ISO8601:timestamp}
     #    如：%d{yyyy年MM月dd日HH:mm:ss,SSS}，输出类似：2012年01月05日 22:10:28,921
     #%l 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数                         %{DATA:loginfo}
     #    如：Testlog.main(TestLog.java:10)
     #%F 输出日志消息产生时所在的文件名称                                                      %{DATA:filename}
     #%L 输出代码中的行号                                                                   %{DATA:linenumber}
     #%x 输出和当前线程相关联的NDC(嵌套诊断环境),像javaservlets多客户多线程的应用中
     #%% 输出一个"%"字符                                                                   \%
     #
     # 可以在%与模式字符之间加上修饰符来控制其最小宽度、最大宽度、和文本的对齐方式。如：
     #  %5c: 输出category名称，最小宽度是5，category<5，默认的情况下右对齐
     #  %-5c:输出category名称，最小宽度是5，category<5，"-"号指定左对齐,会有空格
     #  %.5c:输出category名称，最大宽度是5，category>5，就会将左边多出的字符截掉，<5不会有空格
     #  %20.30c:category名称<20补空格，并且右对齐，>30字符，就从左边交远销出的字符截掉
     */



}
