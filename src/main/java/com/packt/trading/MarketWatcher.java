package com.packt.trading;

import com.packt.trading.dto.Stock;

/**
 * Created by pan on 2016/3/7.
 */
public interface MarketWatcher {
    Stock getQuote(String symbol);
}
