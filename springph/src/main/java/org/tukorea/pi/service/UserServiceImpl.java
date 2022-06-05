package org.tukorea.pi.service;

import org.tukorea.pi.domain.UserVO;
import org.tukorea.pi.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({"org.tukorea.pi.persistence"})
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
	public void signup(UserVO userVO) throws Exception {
		userDAO.insertUser(userVO);
	}
	
	public void deleteUser(String email) throws Exception {
		userDAO.deleteUser(email);
	}
}
