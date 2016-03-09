package mockito.stub;

/**
 * Created by pan on 16/3/8.
 */

import com.packt.trading.MarketWatcher;
import com.packt.trading.StockBroker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static org.junit.Assert.assertNotNull;


/**
 * 利用假实现来测试功能对象StockBroker的行为
 *
 * 该对象是否调用了某个对象的某个方法，调用了几次，这样的验证。
 */
@RunWith(MockitoJUnitRunner.class)
public class InjectMocksTest {

    @Mock
    MarketWatcher marketWatcher;


    /**
     * Mockito会自动根据构造函数，各种set方法将mock对象插入到被测试对象中。
     * 具体细节可以查看@InjectMocks的文档。
     */
    @InjectMocks
    StockBroker broker;

    @Test
    public void marketWatcher_Returns_current_stock_status() {
        assertNotNull(broker);
    }

}
