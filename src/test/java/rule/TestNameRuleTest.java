package rule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;


import static org.junit.Assert.*;

/**
 * Created by pan on 2016/3/6.
 */
public class TestNameRuleTest {

    /**
     * TestName 用于在测试方法中提供与当前测试方法相关的名称。
     */
    @Rule
    public TestName name = new TestName();


    @Before
    public void prepare(){
        System.out.println(name.getMethodName());
    }


    @Test
    public void testA() {
        assertEquals("A", "A");
    }
    @Test
    public void testB() {
        assertEquals("B", "B");
    }


}
