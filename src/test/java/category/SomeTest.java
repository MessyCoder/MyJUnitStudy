package category;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

/**
 * Created by pan on 2016/3/6.
 */
public class SomeTest {
    @Test
    public void a() {
        fail();
    }


    /**
     *
     */
    @Category(CrazyTests.class)
    @Test
    public void b() {
    }
}
