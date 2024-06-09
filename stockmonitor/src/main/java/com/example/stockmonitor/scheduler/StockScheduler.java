package com.example.stockmonitor.scheduler;

import com.example.stockmonitor.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockScheduler {

    @Autowired
    private StockService stockService;

    @Scheduled(fixedRate = 60000) // Update every minute
    public void updateStocks() {
        // Update your stocks here
        stockService.updateStock("TSLA");
        // Add more stocks as needed
    }
}
