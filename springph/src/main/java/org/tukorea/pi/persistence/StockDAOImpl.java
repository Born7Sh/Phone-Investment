package org.tukorea.pi.persistence;


import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tukorea.pi.domain.StockVO;
import org.tukorea.pi.domain.StockVO2;


@Repository
public class StockDAOImpl implements StockDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.tukorea.pi.mapper.stock";
	
	public StockVO selectNowStock(StockVO2 symbol) throws Exception{
		return sqlSession.selectOne(namespace+".selectNowStock", symbol);
	}
	public StockVO selectLastStock(StockVO2 symbol) throws Exception{
		return sqlSession.selectOne(namespace+".selectLastStock", symbol);
	}

}
