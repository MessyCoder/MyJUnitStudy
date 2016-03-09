package mockito.status;

import com.packt.trading.Portfolio;
import com.packt.trading.dto.Stock;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by pan on 16/3/9.
 */
public class ResetStatusTest {

    @Test
    public void resetMock() throws Exception {
        Stock aCorp = new Stock("A", "A Corp", new BigDecimal(11.20));
        Portfolio portfolio = Mockito.mock(Portfolio.class);
        when(portfolio.getAvgPrice(eq(aCorp))).thenReturn(BigDecimal.ONE);
        assertNotNull(portfolio.getAvgPrice(aCorp));

        /**
         * 将mock对象进行重置
         */
        Mockito.reset(portfolio);

        /**
         * 因此这里又重新返回null
         */
        assertNull(portfolio.getAvgPrice(aCorp));
    }


}
