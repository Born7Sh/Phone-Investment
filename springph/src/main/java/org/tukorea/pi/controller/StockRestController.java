package org.tukorea.pi.controller;


import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
        String ip = getClientIP();
		logger.info("ip : {}  /stock/{} REST-API GET method called. then method executed.",ip, symbol);
		StockVO calendar = stockService.readNowStock(symbol);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		return new ResponseEntity<>(calendar, headers, HttpStatus.OK);
	}
	
	// 내가 가지고 있는 주식 확인
	@RequestMapping(value = "/mylist", method = RequestMethod.GET)
	public ResponseEntity<StockVO> readMyStock() throws Exception {
        String ip = getClientIP();
		logger.info("ip : {}  /mylist REST-API GET method called. then method executed.",ip);
	
		StockVO stock = stockService.readMyStock();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		return new ResponseEntity<>(stock, headers, HttpStatus.OK);
	}
	
    public String getClientIP() throws Exception {

        String ip = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-RealIP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
	
}
