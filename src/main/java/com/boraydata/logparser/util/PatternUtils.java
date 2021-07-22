package com.boraydata.logparser.util;

import com.boraydata.logparser.util.constant.PatternConstant;

/** 用来处理 用户传入的 Log4j 的配置信息   将 Pattern 表达式替换 Grok 语法
 * Log4j 语法：https://logging.apache.org/log4j/2.x/manual/layouts.html#PatternLayout
 * 可以 查看看   com.boraydata.logparser.constant.PatternConstant  其中的 注释
 * Grok 语法：https://www.elastic.co/guide/en/logstash/current/plugins-filters-grok.html
 * Grok 测试：https://grokdebug.herokuapp.com/
 * @author bufan
 * @data 2021/7/15
 */
public class PatternUtils {
    public static String compile(String logPattern) {
        String pattern = logPattern;
        for(String key : PatternConstant.logReplaceInfo.keySet()){
            pattern = pattern.replaceAll(key, PatternConstant.logReplaceInfo.get(key));
        }
        return pattern;
    }
}
