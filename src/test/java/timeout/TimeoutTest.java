package timeout;

import org.junit.Test;

/**
 * Created by pan on 2016/3/5.
 */
public class TimeoutTest {

    @Test(timeout = 150)
    public void testTimeout() throws Exception{
        //Junit不会等到真的执行了这么长时间才会报告其失败。
        Thread.sleep(10000);
    }
}
