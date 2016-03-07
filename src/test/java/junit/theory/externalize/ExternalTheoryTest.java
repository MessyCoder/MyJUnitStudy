package junit.theory.externalize;

import com.packtpub.junit.recap.Adder;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * 使用@ParametersSuppliedBy 可以将测试数据放置到测试类外部。
 * 但是 数据提供类往往要被初始化多次。
 */
@RunWith(Theories.class)
public class ExternalTheoryTest {

    @Theory
    public void adds_numbers(
            @ParametersSuppliedBy(NumberSupplier.class) Number num1,
            @ParametersSuppliedBy(NumberSupplier.class) Number num2)
    {
        Adder anAdder = new Adder();
        double expectedSum =
                num1.doubleValue()+num2.doubleValue();
        double actualResult = (Double)anAdder.add(num1, num2);
        assertEquals(actualResult, expectedSum, 0.01);
    }
}
