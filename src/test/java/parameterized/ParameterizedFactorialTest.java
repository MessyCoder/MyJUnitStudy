package parameterized;

import com.packtpub.junit.recap.Factorial;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by pan on 2016/3/5.
 */

@RunWith(Parameterized.class)
public class ParameterizedFactorialTest {

    @Parameterized.Parameters
    public static Collection<Object[]> factorialData(){
        return Arrays.asList(new Object[][]{
                {0, 1}, {1,1}, {2,2}, {3,6}, {4,24}, {5, 120}, {6, 720}
        });
    }
    private int number;
    private int expectedResult;

    // 使用构造函数传递测试数据时要求参数的数量和类型都匹配
    public ParameterizedFactorialTest(int input, int expected) {
        number= input;
        expectedResult= expected;
    }

    @Test
    public void factorial(){
        Factorial factorial = new Factorial();
        assertEquals(expectedResult, factorial.factorial(number));
    }


}
