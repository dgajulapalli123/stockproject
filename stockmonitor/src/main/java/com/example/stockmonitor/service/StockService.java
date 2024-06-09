package com.example.stockmonitor.service;

import com.example.stockmonitor.model.Stock;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StockService {

    private Map<String, Stock> stockCache = new HashMap<>();

    public Stock getStock(String symbol) {
        if (!stockCache.containsKey(symbol)) {
            Stock stock = new Stock(symbol);
            stockCache.put(symbol, stock);
        }
        return stockCache.get(symbol);
    }

    public Stock updateStock(String symbol) {
        Stock stock = stockCache.get(symbol);
        if (stock != null) {
            stock.update();
        } else {
            stock = new Stock(symbol);
            stockCache.put(symbol, stock);
        }
        return stock;
    }
}

