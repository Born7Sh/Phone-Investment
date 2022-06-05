package org.tukorea.pi.persistence;

import org.tukorea.pi.domain.UserVO;

public interface UserDAO {
	public UserVO readEmail(String userName) throws Exception;
	public void insertUser(UserVO userVO) throws Exception;
	public void deleteUser(String email) throws Exception;
	//public String readIP(String email) throws Exception;
}
