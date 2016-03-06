package ignore;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by pan on 2016/3/6.
 */
public class IgnoreTest {

    @Test
    @Ignore("開発者は休暇している")
    public void shouldIgnore(){
        fail();
    }

    @Test
    public void verify_multiple_values(){
        assertThat(1, equalTo(3 - 2));
    }
}
