package org.tukorea.pi.service;

import org.tukorea.pi.domain.UserVO;

public interface UserService {
	public void signup(UserVO userVO) throws Exception;
	public void deleteUser(String email) throws Exception;
}
