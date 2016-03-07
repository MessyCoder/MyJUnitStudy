package com.packt.trading;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.packt.trading.dto.Stock;

/**
 * Portfolio : n. 公文包；文件夹；证券投资组合；部长职务
 */
public final class Portfolio {
    private Map<String, List<Stock>> stockMap = new HashMap<String, List<Stock>>();

    public void buy(Stock stock) {
        List<Stock> stocks = stockMap.get(stock.getSymbol());
        if(stocks != null) {
            stocks.add(stock);
        }else {
            stocks = new ArrayList<Stock>();
            stocks.add(stock);
            stockMap.put(stock.getSymbol(), stocks);
        }
    }

    public void sell(Stock stock, int quantity) {
        List<Stock> stocks = stockMap.get(stock.getSymbol());
        if(stocks == null) {
            throw new IllegalStateException(stock.getSymbol()+" not bought");
        }

        if(stocks.size() < quantity) {
            quantity = stocks.size();
        }

        for(int i=quantity-1; i >=0; i--) {
            stocks.remove(i);
        }
    }

    /**
     * 获取当前持有的某个股票的均价
     * @param stock
     * @return
     */
    public BigDecimal getAvgPrice(Stock stock) {
        List<Stock> stocks = stockMap.get(stock.getSymbol());
        BigDecimal avgPrice = BigDecimal.ZERO;
        if(stocks != null) {
            for(Stock aStock:stocks) {
                avgPrice = avgPrice.add(aStock.getPrice());
            }

            avgPrice = avgPrice.divide(new BigDecimal(stocks.size()));
        }

        return avgPrice;
    }


    /**
     * 获取当前持有的股票的总价值。
     * @return
     */
    public BigDecimal getCurrentValue() {
        BigDecimal avgPrice = BigDecimal.ZERO;
        for(String stockId: stockMap.keySet()) {
            for(Stock stock:stockMap.get(stockId)) {
                avgPrice = avgPrice.add(stock.getPrice());
            }
        }

        return avgPrice;
    }

}
