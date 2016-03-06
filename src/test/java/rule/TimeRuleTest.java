package rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Created by pan on 2016/3/5.
 */

public class TimeRuleTest {


    //该Rule会影响当前测试类中所有的测试用例。
    @Rule
    public Timeout globalTimeout = Timeout.millis(30);

    @Test
    public void testInfiniteLoop1() throws InterruptedException{
        Thread.sleep(10);
    }

    @Test
    public void testInfiniteLoop2() throws InterruptedException{
        Thread.sleep(10);
    }
}
