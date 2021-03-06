package com.packt.trading;

import com.packt.trading.dto.Stock;

import java.math.BigDecimal;

/**
 * StockBroker : 股票经济人
 */
public class StockBroker {
    private final static BigDecimal LIMIT = new BigDecimal("0.10");
    private final MarketWatcher market;
    public StockBroker(MarketWatcher market) {
        System.out.println("StockBroker");
        this.market = market;
    }
    public void perform(Portfolio portfolio,Stock stock) {
        Stock liveStock = market.getQuote(stock.getSymbol());
        BigDecimal avgPrice = new BigDecimal("10.00");//portfolio.getAvgPrice(stock);
        BigDecimal priceGained = liveStock.getPrice().subtract(avgPrice);
        BigDecimal percentGain = priceGained.divide(avgPrice);
        if(percentGain.compareTo(LIMIT) > 0) {
            portfolio.sell(stock, 10);
        }else if(percentGain.compareTo(LIMIT) < 0){
            portfolio.buy(stock);
        }
    }
}
