package main;


import com.boraydata.logparser.parser.LogParser;
import com.boraydata.logparser.parser.LogParserFactory;
import com.boraydata.logparser.util.constant.ParserType;
import com.boraydata.logparser.util.constant.PatternConstant;
import entity.Log4jEntity;

import java.util.Map;

/** 对每个接口 进行 100W日志 的压测
 * @author bufan
 * @data 2021/7/15
 */
public class Main {


    public static void main(String[] args) {

        //使用 Stream 测试 不同日志文件的读取速率
//        FileParserTest_ForStreamAPI.testExample();

        // 使用现池的方式 压测 API
//      FileParserTest_ForThreadPool.runTask(1);

    }

}
