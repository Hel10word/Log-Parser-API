package com.boraydata.logparser.core;


import com.boraydata.logparser.util.MatchUtils;
import com.boraydata.logparser.exception.GrokException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;


/**
 * {@code Match} is a representation in {@code Grok} world of your log.
 *
 * @since 0.0.1
 */
/** Added some methods to format the output at the end,Optimized some methods
 * @描述:
 * @author: bufan
 * @date: 2021/7/15 22:12
 */
public class Match {
  private final CharSequence subject;
  private final Grok grok;
  private final Matcher match;
  private final int start;
  private final int end;
  private boolean keepEmptyCaptures = true;
  private Map<String, Object> capture = new ConcurrentHashMap<>();

  /**
   * Create a new {@code Match} object.
   */
  public Match(CharSequence subject, Grok grok, Matcher match, int start, int end) {
    this.subject = subject;
    this.grok = grok;
    this.match = match;
    this.start = start;
    this.end = end;
  }

  /**
   * Create Empty grok matcher.
   */
  public static final Match EMPTY = new Match("", null, null, 0, 0);

  public Matcher getMatch() {
    return match;
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

  /**
   * Ignore empty captures.
   */
  public void setKeepEmptyCaptures(boolean ignore) {
    // clear any cached captures
    if ( capture.size() > 0) {
      capture = new ConcurrentHashMap<String, Object>();
    }
    this.keepEmptyCaptures = ignore;
  }

  public boolean isKeepEmptyCaptures() {
    return this.keepEmptyCaptures;
  }

  /**
   * Retrurn the single line of log.
   *
   * @return the single line of log
   */
  public CharSequence getSubject() {
    return subject;
  }

  /**
   * Match to the <tt>subject</tt> the <tt>regex</tt> and save the matched element into a map.
   *
   * Multiple values for the same key are stored as list.
   *
   */
  public Map<String, Object> capture() {
    return capture(false);
  }


  /**
   * Private implementation of captureFlattened and capture.
   * @param flattened will it flatten values.
   * @return the matched elements.
   * @throws GrokException if a keys has multiple non-null values, but only if flattened is set to true.
   */
  /**
   * @描述: 将 处理的 Matcher 对象 提取出来 转为 Map 返回
   * @author: bufan
   * @date: 2021/7/15 22:35
   */
  private Map<String, Object> capture(boolean flattened ) throws GrokException {
    if (match == null) {
      return Collections.emptyMap();
    }

    // {name0=Jul  5 16:00:20, name1=Jul, name2=5, name3=16:00:20, name4=16, name5=00, name6=20, name7=boray01, name8=boray01}
    Map<String, String> mappedw = GrokUtils.namedGroups(this.match, this.grok.namedGroups);
    // {name0=timestamp,name7=hostname}
    Map<String, String> namedRegexCollection = this.grok.getNamedRegexCollection();
    Map<String,Object> map = new ConcurrentHashMap<>();
//    System.out.println("mappedw : "+mappedw);
//    System.out.println("namedRegexCollection : "+namedRegexCollection);
    for (String key : namedRegexCollection.keySet())
      map.put(namedRegexCollection.get(key),mappedw.get(key));
    Collections.unmodifiableMap(map);
    return map;
  }

  /**
   * remove from the string the quote and double quote.
   *
   * @param value string to pure: "my/text"
   * @return unquoted string: my/text
   */
  /** 优化了大佬写的  取首尾字符串判断的方法
   * @描述:
   * @author: bufan
   * @date: 2021/7/15 22:33
   */
  private String cleanString(String value) {
    if (value == null || value.isEmpty()) {
      return value;
    }

    char firstChar = value.charAt(0);
    char lastChar = value.charAt(value.length() - 1);
    if (firstChar == lastChar
            && (firstChar == '"' || firstChar == '\'')
    ) {
      if (value.length() <= 2) {
        return "";
      } else {
        int found = 0;
        for (int i = 1; i < value.length() - 1; i++ ) {
          if (value.charAt(i) == firstChar) {
            found++;
          }
        }
        if (found == 0) {
          return value.substring(1, value.length() - 1);
        }
      }
    }

    return value;
  }


  /**
   * Util fct.
   *
   * @return boolean
   */
  public Boolean isNull() {
    return this.match == null;
  }




///////////////////////////////////////////////////////////////////////////////////////////////////////////
  /** 增加一些自己的方法
   * @描述:
   * @author: bufan
   * @date: 2021/7/14 18:17
   */


  /** 数据返回为 Map
   * @描述: 这儿实际是 使用了大佬的方法  后续方法皆是 将 Map 对象  转为 JSON 或 POJO
   * @author: 凡
   * @date: 2021/7/13 11:28
   */
  public Map<String, Object> toMap() throws GrokException {
    return capture(true);
  }

  /** 筛选出指定键值 返回 Map
   * @描述:
   * @author: 凡
   * @date: 2021/7/13 11:29
   * @return
   */
  public Map<String, Object> toMap(String[] Keys){
    Map<String, Object> matchMap = capture(true);
    Map<String, Object> argMap = new LinkedHashMap<>();
    for (String key:Keys)
      argMap.put(key,
              matchMap.containsKey(key)?
                      matchMap.get(key):
//                                Optional.empty()
                      "UnKnown"
      );
    return argMap;
  }

  /** 将数据作为 JSON 返回
   * @描述:
   * @author: 凡
   * @date: 2021/7/13 11:28
   */
  public String toJson(){
    return MatchUtils.mapToJson(capture(true));
  }

  /** 筛选出指定键 返回JSON
   * @描述:
   * @author: 凡
   * @date: 2021/7/13 11:27
   */
  public String toJson(String[] Keys){
    return MatchUtils.mapToJson(toMap(Keys));
  }

  /** 根据传入的 Entity 封装对象 返回
   * @描述:
   * @author: 凡
   * @date: 2021/7/13 11:28
   */
  public <T> T toEntity(Class<T> c){
    try {
      Map<String, Object> map = capture(true);
      T t = c.newInstance();
      for (Field f:c.getDeclaredFields()){
        f.setAccessible(true);
        f.set(t,map.get(f.getName()));
      }
      return t;
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }



}
