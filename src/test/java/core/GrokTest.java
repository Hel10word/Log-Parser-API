package core;

import com.boraydata.logparser.core.Grok;
import com.boraydata.logparser.core.GrokCompiler;
import com.boraydata.logparser.core.Match;
import com.boraydata.logparser.util.constant.PatternConstant;

/** 测试 Grok
 * @author bufan
 * @data 2021/7/19
 */
public class GrokTest {
    public void showGrokParameter(){
        GrokCompiler grokCompiler = GrokCompiler.newInstance();
        grokCompiler.registerDefaultPatterns();
        Grok grok = grokCompiler.compile(PatternConstant.PATTEN_SYSLOG);
        Match match = grok.match("");

    }
}
