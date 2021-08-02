package com.boraydata.logparser.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

/** 具体实现 请参考 AbstractMatch 抽象类
 * @author /** 继承了 /** 继承了
 * @data 2021/7/15
 */
public class ApachelogParser extends AbstractMatch {
    /**
     * 提供一个 格式化 Apache Log 日志的 API
     *
     * @Param time : Apache Log 中的日期  （ep："17/Nov/2020:20:33:13 +0800"）
     * @Return: LocalDateTime :   2020-11-17T20:33:13
     */
    @Override
    public LocalDateTime toLocalDateTime(String time) {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("d/MMM/yyyy:HH:mm:ss X")
                .toFormatter(Locale.ENGLISH);
        return LocalDateTime.parse(time, dateTimeFormatter);
    }
}
