package rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by pan on 2016/3/5.
 */
public class ExpectedExceptionRuleTest {

    /**
     * ExpectedException.none(); 返回一个表示不期待异常抛出的Rule。
     *
     * 在单元测试中, 通过调用ExpectedException.expect()方法来设定预期的异常。
     * 通过调用ExpectedException.expectMessage()来设置异常中预期的信息。(该特性是与@Test(expected=Exception class)的不同之处)
     * 这些设置方法会在单元测试结束后重置。
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwsNothing() {
    }

    @Test
    public void throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        throw new NullPointerException();
    }

    @Test
    public void throwsIllegalStateExceptionWithMessage() {
        thrown.expect(IllegalStateException.class);

        //期待 抛出的异常中 “含有” 一段消息
        thrown.expectMessage("Is this a legal state?");
        throw new IllegalStateException("Is this a legal state?");
    }
}
