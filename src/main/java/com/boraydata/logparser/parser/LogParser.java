package com.boraydata.logparser.parser;

import java.time.LocalDateTime;
import java.util.Map;

/** 不同 Parser 需要实现的接口
 * @author bufan
 * @data 2021/7/15
 */
public interface LogParser {

    public String toJson(String content);
    public String toJson(String content, String[] args);
    public Map<String, Object> toMap(String content);
    public Map<String, Object> toMap(String content, String[] args);
    public <T> T toEntity(String content, Class<T> c);
    public LocalDateTime toLocalDateTime(String time);
    public LocalDateTime toLocalDateTime(String time,long year);

}
