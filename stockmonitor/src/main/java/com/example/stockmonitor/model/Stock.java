package com.example.stockmonitor.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Stock {
    private static final String API_KEY = "ZQZ7I69AC40B6LFI";  // Replace with your actual API key
    private static final String BASE_URL = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE";
    
    public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public String getLatestTradingDay() {
		return latestTradingDay;
	}

	public void setLatestTradingDay(String latestTradingDay) {
		this.latestTradingDay = latestTradingDay;
	}

	public double getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(double previousClose) {
		this.previousClose = previousClose;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public double getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(double changePercent) {
		this.changePercent = changePercent;
	}

	private String symbol;
    private double open;
    private double high;
    private double low;
    private double price;
    private double volume;
    private String latestTradingDay;
    private double previousClose;
    private double change;
    private double changePercent;

    public Stock(String symbol) {
        this.symbol = symbol;
        update();
    }

    public void update() {
        String jsonString = fetchStockData(this.symbol);
        //System.out.println(jsonString);
        this.symbol = extractValue(jsonString, "01. symbol");
        this.open = Double.parseDouble(extractValue(jsonString, "02. open"));
        this.high = Double.parseDouble(extractValue(jsonString, "03. high"));
        this.low = Double.parseDouble(extractValue(jsonString, "04. low"));
        this.price = Double.parseDouble(extractValue(jsonString, "05. price"));
        this.volume = Double.parseDouble(extractValue(jsonString, "06. volume"));
        this.latestTradingDay = extractValue(jsonString, "07. latest trading day");
        this.previousClose = Double.parseDouble(extractValue(jsonString, "08. previous close"));
        this.change = Double.parseDouble(extractValue(jsonString, "09. change"));
        int len=extractValue(jsonString, "10. change percent").length();
        this.changePercent = Double.parseDouble(extractValue(jsonString, "10. change percent").substring(0,len-1));
    }

    public void printStockInfo() {
        System.out.println("Symbol: " + this.symbol);
        System.out.println("Open: " + this.open);
        System.out.println("High: " + this.high);
        System.out.println("Low: " + this.low);
        System.out.println("Price: " + this.price);
        System.out.println("Volume: " + this.volume);
        System.out.println("Latest Trading Day: " + this.latestTradingDay);
        System.out.println("Previous Close: " + this.previousClose);
        System.out.println("Change: " + this.change);
        System.out.println("Change Percent: " + this.changePercent);
    }

    private static String fetchStockData(String ticker) {
        String urlString = BASE_URL + "&symbol=" + ticker + "&apikey=" + API_KEY;
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                connection.disconnect();
            } else {
                System.out.println("GET request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return content.toString();
    }

    private static String extractValue(String jsonString, String key) {
        String searchString = "\"" + key + "\": \"";
        int startIndex = jsonString.indexOf(searchString);
        if (startIndex == -1) {
            return "N/A";  // Return "N/A" if the key is not found
        }
        startIndex += searchString.length();
        int endIndex = jsonString.indexOf("\"", startIndex);
        return jsonString.substring(startIndex, endIndex);
    }


}
