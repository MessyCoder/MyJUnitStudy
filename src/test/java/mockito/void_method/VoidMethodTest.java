package mockito.void_method;

import com.packt.trading.MarketWatcher;
import com.packt.trading.Portfolio;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.UnfinishedStubbingException;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * org.mockito.Mockito里面有若干以do开头的方法，
 * 这些方法是专门用来处理返回值为void的方法的。
 *
 * 之所以为void单独设置是因为when方法无法接收void。 这些方法同样可以用于处理非void方法。
 *
 * 只不过，do方法都不是类型安全的
 */
@RunWith(MockitoJUnitRunner.class)
public class VoidMethodTest {


    @Mock
    MarketWatcher marketWatcher;
    @Mock
    Portfolio portfolio;



    @Test
    public void test_void_method(){
        /**
         * 这里的thenReturn方法是类型安全的，参数必须为BigDecimal，否则无法编译。
         */
        when(portfolio.getCurrentValue()).thenReturn(BigDecimal.ONE);

        //该方法运行没有问题
        portfolio.getCurrentValue();




        try {
            /**
             * doReturn方法不是类型安全，它完全可以设置一个其他类型的返回值.
             * 运行时就会报错。
             */
            doReturn("See returning a String").when(portfolio.getCurrentValue());
        }catch (UnfinishedStubbingException ignore){

        }
    }


    /**
     * 使用do-when方法来解除spy的副作用。
     */
    @Test
    public void avoid_spy_side_affect(){
        List<String> list = new ArrayList<String>();
        List<String> spy = spy(list);

        /**
         * 不这么写的话get(0)会导致跨界异常。
         * when里面写的是spy或者mock对象，不可以是真实对象！
         *
         * 此外，do-when中的when方法和when-then中的when方法不是同一个。
         * 在when-then中无法像下面那样使用。
         */
        doReturn("faked data").when(spy).get(0);
        assertEquals("faked data", spy.get(0));
    }



}
