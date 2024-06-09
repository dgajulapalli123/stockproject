package com.example.stockmonitor.controller;

import com.example.stockmonitor.model.Stock;
import com.example.stockmonitor.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{symbol}")
    public Stock getStock(@PathVariable String symbol) {
        return stockService.getStock(symbol);
    }

    @PutMapping("/{symbol}/update")
    public Stock updateStock(@PathVariable String symbol) {
        return stockService.updateStock(symbol);
    }
}
