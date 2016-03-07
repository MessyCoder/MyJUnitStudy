package junit.assertThat;

import junit.assertThat.matcher.LessThanOrEqualTo;
import org.junit.Test;

//加入这两个静态导入使代码更容易书写
import java.util.Arrays;
import java.util.List;

//Junit 4 自身依赖hamcrest
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
/**
 *
 * 下面的单元测试用使用的都是hamcrest的Matcher，
 * Junit本身也有一个JunitMatcher，但是其中的方法全部被标记为 已过时。
 */
public class AssertThatTest {

    @Test
    public void verify_matcher(){
        int age = 30;

        assertThat(age, equalTo(30));
        assertThat(age, is(30));
        assertThat(age, not(equalTo(33)));
        assertThat(age, is(not(33)));
    }

    @Test
    public void verify_multiple_values(){
        double marks = 100.00;
        assertThat(marks, either(is(100.00)).or(is(90.9)));
        assertThat(marks, both(not(99.99)).and(not(60.00)));

        assertThat(marks, anyOf(is(11.00), is(33.3), is(100.00)));
        assertThat(marks, not(anyOf(is(0.0), is(33.5), is(200.0))));

        assertThat(marks, not(allOf(is(10.0), is(100.0))));
    }

    @Test
    public void verify_collection_values(){
        List<Double> salary = Arrays.asList(50.0, 200.0, 500.0);

        assertThat(salary, hasItem(50.0));
        assertThat(salary, hasItems(50.0, 200.0));
        assertThat(salary, not(hasItem(51.0)));
    }

    @Test
    public void verify_strings(){
        String name = "John Jr Dale";
        assertThat(name, startsWith("John"));
        assertThat(name, endsWith("Dale"));
        assertThat(name, containsString("Jr"));
        assertThat(name, not(containsString("jr")));
    }

    @Test
    public void lessThanOrEqualTo_custom_matcher(){
        int actualGoalScored = 100;
        assertThat(actualGoalScored, LessThanOrEqualTo.lessThanOrEqual(200));
    }

}
