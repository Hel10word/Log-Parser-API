package parser;

import com.boraydata.logparser.util.constant.ParserType;
import com.boraydata.logparser.util.constant.PatternConstant;
import com.boraydata.logparser.parser.LogParser;
import com.boraydata.logparser.parser.LogParserFactory;
import entity.Log4jEntity;
import entity.SyslogEntity;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author bufan
 * @data 2021/7/15
 */
public class Log4jApiTest {
    String Log4jStr_One = "2021-07-12T16:16:09,361  [[localhost].[/]       ] http-nio-8090-exec-1 INFO : Initializing Spring DispatcherServlet 'dispatcherServlet'";
    String Log4jPatter_One = "%d{ISO8601} %style{%marker}{red} [%-22c{2}] %t %-5p: %m%n";

    String Log4jStr_Two = "2021-02-10 17:34:52,753|INFO |test.TestLog4j|main|gogogo";
    String Log4jPatter_Two = "%d{ISO8601}|%-5p|%c|%t|%m%n";

    String Log4j2Str_One = "2021-07-14 13:27:05 [INFO] com.zbf.log.Main: log4j2";
    String Log4j2Patter_One = "%d{yyyy-MM-dd HH:mm:ss} [%p] %c: %m%n";

    String[] argsArray = new String[]{PatternConstant.LOG_TIME,PatternConstant.LOG_LEVE,PatternConstant.CLASSNAME,PatternConstant.LOG_MESSAGE};

    @Test
    public void outJson(){
        System.out.println("----------------------------------------------  ToJSON");
        LogParser parser = LogParserFactory.create(ParserType.Log4j,Log4jPatter_One);
        String jsons = parser.toJson(Log4jStr_One);
        System.out.println("===============  AllKay  ==============\n");
        System.out.println(jsons);

        String json = parser.toJson(Log4jStr_One, argsArray);
        System.out.println("===============  FilterKay  ==============\n");
        System.out.println(json);
    }
    @Test
    public void outMap(){
        System.out.println("----------------------------------------------  ToMap");
        LogParser parser = LogParserFactory.create(ParserType.Log4j,Log4jPatter_Two);
        String jsons = parser.toJson(Log4jStr_Two);
        System.out.println("===============  AllKay  ==============\n");
        System.out.println(jsons);

        String json = parser.toJson(Log4jStr_Two, argsArray);
        System.out.println("\n===============  FilterKay  ==============\n");
        System.out.println(json);
    }

    /** Log4jEntity { classname、logleve、message、timestamp }
     * @描述:  Log4jEntity 字段介绍
     * @author: bufan
     * @date: 2021/7/15 21:03
     */
    @Test
    public void outPOJO(){
        System.out.println("----------------------------------------------  ToPOJO");
        LogParser parser = LogParserFactory.create(ParserType.Log4j,Log4j2Patter_One);
        Log4jEntity log4jEntity = parser.toEntity(Log4j2Str_One, Log4jEntity.class);
        System.out.println("===============  SyslogEntity  ==============\n");
        System.out.println(log4jEntity.toString());
    }


}
