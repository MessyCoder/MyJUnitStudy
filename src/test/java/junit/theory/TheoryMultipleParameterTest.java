package junit.theory;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Theories.class)
public class TheoryMultipleParameterTest {
    @DataPoint
    public static String name ="Jack";

    @DataPoint
    public static String mike ="Mike";

    @DataPoint
    public static String john ="John";

    /**
     * 当测试用例需要多个参数时， JUnit对其参数进行全模式匹配，
     *
     * 如果参数两个，@DataPoint也有2个，则此方法被执行4次。 2^2 = 4
     * 如果参数两个，@DataPoint也有3个，则此方法被执行9次。 3^2 = 9
     *
     * 执行次数为 从 N个 DataPoint 中找出 M个可重复的结果的个数。因此是 N^M 个。
     * (@DataPoints 会增加N的个数而已)
     *
     */
    @Theory
    public void sanity(String firstName, String middleName, String lastName) {
        System.out.println("Sanity check "+firstName+", "+middleName+", "+lastName);
    }

}
