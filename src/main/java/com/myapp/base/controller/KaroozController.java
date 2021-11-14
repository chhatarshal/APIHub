package com.myapp.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.base.entity.Hero;
import com.myapp.base.model.AuthenticationRequest;
import com.myapp.base.model.AuthenticationResponse;
import com.myapp.base.model.KaroozModel;
import com.myapp.base.model.UserModel;
import com.myapp.base.service.JwtService;
import com.myapp.base.service.KaroozService;
import com.myapp.base.service.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/kar")
public class KaroozController {
	
Object o = new Object();
	@Autowired
	private UserService userService;
	 
	
	@Autowired
	private KaroozService karoozService;
	
	@PostMapping("/saveuser")
	public UserModel createUser(@RequestBody UserModel userModel) {		
		return userService.saveUser(userModel);		
	}
	
	@GetMapping("/getAllUsers")
	public List<UserModel> getAllUsers() {
		 		 
		return userService.getAllUsers();
	}
	
	@GetMapping("/getAllHeros")
	public List<Hero> getAllHeros() {	
		 
		return userService.getAllHeros();
	}
	
	@GetMapping("/getAllKaroozOfUser")
	public List<Hero> getAllKaroozOfUser() {		
		return userService.getAllHeros();
	}
	
	@PostMapping("/addUserInChain")
	public List<UserModel> addUserInChain(@RequestBody UserModel userModel) {		
		return userService.addUserInChain(userModel);
	}
	
	@GetMapping("/getAllChainedUser")
	public List<UserModel> getAllChained(@RequestParam Long userId) {		
		return userService.getAllChainedUser(userId);
	}
	
	@GetMapping("/buyKarooz")
	public KaroozModel buyKarooz(@RequestParam Long karooId, @RequestParam Long buyerId, @RequestParam Long parentId) {		
		return userService.buyKarooz(karooId, buyerId, parentId);
	}
	
	@GetMapping("/getAllKarooz")
	public List<KaroozModel> getAllKarooz() {		
		return karoozService.getAllKarooz();
	}
	
	@PostMapping("/addKaroozInChain")
	public KaroozModel addNewKarooz(@RequestBody KaroozModel karoozModel) {		
		return karoozService.addKarooz(karoozModel);
	}
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
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

		
		//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

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
