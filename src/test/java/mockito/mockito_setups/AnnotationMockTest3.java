package mockito.mockito_setups;

import com.packt.trading.MarketWatcher;
import com.packt.trading.Portfolio;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

/**
 * 使用MockitoJUnitRunner运行单体测试
 * 也可以对Mock对象进行初始化。这是最经典的用法。
 *
 * Mockito不可以对终态类， 匿名类，以及原生数据类进行Mock
 */

@RunWith(MockitoJUnitRunner.class)
public class AnnotationMockTest3 {
    @Mock
    MarketWatcher marketWatcher;

    @Mock
    Portfolio portfolio;

    @Test
    public void sanity() throws Exception {
        assertNotNull(marketWatcher);
        assertNotNull(portfolio);
    }

    @Test
    public void sanity2() throws Exception {
        assertNotNull(marketWatcher);
        assertNotNull(portfolio);
    }
}

