package mockito.spy;

import com.packt.trading.dto.Stock;
import org.junit.Test;

import java.math.BigDecimal;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


/**
 * 书上是这样描述spy的：
 *
 * spy用于修改那些难以测试的方法。而不至于将整个对象都mock掉。
 * 也称作部分Mock。但是设计良好的代码，或者测试驱动的代码不应该使用spy进行测试。
 *
 * spy多用于老旧的难以测试的情况下。
 */
public class SpyTest {
    @Test
    public void spying() throws Exception {
        //这是真实的对象。
        Stock realStock = new Stock("A", "Company A", BigDecimal.ONE);

        //这是被【spy：侦察】的对象。
        Stock spyStock = spy(realStock);
        assertEquals("A", spyStock.getSymbol());

        //spy可以调用真实的方法，并且产生实际的效果
        spyStock.updatePrice(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, spyStock.getPrice());

        //同时spy对象还可以设定对象的返回值，设定之后，原方法不再被执行。
        //但是此处的when(spyStock.getPrice())仍旧会调用原方法！！
        when(spyStock.getPrice()).thenReturn(BigDecimal.TEN);
        spyStock.updatePrice(new BigDecimal("7"));
        //设置方法返回10之后便不再受到他人影响。
        assertNotEquals(new BigDecimal("7"), spyStock.getPrice());
        assertEquals(BigDecimal.TEN, spyStock.getPrice());
    }
}
