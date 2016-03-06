package rule;

import com.packtpub.junit.recap.Resource;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestName;

/**
 * Created by pan on 2016/3/6.
 */
public class ExternalResourceTest {
    Resource resource;


    @Rule
    public TestName name = new TestName();


    /**
     * ExternalResource 用于管理 在测试方法内部使用的外部资源。
     *
     * 比如 外部资料的连接和关闭 等。
     */
    @Rule
    public ExternalResource rule = new ExternalResource() {

        /**
         * 该方法在JUnit4.12中调用的被@Before还要早。
         * 也许这正是无法通过TestName获取 methodName的原因。
         * @throws Throwable
         */
        @Override
        protected void before() throws Throwable {
            resource = new Resource();
            resource.open();

            //下面的方法返回的是null，与教科书中不符，可能是bug
            //System.out.println(name.getMethodName());
        }


        /**
         * 该方法在JUnit4.12中调用的被@After还要晚。
         */
        @Override
        protected void after() {
            resource.close();
            System.out.println("\n");
        }
    };


    @Before
    public void prepare(){
        System.out.println(name.getMethodName() + " started");
    }
    @After
    public void leaveAway(){
        System.out.println(name.getMethodName() + " ended");
    }

    @Test
    public void someTest() throws Exception {
        System.out.println(resource.get());
    }
    @Test
    public void someTest2() throws Exception {
        System.out.println(resource.get());
    }



}
