package junit.theory;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Created by pan on 2016/3/5.
 */
@RunWith(Theories.class)
public class TheorySingleParameterTest {

    @DataPoint
    public static final String name ="Jack";
    @DataPoint
    public static String mike ="Mike";

    @DataPoints
    public static final char[] TTT = new char[]{
            'c', 'd'
    };

    /**
     * 参数data此时会匹配一个攻类型的@DataPoint 的静态公开变量
     *
     * 如果有多个类型匹配的变量，则此方法被执行多次。
     * 如果没有匹配的变量，此单元测试会报异常。
     *
     * @param data
     */
    @Theory
    public void sanity(String data) {
        System.out.println("Sanity check " + data);
    }
}
