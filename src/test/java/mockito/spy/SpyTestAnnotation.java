package mockito.spy;

import com.packt.trading.dto.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * 书上是这样描述spy的：
 *
 * spy用于修改那些难以测试的方法。而不至于将整个对象都mock掉。
 * 也称作部分Mock。但是设计良好的代码，或者测试驱动的代码不应该使用spy进行测试。
 *
 * spy多用于老旧的难以测试的情况下。
 */

@RunWith(MockitoJUnitRunner.class)
public class SpyTestAnnotation {

    /**
     * Mockito在运行时，如果检测到@Spy标记在非null的成员变量身上，则以当前的值进行spy，否则将调用
     * 对应类型的无参构造函数进行构造，然后再spy。
     */
    @Spy
    Stock spyStock = new Stock("A", "Company A", BigDecimal.ONE);

    @Spy
    Stock fooStock;

    @Test
    public void spying() throws Exception {
        assertNotNull(fooStock);
        assertNull(fooStock.getName());



        //这是被【spy：侦察】的对象。

        assertEquals("A", spyStock.getSymbol());

        //spy可以调用真实的方法，并且产生实际的效果
        spyStock.updatePrice(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, spyStock.getPrice());

        /**
         * 同时spy对象还可以设定对象的返回值，设定之后，原方法不再被执行。
         *
         * 但是此处的when(spyStock.getPrice())仍旧会调用原方法！！
         * 这是syp的一种副作用，不过，使用do-when模式可以避免该问题。（查看void_method包了解如何操作）
         * 所以：当使用spy的时候，尽量不要使用when-then模式。
         *
         */
        when(spyStock.getPrice()).thenReturn(BigDecimal.TEN);
        /**
         * verify仍然认为上面when方法中没有调用getPrice方法。所以这里times里面只能写“1”
         * 实际上，如果为debug一下getPrice的话，确实有两次调用。
         */
        verify(spyStock, times(1)).getPrice();


        spyStock.updatePrice(new BigDecimal("7"));

        //设置方法返回10之后原方法不再执行。
        assertNotEquals(new BigDecimal("7"), spyStock.getPrice());
        assertEquals(BigDecimal.TEN, spyStock.getPrice());
    }



}
