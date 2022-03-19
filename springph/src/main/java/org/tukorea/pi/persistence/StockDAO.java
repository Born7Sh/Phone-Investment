package org.tukorea.pi.persistence;

import org.tukorea.pi.domain.StockVO;

public interface StockDAO {
	public StockVO selectNowStock(String symbol) throws Exception;
}
