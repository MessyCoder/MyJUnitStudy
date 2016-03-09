package mockito.answer;

import com.packt.trading.MarketWatcher;
import com.packt.trading.Portfolio;
import com.packt.trading.dto.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AnswerTest {
    Map<String, List<Stock>> stockMap;

    @Mock
    MarketWatcher marketWatcher;
    @Mock
    Portfolio portfolio;


    @Before
    public void setup(){
        stockMap = new HashMap<String, List<Stock>>();
    }
    /**
     * 利用Answer接口可以设置调用某个Mock方法时触发某些逻辑，以及动态设置返回值。
     */
    @Test
    public void testDoAnswer(){
        doAnswer(new BuyAnswer()).when(portfolio).buy(isA(Stock.class));

        when(portfolio.getCurrentValue()). then(new TotalPriceAnswer());

        portfolio.buy(new Stock("A", "A", BigDecimal.TEN));
        portfolio.buy(new Stock("B", "B", BigDecimal.ONE));

        assertEquals(new BigDecimal("11"), portfolio.getCurrentValue());
    }

    class BuyAnswer implements Answer<Object> {

        public Object answer(InvocationOnMock invocation) throws Throwable {
            /**
             * 通过invocation对象可以获知方法调用时参数。
             */
            Stock newStock = (Stock)invocation.getArguments()[0];
            List<Stock> stocks = stockMap.get(newStock.getSymbol());
            if(stocks != null) {
                stocks.add(newStock);
            }else {
                stocks = new ArrayList<Stock>();
                stocks.add(newStock);
                stockMap.put(newStock.getSymbol(), stocks);
            }

            /**
             * 对于返回值为void的方法来说，Answer实现中可以返回null。
             * 返回其他对象不会导致错误。但是也不起作用。
             */
            return null;
        }
    }

    class TotalPriceAnswer implements Answer<BigDecimal>{

        public BigDecimal answer(InvocationOnMock invocation) throws Throwable {
            BigDecimal totalPrice = BigDecimal.ZERO;
            for(String stockId : stockMap.keySet()) {
                for(Stock stock : stockMap.get(stockId)) {
                    totalPrice = totalPrice.add(stock.getPrice());
                }
            }
            return totalPrice;
        }
    }
}




