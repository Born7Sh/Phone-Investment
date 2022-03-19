package org.tukorea.pi.service;


import java.time.LocalTime;

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
		LocalTime now = LocalTime.now();
		int hour = now.getHour();
		
		// 장 개장시간은 13시부터 21시까지
		if(hour>=13 && hour<21) {
			return stockDAO.selectNowStock(symbol);
		}
	
		return stockDAO.selectLastStock(symbol);

		
	}
	
}
