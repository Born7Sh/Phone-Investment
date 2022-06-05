package org.tukorea.pi.persistence;

import org.apache.ibatis.session.SqlSession;
import org.tukorea.pi.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.tukorea.pi.mapper.userMapper";
	
	public UserVO readEmail(String userName) throws Exception{
		UserVO userVO = new UserVO();
		userVO = sqlSession.selectOne(namespace+".selectbyUserName",userName);
		return userVO;
	}
	
	
	public void insertUser(UserVO userVO) throws Exception{
		userVO.setPassword("{noop}" + userVO.getPassword());
		sqlSession.insert(namespace+".insert",userVO); 
	}
	
	public void deleteUser(String email) throws Exception{
		sqlSession.delete(namespace+".deleteUser",email); 
	}
}
