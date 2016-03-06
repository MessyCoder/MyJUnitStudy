package rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Verifier;

import static org.junit.Assert.*;

/**
 * Created by pan on 2016/3/5.
 */
public class VerifierRuleTest {
    private String errorMsg = null;

    /**
     * Verifier 是一个抽象类，（它还是ErrorCollector的父类）
     *
     * 它可以在每一个测试方法结束之后进行统一的再一次测试，如果测试失败，则之前成功的测试被认定为失败。
     * 如果测试方法本身就已经失败，则Verifier不会再进行测试。
     *
     */
    @Rule
    public TestRule rule = new Verifier() {
        protected void verify() {
            assertNull("ErrorMsg should be null after each test execution", errorMsg);
        }
    };

    @Test
    public void testName() throws Exception {
        errorMsg = "Giving a value";
    }
}
