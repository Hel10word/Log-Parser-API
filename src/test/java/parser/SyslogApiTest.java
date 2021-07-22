package parser;

import com.boraydata.logparser.util.constant.ParserType;
import com.boraydata.logparser.util.constant.PatternConstant;
import com.boraydata.logparser.parser.LogParser;
import com.boraydata.logparser.parser.LogParserFactory;
import entity.SyslogEntity;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author bufan
 * @data 2021/7/15
 */
public class SyslogApiTest {
    String sysLog = "Jul  5 16:00:57 boray01 conmon[15913]: cluster 2021-07-05T16:00:56.793298+0000 mgr.boray01.eamgxr";
    String[] argsArray = new String[]{PatternConstant.LOG_TIME,"hostname","pid",PatternConstant.LOG_MESSAGE};

    static LogParser parser = LogParserFactory.create(ParserType.Syslog);

    @Test
    public void outJson(){
        System.out.println("----------------------------------------------  ToJSON");
        String jsons = parser.toJson(sysLog);
        System.out.println("===============  AllKay  ==============\n");
        System.out.println(jsons);

        String json = parser.toJson(sysLog, argsArray);
        System.out.println("===============  FilterKay  ==============\n");
        System.out.println(json);
    }

    @Test
    public void outMap(){
        System.out.println("----------------------------------------------  ToMap");
        Map<String, Object> maps = parser.toMap(sysLog);
        System.out.println("===============  AllKay  ==============\n");
        System.out.println(maps);

//        Map<String, Object> map = parser.toMap(sysLog, argsArray);
//        System.out.println("===============  FilterKay  ==============\n");
//        System.out.println(map);
    }

    /** SyslogEntity { timestamp、hostname、program、pid、message }
     * @描述: SyslogEntity.class
     * @author: bufan
     * @date: 2021/7/15 16:41
     */
    @Test
    public void outPOJO(){
        System.out.println("----------------------------------------------  ToPOJO");
        SyslogEntity syslogEntity = parser.toEntity(sysLog, SyslogEntity.class);
        System.out.println("===============  SyslogEntity  ==============\n");
        System.out.println(syslogEntity.toString());
    }
}
