package mockito.matcher;

import com.packt.trading.MarketWatcher;
import com.packt.trading.Portfolio;
import com.packt.trading.StockBroker;
import com.packt.trading.dto.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

/**
 * Created by pan on 16/3/8.
 */


@RunWith(MockitoJUnitRunner.class)
public class ArgumentMatcherTest {

    @Mock
    MarketWatcher marketWatcher;
    @Mock
    Portfolio portfolio;

    @Test
    public void argument_matcher(){
        when(portfolio.getAvgPrice(isA(Stock.class))).thenReturn(new BigDecimal("10.00"));
        Stock blueChipStock = new Stock("FB", "FB Corp", new BigDecimal(1000.00));
        Stock otherStock = new Stock("XY", "XY Corp", new BigDecimal(5.00));

        when(marketWatcher.getQuote(argThat(new BlueChipStockMatcher()))).thenReturn(blueChipStock);
        when(marketWatcher.getQuote(argThat(new OtherStockMatcher()))).thenReturn(otherStock);

        StockBroker broker = new StockBroker(marketWatcher);
        broker.perform(portfolio, blueChipStock);


        /**
         * isA相当于调用argThat(new InstanceOf(Stock.class)) 而InstanceOf 也是一个ArgumentMatcher
         */
        //！！！如果在参数上使用了matcher，那么在所有的参数上都要使用matcher！！！
        verify(portfolio).sell(isA(Stock.class), isA(Integer.class));

        broker.perform(portfolio, otherStock);

        //！！！如果在参数没有使用matcher，那么在所有的参数上都不要使用matcher！！！
        verify(portfolio, never()).sell(otherStock,10);

    }
}
