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

    public void describeTo(Description description) {
        description.appendText(" less than or equal(<=) " + expectedValue);
    }

    public static <T extends Comparable<T>> Matcher<T> lessThanOrEqual(T t){
        return new LessThanOrEqualTo<T>(t);
    }


}
