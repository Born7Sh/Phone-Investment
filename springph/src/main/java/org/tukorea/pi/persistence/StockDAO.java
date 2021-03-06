package org.tukorea.pi.persistence;

import org.tukorea.pi.domain.StockVO;
import org.tukorea.pi.domain.StockVO2;

public interface StockDAO {
	public StockVO selectNowStock(StockVO2 symbol) throws Exception;
	public StockVO selectLastStock(StockVO2 symbol) throws Exception;
	public StockVO selectMyStock(StockVO2 symbol) throws Exception;
}
