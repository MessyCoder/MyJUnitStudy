package mockito.mockito_setups;

import com.packt.trading.MarketWatcher;
import com.packt.trading.Portfolio;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;

/**
 * Created by pan on 2016/3/7.
 */
public class StaticMockTest {

    /**
     * 使用Mockito的静态方法mock获取一个mock（替身）对象
     */
    MarketWatcher marketWatcher = Mockito.mock(MarketWatcher.class);
    Portfolio portfolio = Mockito.mock(Portfolio.class);

    @Test
    public void sanity() throws Exception {
        assertNotNull(marketWatcher);
        assertNotNull(portfolio);
    }
}
