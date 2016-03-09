package mockito.answer;

import com.packt.trading.Portfolio;
import com.packt.trading.dto.Stock;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


/**
 * Answer接口还可用于生成具有指定返回值策略的mock对象。
 */
public class ReturnPolicyTest {


    /**
     * 通过Mockito.mock()方法获取mock对象时可以设置该对象的默认方法返回值策略。
     *
     * 返回值策略一共有五个，它们分别是：
     * Mockito.RETURNS_DEFAULTS
     *      这是默认策略。对象类型返回null，布尔类型返回false，数字类型返回0。
     * Mockito.RETURNS_SMART_NULLS
     *      方法调用时，返回对应类型的spy对象。
     *      如果没有为该spy对象的方法设置返回值，则调用时产生SmartNullPointerException异常。
     * Mockito.RETURNS_MOCKS
     *      返回对应类型的mock。原生数据类型的时候返回0。
     * Mockito.RETURNS_DEEP_STUBS
     *      返回对应类型的mock。不过通过该mock获取的对象也是mock；永远不要在clean code里面使用该策略。
     *      该策略仅用于测试旧代码。
     * Mockito.CALLS_REAL_METHODS
     *      调用真实的对象方法。
     *
     *
     */
    @Test
    public void changing_default() throws Exception {
        Stock aCorp = new Stock("A", "A Corp", new BigDecimal(11.20));

        Portfolio pf = mock(Portfolio.class);

        //默认情况下方法的返回值都是null
        assertNull(pf.getAvgPrice(aCorp));

        /**
         *
         */
        Portfolio pf1 = mock(Portfolio.class, Mockito.RETURNS_SMART_NULLS);

        //avgPrice是BigDecimal的spy实例。
        BigDecimal avgPrice = pf1.getAvgPrice(aCorp);
        System.out.println("#1 "+ avgPrice);
        assertNotNull(avgPrice);


        Portfolio pf2 = mock(Portfolio.class, Mockito.RETURNS_MOCKS);
        System.out.println("#2 "+pf2.getAvgPrice(aCorp));
        assertNotNull(pf2.getAvgPrice(aCorp));

        Portfolio pf3 = mock(Portfolio.class, Mockito.RETURNS_DEEP_STUBS);
        //a deep stubbed mock is returned
        System.out.println("#3 "+pf3.getAvgPrice(aCorp));
        assertNotNull(pf3.getAvgPrice(aCorp));

    }

}
