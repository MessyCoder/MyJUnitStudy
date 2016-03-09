package mockito.basics;

import com.packt.trading.MarketWatcher;
import com.packt.trading.Portfolio;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

/**
 * 在@Before的方法中对Mock对象进行初始化。
 */
public class AnnotationMockTest2 {
    @Mock
    MarketWatcher marketWatcher;

    @Mock
    Portfolio portfolio;



    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sanity() throws Exception {
        assertNotNull(marketWatcher);
        assertNotNull(portfolio);
    }

    @Test
    public void sanity2() throws Exception {
        assertNotNull(marketWatcher);
        assertNotNull(portfolio);
    }
}

