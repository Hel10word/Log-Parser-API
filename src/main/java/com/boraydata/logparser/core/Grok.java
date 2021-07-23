package com.boraydata.logparser.core;

//import com.boraydata.logparser.core.Converter.IConverter;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code Grok} parse arbitrary text and structure it.
 * <br>
 * {@code Grok} is simple API that allows you to easily parse logs
 * and other files (single line). With {@code Grok},
 * you can turn unstructured log and event data into structured data.
 *
 * @since 0.0.1
 */
public class Grok implements Serializable {
  /**
   * Named regex of the originalGrokPattern.
   */
  private final String namedRegex;
  /**
   * Map of the named regex of the originalGrokPattern
   * with id = namedregexid and value = namedregex.
   */
  private final Map<String, String> namedRegexCollection;
  /**
   * Original {@code Grok} pattern (expl: %{IP}).
   */
  private final String originalGrokPattern;
  /**
   * Pattern of the namedRegex.
   */
  private final Pattern compiledNamedRegex;

  /**
   * {@code Grok} patterns definition.
   */
  private final Map<String, String> grokPatternDefinition;

  public final Set<String> namedGroups;

//  public final Map<String, Converter.Type> groupTypes;
//  public final Map<String, IConverter<? extends Object>> converters;
  /**
   * {@code Grok} discovery.
   */
//  private Discovery disco;

  /** only use in grok discovery. */
  private String savedPattern = "";

  public Grok(String pattern, // 用户 传入 的 Grok 语法
      String namedRegex,    // 实际上传入的正则 表达式  (?<name0>\b(?:[1-9][0-9]*)\b?)(?<name1>(?<name2>..regx..?)(?<name3>..regx..?)?)
      Map<String, String> namedRegexCollection,   //  传入了 所有能匹配到的值;{name0=timestamp,name1=MONTH,name2=MONTHDAY}  *** 后来进行了 处理  只能接受小写
      Map<String, String> patternDefinitions,   // Grok 语法的 初步解析结果   {POSTINT}{IPORHOST=(?:%{IP}|%{HOSTNAME})}
      ZoneId defaultTimeZone) {
    this.originalGrokPattern = pattern;
    this.namedRegex = namedRegex;
    this.compiledNamedRegex = Pattern.compile(namedRegex);
    this.namedRegexCollection = namedRegexCollection;
    this.namedGroups = GrokUtils.getNameGroups(namedRegex);   // 正则中一个个  name0、name1、name2
//    this.groupTypes = Converter.getGroupTypes(namedRegexCollection.values());
//    this.converters = Converter.getConverters(namedRegexCollection.values(), defaultTimeZone);
    this.grokPatternDefinition = patternDefinitions;
//    System.out.println("this.namedRegex = "+namedRegex);
//    System.out.println("this.namedRegexCollection = "+namedRegexCollection);
//    System.out.println("grokPatternDefinition = "+patternDefinitions);
  }

  public String getSaved_pattern() {
    return savedPattern;
  }

  public void setSaved_pattern(String savedpattern) {
    this.savedPattern = savedpattern;
  }

  /**
   * Get the current map of {@code Grok} pattern.
   *
   * @return Patterns (name, regular expression)
   */
  public Map<String, String> getPatterns() {
    return grokPatternDefinition;
  }

  /**
   * Get the named regex from the {@code Grok} pattern. <br>
   * @return named regex
   */
  public String getNamedRegex() {
    return namedRegex;
  }

  /**
   * Original grok pattern used to compile to the named regex.
   *
   * @return String Original Grok pattern
   */
  public String getOriginalGrokPattern() {
    return originalGrokPattern;
  }

  /**
   * Get the named regex from the given id.
   *
   * @param id : named regex id
   * @return String of the named regex
   */
  public String getNamedRegexCollectionById(String id) {
    return namedRegexCollection.get(id);
  }

  /**
   * Get the full collection of the named regex.
   *
   * @return named RegexCollection
   */
  public Map<String, String> getNamedRegexCollection() {
    return namedRegexCollection;
  }

  /**
   * Match the given <tt>log</tt> with the named regex.
   * And return the json representation of the matched element
   *
   * @param log : log to match
   * @return map containing matches
   */
  public Map<String, Object> capture(String log) {
    Match match = match(log);
    return match.capture();
  }

  /**
   * Match the given list of <tt>log</tt> with the named regex
   * and return the list of json representation of the matched elements.
   *
   * @param logs : list of log
   * @return list of maps containing matches
   */
  public ArrayList<Map<String, Object>> capture(List<String> logs) {
    final ArrayList<Map<String, Object>> matched = new ArrayList<>();
    for (String log : logs) {
      matched.add(capture(log));
    }
    return matched;
  }

  /**
   * Match the given <tt>text</tt> with the named regex
   * {@code Grok} will extract data from the string and get an extence of {@link Match}.
   *
   * @param text : Single line of log
   * @return Grok Match
   */
  public Match match(CharSequence text) {
    if (compiledNamedRegex == null || text == null) {
      return Match.EMPTY;
    }

    Matcher matcher = compiledNamedRegex.matcher(text);
    if (matcher.find()) {
      return new Match(
          text, this, matcher, matcher.start(0), matcher.end(0)
      );
    }

    return Match.EMPTY;
  }

  /**
   * {@code Grok} will try to find the best expression that will match your input.
   * {@link Discovery}
   *
   * @param input : Single line of log
   * @return the Grok pattern
   */
//  public String discover(String input) {
//
//    if (disco == null) {
//      disco = new Discovery(this);
//    }
//    return disco.discover(input);
//  }
}
