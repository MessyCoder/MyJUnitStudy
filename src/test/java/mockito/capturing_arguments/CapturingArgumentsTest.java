package mockito.capturing_arguments;

import com.packt.trading.MarketWatcher;
import com.packt.trading.Portfolio;
import com.packt.trading.StockBroker;
import com.packt.trading.dto.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by pan on 16/3/9.
 */
@RunWith(MockitoJUnitRunner.class)
public class CapturingArgumentsTest {

    @Mock
    MarketWatcher marketWatcher;
    @Mock
    Portfolio portfolio;

    private StockBroker broker;

    @Before
    public void setup(){
        broker = new StockBroker(marketWatcher);
    }

    @Test
    public void argument_captor() throws Exception {
        when(portfolio.getAvgPrice(isA(Stock.class))).thenReturn( new BigDecimal("10.00"));
        Stock aCorp = new Stock("A", "A Corp", new BigDecimal(11.20));
        when(marketWatcher.getQuote(anyString())).thenReturn(aCorp);

        broker.perform(portfolio, aCorp);

        ArgumentCaptor<String> stockIdCaptor = ArgumentCaptor.forClass(String.class);

        /**
         * capture方法必须要用在verification语句中。
         *
         * 由于一个方法可能被调用多次，所以参数值也会有多套。stockIdCaptor.getValue()用于获取最后一次调用的参数值。
         * 此外，还可以调用getAllValues()来获取所有的参数。
         */
        verify(marketWatcher).getQuote(stockIdCaptor.capture());
        assertEquals("A", stockIdCaptor.getValue());

        //Two arguments captured
        ArgumentCaptor<Stock> stockCaptor = ArgumentCaptor.forClass(Stock.class);
        ArgumentCaptor<Integer> stockSellCountCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(portfolio).sell(stockCaptor.capture(), stockSellCountCaptor.capture());

        assertEquals("A", stockCaptor.getValue().getSymbol());
        assertEquals(10, stockSellCountCaptor.getValue().intValue());
    }
}
