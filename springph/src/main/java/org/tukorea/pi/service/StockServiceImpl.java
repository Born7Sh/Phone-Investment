package org.tukorea.pi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.tukorea.pi.domain.StockVO;
import org.tukorea.pi.persistence.StockDAO;


@ComponentScan({"org.tukorea.pi.persistence"})
@Service("StockService")
public class StockServiceImpl implements StockService {
	@Autowired
	private StockDAO stockDAO;

	public StockVO readNowStock(String symbol) throws Exception{
		return stockDAO.selectNowStock(symbol);
	}
	
}
