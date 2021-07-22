package main;


import com.boraydata.logparser.parser.LogParser;
import com.boraydata.logparser.parser.LogParserFactory;
import com.boraydata.logparser.util.constant.ParserType;
import entity.ApacheEntity;
import entity.Log4jEntity;
import entity.SyslogEntity;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/** 传入 文件路径  与  解析方法来 测试   使用 Stream API 来实现
 * @author bufan
 * @data 2021/7/15
 */
public class FileParserTest_ForStreamAPI {

    static String LOG4J_PATTERN = "%d{ISO8601} %style{%marker}{red} [%-22c{2}] %t %-5p: %m%n";
    static LogParser syslogParser = LogParserFactory.create(ParserType.Syslog);
    static LogParser apacheParser = LogParserFactory.create(ParserType.ApacheLog);
    static LogParser log4jParser = LogParserFactory.create(ParserType.Log4j,LOG4J_PATTERN);

    public static void testExample(){
        // 测试 Syslog
        FileParserTest_ForStreamAPI.fileParser("./sysLogTest", x->syslogParser.toJson(x));
        FileParserTest_ForStreamAPI.fileParser("./sysLogTest", x->syslogParser.toMap(x));
        FileParserTest_ForStreamAPI.fileParser("./sysLogTest",syslogParser, SyslogEntity.class);

        // 测试 Apache log
        FileParserTest_ForStreamAPI.fileParser("./ApacheLogTest", x->apacheParser.toJson(x));
        FileParserTest_ForStreamAPI.fileParser("./ApacheLogTest", x->apacheParser.toMap(x));
        FileParserTest_ForStreamAPI.fileParser("./ApacheLogTest",apacheParser, ApacheEntity.class);

        // 测试 Log4j
        FileParserTest_ForStreamAPI.fileParser("./Log4jTest", x->log4jParser.toJson(x));
        FileParserTest_ForStreamAPI.fileParser("./Log4jTest", x->log4jParser.toMap(x));
        FileParserTest_ForStreamAPI.fileParser("./Log4jTest",log4jParser, Log4jEntity.class);
    }

    public static void fileParser(String path, Function<String,Object> function) {
        try {
//            Method writeReplace = function.getClass().getDeclaredMethod("writeReplace");
//            Object invoke = writeReplace.invoke(function);
//            SerializedLambda serializedLambda = (SerializedLambda) invoke;
            long start = System.currentTimeMillis();

            // https://www.geeksforgeeks.org/what-is-java-parallel-streams/
//            Files.lines(Paths.get(path)).parallel()

            List<Object> list = Files.readAllLines(Paths.get(path)).parallelStream()
                    .map(constant -> function.apply(constant))
                    .collect(Collectors.toList());

            int index = list.size();
//            System.out.println("================测试文件："+path+"===============测试方法："+serializedLambda.getImplMethodName()+"===============================");
            System.out.println("================测试文件："+path+"==============================================");
            System.out.println("Processing is complete:："+index+" data");
            System.out.println("the "+index+" number of data：\n"+list.get(index-1));
            list.clear();
            list = null;
            long end = System.currentTimeMillis();
            System.out.println("Total time spent:" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.gc();
        }


    }

    public static <T> void fileParser(String path, LogParser parser,Class<T> c) {
        long start = System.currentTimeMillis();
        try {
            List<T> list = Files.readAllLines(Paths.get(path)).parallelStream()
                    .map(constant -> parser.toEntity(constant,c))
                    .collect(Collectors.toList());

            int index = list.size();
            System.out.println("================测试文件："+path+"===============测试方法：toEntity===============================");
            System.out.println("Processing is complete:："+index+" data");
            System.out.println("the "+index+" number of data：\n"+list.get(index-1).toString());
            list.clear();
            list = null;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.gc();
        }
        long end = System.currentTimeMillis();
        System.out.println("处理完用时:" + (end - start));
    }

}
