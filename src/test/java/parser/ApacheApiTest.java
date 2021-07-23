package parser;

import com.boraydata.logparser.util.constant.ParserType;
import com.boraydata.logparser.util.constant.PatternConstant;
import com.boraydata.logparser.parser.LogParser;
import com.boraydata.logparser.parser.LogParserFactory;
import entity.ApacheEntity;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author bufan
 * @data 2021/7/15
 */
public class ApacheApiTest {

    String apacheLog = "192.168.64.101 - - [17/Nov/2020:20:33:13 +0800] \"GET /favicon.ico HTTP/1.1\" 200 21630";
    String[] argsArray = new String[]{PatternConstant.LOG_TIME,PatternConstant.LOG_IP,PatternConstant.METHOD,PatternConstant.LOG_URL};

    static LogParser parser = LogParserFactory.create(ParserType.ApacheLog);

    @Test
    public void outJson(){
        System.out.println("----------------------------------------------  ToJSON");
        String jsons = parser.toJson(apacheLog);
        System.out.println("===============  AllKay  ==============\n");
        System.out.println(jsons);

        String json = parser.toJson(apacheLog, argsArray);
        System.out.println("===============  FilterKay  ==============\n");
        System.out.println(json);
    }

    @Test
    public void outMap(){
        System.out.println("----------------------------------------------  ToMap");
        Map<String, Object> maps = parser.toMap(apacheLog);
        System.out.println("===============  AllKay  ==============\n");
        System.out.println(maps);

        Map<String, Object> map = parser.toMap(apacheLog, argsArray);
        System.out.println("===============  FilterKay  ==============\n");
        System.out.println(map);
    }


    /** ApacheEntity { clientip、timestamp、method、url、httpversion、response、bytes }
     * @描述: ApacheEntity.class
     * @author: bufan
     * @date: 2021/7/15 16:41
     */
    @Test
    public void outPOJO(){
        System.out.println("----------------------------------------------  ToPOJO");
        ApacheEntity apacheEntity = parser.toEntity(apacheLog, ApacheEntity.class);
        System.out.println("===============  ApacheEntity  ==============\n");
        System.out.println(apacheEntity.toString());
    }
}
