package org.tukorea.pi.service;

import java.util.ArrayList;


import org.tukorea.pi.domain.UserVO;
import org.tukorea.pi.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({"org.tukorea.pi.persistence"})
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDAO userDAO;

	private static PasswordEncoder passwordEncoder;
	
	public UserDetails loadUserByUsername(String userName) {
		UserVO user = new UserVO();

		try {
			user = userDAO.readEmail(userName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRolename());
	
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
		
		
	}



}
