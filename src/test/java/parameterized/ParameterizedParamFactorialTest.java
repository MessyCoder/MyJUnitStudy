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
public class ParameterizedParamFactorialTest {
    @Parameterized.Parameters
    public static Collection<Object[]> factorialData(){
        return Arrays.asList(new Object[][]{
                {0,1}, {1,1}, {2,2}, {3,6}, {4,24}, {5, 120}, {6, 720}
        });
    }

    //不使用构造函数传递测试数据的时候，需要在成员变量上标注@Parameter
    //只能对公开的成员变量上进行标注。
    @Parameterized.Parameter(value=0)
    public int number;

    @Parameterized.Parameter(value=1)
    public int expectedResult;

    @Test
    public void factorial(){
        Factorial factorial = new Factorial();
        assertEquals(expectedResult, factorial.factorial(number));
    }

}
