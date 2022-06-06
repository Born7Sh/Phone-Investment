package org.tukorea.pi.service;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.tukorea.pi.controller.StockRestController;
import org.tukorea.pi.domain.StockVO;
import org.tukorea.pi.domain.StockVO2;
import org.tukorea.pi.persistence.StockDAO;


@ComponentScan({"org.tukorea.pi.persistence"})
@Service("StockService")
public class StockServiceImpl implements StockService {
	@Autowired
	private StockDAO stockDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(StockRestController.class);
	
	//2022 휴장일 리스트
	public final List<String> list = new ArrayList<String>(10) {
		{
			add("20220117"); add("20220221");  add("20220415"); add("20220530"); add("20220620");
			add("20220704"); add("20220905");  add("20221124"); add("20221125"); add("20221226");
		}
	};
	
	
	public StockVO readNowStock(String symbol) throws Exception{
		LocalDateTime now = LocalDateTime.now();
		
		//실제 주식날짜 기준 얼마나 전날로 할지
		int mday = 101;

		//어느 가상날짜로 지정할지
		String monthmday = now.minusDays(mday).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		// 월요일이 1
		DayOfWeek dayOfWeek = now.getDayOfWeek();
		int dayOfWeekNumber = dayOfWeek.getValue();
		// 0이면 개장일 1이면 휴장일
		int flag = 0;
		int hour = now.getHour();
		
		String vdate = offHours(mday);
		//db 테이블 이름 now.minusDays(mday).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String date = "stock_" + vdate;
		logger.info(date);
		StockVO2 stockVO2 = new StockVO2();
		stockVO2.setSymbol(symbol);
		stockVO2.setTable_name(date);
		stockVO2.setMday(mday);
		
		//평일인경우
		if(dayOfWeekNumber!=6 || dayOfWeekNumber!=7) {
			//휴장일인지
			for(int i=0;i<list.size();i++) {
				if(monthmday.equals(list.get(i))) {
					flag = 1;
					break;
				}
			}
			// 장 개장시간은 13시부터 21시까지
			if(hour>=13 && hour<21 && flag == 0) {
				logger.info("장시간");
				return stockDAO.selectNowStock(stockVO2);
			}
			else {
				logger.info("장외시간");
				return stockDAO.selectLastStock(stockVO2);
			}
		}else {
			logger.info("장외시간");
			return stockDAO.selectLastStock(stockVO2);
		}
	}
	
	public StockVO readMyStock() throws Exception{
		int mday = 101;
		
		String vdate = offHours(mday);
		//db 테이블 이름 now.minusDays(mday).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String date = "stock_" + vdate;
		
		StockVO2 stockVO2 = new StockVO2();
		stockVO2.setTable_name(date);
		stockVO2.setMday(mday);
		
		return stockDAO.selectMyStock(stockVO2);
	}
	
	//장외시간 날짜 반환
	public String offHours(int mday) throws Exception{
		LocalDateTime now = LocalDateTime.now();
		for(int j=0; j<10; j++) {
			int flag = 0;
			String monthmday = now.minusDays(mday).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			DayOfWeek dayOfWeek = now.minusDays(mday).getDayOfWeek();
			int dayOfWeekNumber = dayOfWeek.getValue();
			logger.info(monthmday);
			//휴장일이면
			for(int i=0;i<list.size();i++) {
				if(monthmday.equals(list.get(i))) {
					mday += 1;
					flag = 1;
				}
			}
			if(flag == 1) {	//휴장일이면
				logger.info("휴장일");
				continue;
			}
			//주말이면
			if(dayOfWeekNumber==6 || dayOfWeekNumber==7) {
				logger.info("주말");
				mday+=1;
				continue;
			}else {
				return now.minusDays(mday).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			}
		}
		return now.minusDays(mday).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
}
