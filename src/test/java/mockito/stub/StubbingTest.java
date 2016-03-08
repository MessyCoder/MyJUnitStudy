package mockito.stub;

/**
 * Created by pan on 16/3/8.
 */
import com.packt.trading.MarketWatcher;
import com.packt.trading.Portfolio;
import com.packt.trading.StockBroker;
import com.packt.trading.dto.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


//Mockito继承自Matchers 所以直接导入Mockito就可以了。
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

/**
 * 利用假实现来测试功能对象StockBroker的行为
 *
 * 该对象是否调用了某个对象的某个方法，调用了几次，这样的验证。
 */
@RunWith(MockitoJUnitRunner.class)
public class StubbingTest {

    @Mock
    MarketWatcher marketWatcher;
    @Mock
    Portfolio portfolio;


    @Test
    public void marketWatcher_Returns_current_stock_status() {
        Stock uvsityCorp = new Stock("UV", "Uvsity Corporation", new BigDecimal("100.00"));
        //默认情况下调用被Mock的对象的方法时，返回对应类型的默认值。
        assertNull(marketWatcher.getQuote(""));
        assertNull(marketWatcher.getQuote("UV"));

        //做出相应假设后会返回指定的值
        when(marketWatcher.getQuote(anyString())).thenReturn(uvsityCorp);
        assertNotNull(marketWatcher.getQuote("UV"));

    }


    /**
     * 测试功能对象在调用perform时的行为。
     */
    @Test
    public void when_ten_percent_gain_then_the_stock_is_sold() {
        StockBroker broker = new StockBroker(marketWatcher);
        /*
         * 假设目前持有的某个股票的平均价格是10美刀。
         *
         * any方法的意思是 ： 当传入任何一个对象时。该方法只是为了解决类型转换的麻烦而存在。
         * when(portfolio.getAvgPrice(any(Stock.class))).thenReturn(new BigDecimal("10.00"));
         *
         * 如果要设定传入的参数必须是某类型时才如何如何的话，要用到isA方法
         */

        when(portfolio.getAvgPrice(isA(Stock.class))).thenReturn(new BigDecimal("10.00"));

        //而那个股票的价格是现在涨到了11.20美刀。
        Stock aCorp = new Stock("A", "A Corp", new BigDecimal("11.20"));
        when(marketWatcher.getQuote(anyString())).thenReturn(aCorp);

        //由于股票价格增长了12%， 因此执行perform会卖出股票。
        broker.perform(portfolio, aCorp);

        /*
         * 验证对象portfolio的sell方法被调用，并且参数分别是下面的值。
         *
         * 在实际的sell方法中，sell多半要链接数据库或者h互联网进行操作。
         * 不过由于我们只在乎sell的执行与否，而不关心它的具体过程和结果，因此对其进行Mock。
         */
        verify(portfolio).sell(aCorp, 10);

        /*
         * 调用verify方法时可以指定期待次数。如果不指定默认为1次，0次表示未被调用。指定负数会导致异常。
         * 验证多次调用的时候，每次参数都要匹配。
         *
         * 除了直接指定次数以外，还可以使用：
         * never() 表示 0次。
         * atLeastOnce() 表示至少一次。
         * atLeast(int minNumberOfInvocations) 至少指定的那些次数。
         * atMost(int maxNumberOfInvocations) 最多那些次数。
         * only() 仅仅一个方法被调用过一次。
         * timeout(int millis) 验证该方法的调用在某某时间之内。 （该方法多用于测试多线程等待，而不是实际的业务时间耗费）
         */
        verify(portfolio, times(1)).sell(aCorp, 10);
    }


    @Test
    public void verify_zero_interaction() {
        //验证村从来没有调用过这些对象上的方法。
        verifyZeroInteractions(marketWatcher, portfolio);
    }
    @Test
    public void verify_no_more_interaction(){
        Stock noStock = null;
        portfolio.getAvgPrice(null);
        portfolio.sell(null, 0);

        //验证：getAvgPrice被调用了一次
        verify(portfolio).getAvgPrice(null);

        /**
         * 验证：除去已经验证过的方法调用之外，没有其他方法被调用。
         *
         * 由于sell方法也被调用，因此该验证失败。
         */
        verifyNoMoreInteractions(portfolio);
    }
}
