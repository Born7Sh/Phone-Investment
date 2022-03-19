package org.tukorea.pi.domain;

public class StockVO {
	private String symbol;
	private String stock_datetime;
	private float price;
	private String symbol_ko;
	private String symbol_en;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getStock_datetime() {
		return stock_datetime;
	}
	public void setStock_datetime(String stock_datetime) {
		this.stock_datetime = stock_datetime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getSymbol_ko() {
		return symbol_ko;
	}
	public void setSymbol_ko(String symbol_ko) {
		this.symbol_ko = symbol_ko;
	}
	public String getSymbol_en() {
		return symbol_en;
	}
	public void setSymbol_en(String symbol_en) {
		this.symbol_en = symbol_en;
	}
	
	
}
