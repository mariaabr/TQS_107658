package ua.tqs;

import java.util.*;
import ua.tqs.Stock;
import ua.tqs.IStockmarketService;

public class StocksPortfolio {
    
    private IStockmarketService stockmarket;
    private List<Stock> stocks;

    public StocksPortfolio(IStockmarketService stockmarket){
        this.stockmarket = stockmarket;
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double totalValue() {
        double totalValue = 0;

        for(Stock stock : stocks) {
            totalValue += stockmarket.lookUpPrice(stock.getLabel()) * stock.getQuantity();
        }

        return totalValue;
    }
}
