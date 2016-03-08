package mockito.consecutive;

import com.packt.trading.MarketWatcher;
import com.packt.trading.Portfolio;
import com.packt.trading.dto.Stock;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;


@RunWith(MockitoJUnitRunner.class)
public class ConsecutiveCallTest {

    @Mock
    MarketWatcher marketWatcher;
    @Mock
    Portfolio portfolio;

    /**
     * Mockito中的thenReturn方法可以写入多个参数作为“每次”调用的返回值。
     *
     * @throws Exception
     */
    @Test
    public void consecutive_calls() throws Exception {
        Stock stock = new Stock(null, null, null);
        when(portfolio.getAvgPrice(stock)).thenReturn(BigDecimal.TEN, BigDecimal.ZERO);

        /**
         * 第一次调用和第二次调用分别返回BigDecimal.TEN 和 BigDecimal.ZERO。
         * 更多的调用都会返回BigDecimal.ZERO。
         */
        assertEquals(BigDecimal.TEN, portfolio.getAvgPrice(stock));
        assertEquals(BigDecimal.ZERO, portfolio.getAvgPrice(stock));
        assertEquals(BigDecimal.ZERO, portfolio.getAvgPrice(stock));
    }

    /**
     * 设置多次调用的返回值还可以有另一种方法链式的方法：
     * @throws Exception
     */
    @Test(expected = IllegalStateException.class)
    public void consecutive_calls_multiple_thenReturn() throws Exception {
        Stock stock = new Stock(null, null, null);
        /**
         * 第一次返回BigDecimal.TEN
         * 第二次也返回BigDecimal.TEN
         * 第三次抛出异常。
         */
        when(portfolio.getAvgPrice(stock))
                .thenReturn(BigDecimal.TEN)
                .thenReturn(BigDecimal.TEN)
                .thenThrow(new IllegalStateException());

        /**
         * 第一次调用和第二次调用分别返回BigDecimal.TEN 和 BigDecimal.ZERO。
         * 更多的调用都会返回BigDecimal.ZERO。
         */
        assertEquals(BigDecimal.TEN, portfolio.getAvgPrice(stock));
        assertEquals(BigDecimal.TEN, portfolio.getAvgPrice(stock));

        /**
         * 第三次调用getAvgPrice方法会抛出异常。
         */
        assertEquals(BigDecimal.ZERO, portfolio.getAvgPrice(stock));
    }
}
