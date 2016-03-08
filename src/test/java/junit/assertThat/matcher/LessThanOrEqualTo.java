package junit.assertThat.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by pan on 2016/3/4.
 */
public class LessThanOrEqualTo<T> extends BaseMatcher<T> {

    private final Comparable<T> expectedValue;
    public LessThanOrEqualTo(Comparable<T> expectedValue) {
        this.expectedValue = expectedValue;
    }

    public boolean matches(Object item) {
        int i = expectedValue.compareTo((T) item);
        return i > -1;
    }

    /**
     * 在出错的时候该方法会被调用用以收集可读的Matcher信息。
     * @param description
     */
    public void describeTo(Description description) {
        description.appendText(" less than or equal(<=) " + expectedValue);
    }


    /**
     * 为了在代码中表现出较高的易读性从而编写一个方便的静态方法
     * @param t 被比较的对象
     * @param <T> 实现了Comparable接口的类型
     * @return 当前的实现类型
     */
    public static <T extends Comparable<T>> Matcher<T> lessThanOrEqual(T t){
        return new LessThanOrEqualTo<T>(t);
    }


}
