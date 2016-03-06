package category;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by pan on 2016/3/6.
 */
public class OtherTest{


    /**
     * 标记该测试为"CrazyTests"分类和"SmartTests"分类
     */
    @Category({CrazyTests.class, SmartTests.class})
    @Test
    public void c() {
    }


}
