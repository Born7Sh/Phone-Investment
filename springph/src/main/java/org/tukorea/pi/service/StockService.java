package org.tukorea.pi.service;

import org.tukorea.pi.domain.StockVO;

public interface StockService {
	public StockVO readNowStock(String symbol) throws Exception;
}
