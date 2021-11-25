package com.booknotes.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booknotes.base.model.AuthenticationRequest;
import com.booknotes.base.model.AuthenticationResponse;
import com.booknotes.base.service.JwtService;
import com.booknotes.base.service.UserService;
import com.booknotes.base.model.UserModel;
 

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/booknotes/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
			@RequestParam(value = "test", defaultValue = "false") boolean test) throws Exception {
		System.out.println("value of test :  " + test);
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}	
		catch (Exception e) {
			throw new Exception("Incorrect username or password", e);
		}	
		//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
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		UserModel user = new UserModel();
		user.setUserName(authenticationRequest.getUsername());
		final UserModel userModel = userService.getUserByNamePassword(user);
		return ResponseEntity.ok(new AuthenticationResponse(jwt, userModel.getId()));
	}
	

}
