package mockito.matcher;

import org.mockito.ArgumentMatcher;

/**
 * Created by pan on 16/3/8.
 */
public class BlueChipStockMatcher extends ArgumentMatcher<String> {


    @Override
    public boolean matches(Object argument) {
        return "FB".equals(argument) ||  "AAPL".equals(argument);
    }
}
