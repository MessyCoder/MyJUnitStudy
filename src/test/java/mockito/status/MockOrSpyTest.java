package mockito.status;

import com.packt.trading.Portfolio;
import com.packt.trading.dto.Stock;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Mockito在运行时查看某对象是否是Mock，还是spy
 */
public class MockOrSpyTest {
    Stock globalStock = Mockito.when(Mockito.mock(Stock.class).getPrice()).
            thenReturn(BigDecimal.ONE).getMock();
    @Test
    public void mocking_details() throws Exception {
        Portfolio pf1 = Mockito.mock(Portfolio.class, Mockito.RETURNS_MOCKS);
        BigDecimal result = pf1.getAvgPrice(globalStock);
        assertNotNull(result);

        assertTrue(Mockito.mockingDetails(pf1).isMock());

        Stock testStock = Mockito.spy(new Stock(null, null, null));

        assertTrue(Mockito.mockingDetails(testStock).isSpy());
    }
}
