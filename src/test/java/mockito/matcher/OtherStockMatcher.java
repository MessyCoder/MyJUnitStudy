package mockito.matcher;

/**
 * Created by pan on 16/3/8.
 */
public class OtherStockMatcher extends BlueChipStockMatcher{
    @Override
    public boolean matches(Object symbol) {
        return !super.matches(symbol);
    }
}
