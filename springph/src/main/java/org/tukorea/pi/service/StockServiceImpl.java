package org.tukorea.pi.service;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.tukorea.pi.domain.StockVO;
import org.tukorea.pi.domain.StockVO2;
import org.tukorea.pi.persistence.StockDAO;


@ComponentScan({"org.tukorea.pi.persistence"})
@Service("StockService")
public class StockServiceImpl implements StockService {
	@Autowired
	private StockDAO stockDAO;
	
	public final List<String> list = new ArrayList<String>(10) {
		{
			add("117"); add("221");  add("415"); add("530"); add("620");
			add("74"); add("95");  add("1124"); add("1125"); add("1226");
		}
	};
	
	
	public StockVO readNowStock(String symbol) throws Exception{
		LocalDateTime now = LocalDateTime.now();
		now.minusDays(24);
		
		String date = "stock_" + now.minusDays(24).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		int hour = now.getHour();
		String monthday =  Integer.toString(now.getMonthValue()) + Integer.toString(now.getDayOfMonth());
		
		StockVO2 stockVO2 = new StockVO2();
		stockVO2.setSymbol(symbol);
		stockVO2.setTable_name(date);

		// 월요일이 1
		DayOfWeek dayOfWeek = now.getDayOfWeek();
		int dayOfWeekNumber = dayOfWeek.getValue();
		// 0이면 개장일 1이면 휴장일
		int flag = 0;
		
		//평일인경우
		if(dayOfWeekNumber!=6 || dayOfWeekNumber!=7) {
			// 장 개장시간은 13시부터 21시까지
			for(int i=0;i<list.size();i++) {
				if(monthday.equals(list.get(i))) {
					flag = 1;
					break;
				}
			}
			if(hour>=13 && hour<21 && flag == 0) {
				return stockDAO.selectNowStock(stockVO2);
			}
			else {
				return stockDAO.selectLastStock(symbol);
			}
		}else {
			return stockDAO.selectLastStock(symbol);
		}


	}
	
}
