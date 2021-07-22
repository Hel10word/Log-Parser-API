package core;

import com.boraydata.logparser.core.Grok;
import com.boraydata.logparser.core.GrokCompiler;
import com.boraydata.logparser.util.constant.PatternConstant;
import org.junit.jupiter.api.Test;

import java.util.Map;

/** 用来测试 GrokCompiler
 * @author bufan
 * @data 2021/7/21
 */
public class GrokCompilerTest {

    @Test
    public void TestGrokPatternDefinitions(){
        GrokCompiler compiler = GrokCompiler.newInstance();
        compiler.registerDefaultPatterns();
        Grok grok = compiler.compile(PatternConstant.PATTEN_APACHE_LOG);

    }
}
