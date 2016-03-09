package mockito.answer;

import com.packt.trading.Portfolio;
import com.packt.trading.dto.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.verification.SmartNullPointerException;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;


/**
 * 通过Mock注解也可以设置返回值策略：
 */
@RunWith(MockitoJUnitRunner.class)
public class ReturnPolicyTest2 {


    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    Portfolio pf;

    @Test(expected = SmartNullPointerException.class)
    public void changing_default() throws Exception {
        Stock aCorp = new Stock("A", "A Corp", new BigDecimal(11.20));

        BigDecimal avgPrice = pf.getAvgPrice(aCorp);
        assertNotNull(avgPrice);

        System.out.println(avgPrice.toBigInteger());
    }

}
