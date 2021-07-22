package TestLogSource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author 凡
 * @data 2021/7/15
 */
public class Create_100W_Log4j {

    public static void main(String[] args) {
        String path = "./Log4jTest";

        File file=new File(path);
        try (BufferedWriter br = file.exists()?
                //表示不续写原文件
                new BufferedWriter(new FileWriter(file,false))
                //创建写出流对象
                :new BufferedWriter(new FileWriter(path))) {
//        写入 数据
            for (int i = 0 ;i<50_000;i++)
                br.write(LOG4J_STR);
        }catch (Exception e){}
        System.out.println("文件创建成功");
    }
// 20条 Log4j2 记录   pattern：%d{ISO8601} %style{%marker}{red} [%-22c{2}] %t %-5p: %m%n
    final static String LOG4J_STR =
        "2021-07-12T16:14:12,920  [operation.CachingOperationNameGenerator] main INFO : Generating unique operation named: postUserUsingPOST_3\n" +
        "2021-07-12T16:14:12,922  [operation.CachingOperationNameGenerator] main INFO : Generating unique operation named: deleteBlackListUsingDELETE_1\n" +
        "2021-07-12T16:14:12,925  [operation.CachingOperationNameGenerator] main INFO : Generating unique operation named: getAllUsingGET_1\n" +
        "2021-07-12T16:14:12,926  [operation.CachingOperationNameGenerator] main INFO : Generating unique operation named: postBlackListUsingPOST_1\n" +
        "2021-07-12T16:14:12,976  [routeradmin.Application] main INFO : Started Application in 6.789 seconds (JVM running for 9.584)\n" +
        "2021-07-12T16:16:09,361  [[localhost].[/]       ] http-nio-8090-exec-1 INFO : Initializing Spring DispatcherServlet 'dispatcherServlet'\n" +
        "2021-07-12T16:16:09,361  [servlet.DispatcherServlet] http-nio-8090-exec-1 INFO : Initializing Servlet 'dispatcherServlet'\n" +
        "2021-07-12T16:16:09,363  [servlet.DispatcherServlet] http-nio-8090-exec-1 INFO : Completed initialization in 2 ms\n" +
        "2021-07-12T16:16:09,653  [util.DriverDataSource ] http-nio-8090-exec-1 WARN : Registered driver with driverClassName=com.mysql.jdbc.Driver was not found, trying direct instantiation.\n" +
        "2021-07-12T16:16:11,558  [filter.JWTAuthenticationFilter] http-nio-8090-exec-1 INFO : 登录成功返回信息写入成功\n" +
        "2021-07-12T16:17:10,960  [concurrent.ThreadPoolTaskExecutor] SpringContextShutdownHook INFO : Shutting down ExecutorService 'applicationTaskExecutor'\n" +
        "2021-07-12T17:14:49,504  [routeradmin.Application] main INFO : Starting Application using Java 1.8.0_291 on DESKTOP-8PG5B9A with PID 4620 (D:\\git_project\\router\\RouterAdmin\\target\\classes started by jin in D:\\git_project\\router)\n" +
        "2021-07-12T17:14:49,513  [routeradmin.Application] main INFO : No active profile set, falling back to default profiles: default\n" +
        "2021-07-12T17:14:50,784  [support.PostProcessorRegistrationDelegate$BeanPostProcessorChecker] main INFO : Bean 'org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler@ebd06a9' of type [org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)\n" +
        "2021-07-12T17:14:50,789  [support.PostProcessorRegistrationDelegate$BeanPostProcessorChecker] main INFO : Bean 'methodSecurityMetadataSource' of type [org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)\n" +
        "2021-07-12T17:14:51,037  [tomcat.TomcatWebServer] main INFO : Tomcat initialized with port(s): 8090 (http)\n" +
        "2021-07-12T17:14:51,048  [http11.Http11NioProtocol] main INFO : Initializing ProtocolHandler [\"http-nio-8090\"]\n" +
        "2021-07-12T17:14:51,049  [core.StandardService  ] main INFO : Starting service [Tomcat]\n" +
        "2021-07-12T17:14:51,049  [core.StandardEngine   ] main INFO : Starting Servlet engine: [Apache Tomcat/9.0.44]\n" +
        "2021-07-12T17:14:51,227  [[localhost].[/]       ] main INFO : Initializing Spring embedded WebApplicationContext\n";
}
