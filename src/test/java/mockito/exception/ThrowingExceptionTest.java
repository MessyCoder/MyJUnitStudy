package mockito.exception;

import com.packt.trading.MarketWatcher;
import com.packt.trading.Portfolio;
import com.packt.trading.dto.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ThrowingExceptionTest {

    @Mock
    MarketWatcher marketWatcher;
    @Mock
    Portfolio portfolio;


    /**
     * 当测试一个 有 返回值的方法时，可用下面的模式测试其抛出异常。
     *
     * @throws Exception
     */
    @Test(expected = IllegalStateException.class)
    public void throwsException() throws Exception {
        when(portfolio.getAvgPrice(isA(Stock.class))).thenThrow(new IllegalStateException("Database down"));
        portfolio.getAvgPrice(new Stock(null, null, null));
    }

    /**
     * 当测试 没有 返回值类型为void的方法时，可用下面的模式测试其抛出异常。
     *
     * @throws Exception
     */
    @Test(expected = IllegalStateException.class)
    public void throwsException_void_methods() throws Exception {
        doThrow(new IllegalStateException()).when(portfolio).buy(isA(Stock.class));
        portfolio.buy(new Stock(null, null, null));
    }


}