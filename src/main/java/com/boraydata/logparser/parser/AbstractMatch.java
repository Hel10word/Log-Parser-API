package com.boraydata.logparser.parser;

import com.boraydata.logparser.core.Match;
import com.boraydata.logparser.core.Grok;
import java.util.Map;

/** 用来  将 Grok 的 转换结果 Match 对象 转换为不同 格式
 * @author bufan
 * @data 2021/7/15
 */
public class AbstractMatch implements LogParser {

    // 当前的解析器 Grok 对象  每个一 Grok 对象包含了 当前解析器预处理的正则表达式
    private Grok grok;
    // 通过 Grok.match(Str) 利用配置的正则表达式  将字符串 处理为 Match 对象
    private Match match;

    /**
     * 初始化 当前的 Grok 对象
     */
    public void parser(Grok grok) {
        this.grok = grok;
    }

    /**
     * 通过 grok.match() 将用户传入的字符串 转为 可以被我们的多元化输出的 Match 对象
     */
    private Match parseLog(String content) {

        if(this.match == null||this.match.isNull())
            this.match = this.grok.match(content);
        return this.match;
    }

    /**
     * 将结果 转为 json 字符串
     * @Param content : "Jul  5 16:00:57 boray01 conmon[15913]"
     * @Return: String : {"timestamp":"Jul  5 16:00:57","hostname":"boray01","program":"conmon","pid":"15913"}
     */
    @Override
    public String toJson(String content) {
        return parseLog(content).toJson();
    }

    /**
     * 将结果 转为 经过筛选的 json 字符串
     * @Param content : "Jul  5 16:00:57 boray01 conmon[15913]"
     * @Param args : {"program","pid"}
     * @Return: String : {"program":"conmon","pid":"15913"}
     */
    @Override
    public String toJson(String content, String[] args) {
        return parseLog(content).toJson(args);
    }

    /**
     * 将结果 转为 Map
     * @Param content : "Jul  5 16:00:57 boray01 conmon[15913]"
     * @Return: Map : {timestamp=Jul  5 16:00:57, hostname=boray01, program=conmon, pid=15913}
     */
    @Override
    public Map<String, Object> toMap(String content) {
//        System.out.println("调用了  function");
        return parseLog(content).toMap();
    }

    /**
     * 将结果 转为 经过筛选的 Map
     * @Param content : "Jul  5 16:00:57 boray01 conmon[15913]"
     * @Param args : {"program","pid"}
     * @Return: Map : {program=conmon, pid=15913}
     */
    @Override
    public Map<String, Object> toMap(String content, String[] args) {
        return parseLog(content).toMap(args);
    }

    /**
     * 将结果 封装为 POJO    注意： POJO中的对象名 需要与 解析结果中有的一致  可以先测试 单条日志 来查看有哪些相关属性
     * 例如： SyslogEntity { program、pid }
     * @Param content : "Jul  5 16:00:57 boray01 conmon[15913]"
     * @Param c : SyslogEntity.class
     * @Return: T : SyslogEntity(program=conmon, pid=15913)
     */
    @Override
    public <T> T toEntity(String content, Class<T> c) {
        return parseLog(content).toEntity(c);
    }
}
