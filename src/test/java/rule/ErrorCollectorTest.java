package rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.*;

/**
 * Created by pan on 2016/3/5.
 */
public class ErrorCollectorTest {

    /**
     * ErrorCollector 使得测试方法在途中失败时可以继续执行完毕，以收集所有可能的错误。
     */
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void fails_after_execution() {
        collector.checkThat("a", equalTo("b"));
        collector.checkThat(1, equalTo(2));
        collector.checkThat("ae", equalTo("g"));
    }
}
