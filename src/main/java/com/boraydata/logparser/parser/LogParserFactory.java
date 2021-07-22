package com.boraydata.logparser.parser;


import com.boraydata.logparser.core.GrokCompiler;
import com.boraydata.logparser.core.Grok;
import com.boraydata.logparser.util.constant.ParserType;
import com.boraydata.logparser.util.constant.PatternConstant;
import com.boraydata.logparser.util.PatternUtils;

/** 通过工厂 来创建 不同的 Parser
 * @author bufan
 * @data 2021/7/15
 */
public class LogParserFactory {
    // 适合用来创建 规格化输出的 日志 例如 Syslog、Apache log
    public static LogParser create(ParserType parserType){
        if (parserType.equals(ParserType.Syslog)){
            GrokCompiler grokCompiler = GrokCompiler.newInstance();
            grokCompiler.registerDefaultPatterns();
            Grok grok = grokCompiler.compile(PatternConstant.PATTEN_SYSLOG);
            SyslogParser parser = new SyslogParser();
            parser.parser(grok);
            return parser;
        }else if(parserType.equals(ParserType.ApacheLog)){
            GrokCompiler grokCompiler = GrokCompiler.newInstance();
            grokCompiler.registerDefaultPatterns();
            Grok grok = grokCompiler.compile(PatternConstant.PATTEN_APACHE_LOG);
            ApachelogParser parser = new ApachelogParser();
            parser.parser(grok);
            return parser;
        }
        return null;
    }
    // 适合传入 日志的 输出表达式（Pattern） 来创建可以解析自定义的 Log4j Parser以及Log4j2 Parser 以及符合 Groke 语法的自定解析器
    public static LogParser create(ParserType parserType,String pattern){
        if (parserType.equals(ParserType.Log4j)){
            GrokCompiler grokCompiler = GrokCompiler.newInstance();
            grokCompiler.registerDefaultPatterns();
            // 由于 Log4j 常用于 输出 java 日志  可能需加载 默认的一些 支持java 的 正则
            grokCompiler.registerPatternFromClasspath("/patterns/java");
            Grok grok = grokCompiler.compile(PatternUtils.compile(pattern));
            Log4jParser parser = new Log4jParser();
            parser.parser(grok);
            return parser;
        }else if(parserType.equals(ParserType.CustomizeLog)){
            GrokCompiler grokCompiler = GrokCompiler.newInstance();
            // 为了支持 Groke 语法 默认加载 所有的配置
            grokCompiler.registerAllPatterns();
            Grok grok = grokCompiler.compile(pattern);
            AbstractMatch parser = new AbstractMatch();
            parser.parser(grok);
            return parser;
        }

        return null;
    }
}
