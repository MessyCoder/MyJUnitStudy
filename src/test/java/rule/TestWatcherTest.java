package rule;

import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;
import org.junit.runners.model.Statement;

/**
 * Created by panguofeng on 16/3/5.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestWatcherTest {
    private static String dog = "";

    /**
     * TestWatcher相当于一个测试方法的监听器，这里面有一些会在测试方法开始，结束，成功，失败的时候调用的
     * 回调方法， 详细起见，列出了所有可以重写的回调。其实仅有succeeded和failed是必须的。
     */
    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }


        @Override
        protected void succeeded(Description description) {
            dog += description.getMethodName() + " " + "success!\n";

        }

        @Override
        protected void failed(Throwable e, Description description) {
            dog += description.getDisplayName() + " " +
                    e.getClass().getSimpleName() + "\n";
        }

        @Override
        protected void starting(Description description) {
            super.starting(description);
        }
        @Override
        protected void finished(Description description) {
            super.finished(description);
        }
    };


    @Test
    public void red_test() {
        Assert.fail();
    }

    @Test
    public void green() {
    }
    @AfterClass
    public static void afterClass() {
        System.out.println(dog);
    }

}
