package org.tukorea.pi.controller;


import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tukorea.pi.domain.StockVO;
import org.tukorea.pi.service.StockService;

@RestController
@ComponentScan({"org.tukorea.pi.service"})
@RequestMapping(value="/stock")
public class StockRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(StockRestController.class);

	@Autowired
	private StockService stockService;
	
	// 현재 해당하는 주식의 시세를 조회하는 컨트롤러
	@RequestMapping(value = "/{symbol}", method = RequestMethod.GET)
	public ResponseEntity<StockVO> readNowStock(@PathVariable String symbol) throws Exception {
		logger.info(" /stock/{} REST-API GET method called. then method executed.",symbol);
		StockVO calendar = stockService.readNowStock(symbol);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		return new ResponseEntity<>(calendar, headers, HttpStatus.OK);
	}
	
}
