package com.boraydata.logparser.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/** 具体实现 请参考 AbstractMatch 抽象类
 * @author bufan
 * @data 2021/7/15
 */
public class SyslogParser extends AbstractMatch {

    /**
     * 提供一个 格式化 SysLog 日志的 API
     *
     * @Param time : SysLog 中的日期  （ep：Jul 5 16:00:20）
     * @Param year : 由于 SysLog 默认是没有提供年份的 因此要设置日期对应的 年份    （ep：2021）
     * @Return: LocalDateTime :   2021-07-05T16:00:20
     */
    @Override
    public LocalDateTime toLocalDateTime(String time, long year) {
            DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                    .appendPattern("MMM d HH:mm:ss")
                    .parseDefaulting(ChronoField.YEAR,year)
                    .toFormatter(Locale.ENGLISH);
//        String time = "Jul 5 16:00:20";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd HH:mm:ss", Locale.ENGLISH);
//        Date parse = simpleDateFormat.parse(time);
            return LocalDateTime.parse(time, dateTimeFormatter);
    }
}
