package org.tukorea.pi.controller;



import org.tukorea.pi.domain.AuthRequest;
import org.tukorea.pi.domain.UserVO;
import org.tukorea.pi.security.JwtUtil;
import org.tukorea.pi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@ComponentScan(basePackages = {"org.tukorea.pi.service","org.tukorea.pi.security"})
public class WelcomeController {
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
//	@GetMapping("/")
//	public String welcome() {
//	return "Welcome to javatechie !!";
//	}
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			System.out.println(authRequest.getUserName() + " " + authRequest.getPassword());
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword());
			System.out.println(token);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
//			ex.printStackTrace();
			throw new Exception("invalid username/password");
		}
		return jwtUtil.generateToken(authRequest.getUserName());
	}
	
	@PostMapping("/signup")
	public String signup(@RequestBody UserVO userVO) throws Exception{
		userService.signup(userVO);
		return "OK";
	}
}
