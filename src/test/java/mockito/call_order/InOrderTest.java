package mockito.call_order;


import com.packt.trading.MarketWatcher;
import com.packt.trading.Portfolio;
import com.packt.trading.dto.Stock;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class InOrderTest {
    @Mock
    MarketWatcher marketWatcher;

    @Mock
    Portfolio portfolio;

    public InOrderTest(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void call_in_order_test() throws Exception {
        Stock aCorp = new Stock("A", "A Corp", new BigDecimal(11.20));
        portfolio.getAvgPrice(aCorp);
        portfolio.buy(aCorp);



        InOrder inOrder= inOrder(portfolio,marketWatcher);
        /**
         * 调用顺序验证失败：buy后于getAvgPrice 调用
         *
         * 所以该测试失败。
         */
        inOrder.verify(portfolio, times(1)).buy(isA(Stock.class));
        inOrder.verify(portfolio).getAvgPrice(isA(Stock.class));
    }

    @Test
    public void call_in_order_test_times() throws Exception {
        Stock aCorp = new Stock("A", "A Corp", new BigDecimal(11.20));
        portfolio.buy(aCorp);
        portfolio.getAvgPrice(aCorp);
        portfolio.buy(aCorp);
        InOrder inOrder= inOrder(portfolio,marketWatcher);

        /**
         *  buy方法在getAvgPrice的之前和之后各调用一次。所以该测试成功。
         *
         *  此处的times不代表总次数。
         */
        inOrder.verify(portfolio, times(1)).buy(isA(Stock.class));
        inOrder.verify(portfolio).getAvgPrice(isA(Stock.class));
        inOrder.verify(portfolio, times(1)).buy(isA(Stock.class));
    }

}
