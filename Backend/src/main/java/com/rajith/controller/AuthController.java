package com.rajith.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.rajith.config.JwtProvider;
import com.rajith.exception.UserException;
import com.rajith.model.Cart;
import com.rajith.model.User;
import com.rajith.repository.UserRepository;
import com.rajith.request.LoginRequest;
import com.rajith.response.AuthResponse;
import com.rajith.service.CartService;
import com.rajith.service.CustomUserServiceImplementation;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	private CustomUserServiceImplementation customUserService;
	private CartService cartService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{
		String email=user.getEmail();
		String password=user.getPassword();
		String firstName=user.getFirstName();
		String lastName=user.getLastName();
		String role=user.getRole();
		
		User isEmailExist=userRepository.findByEmail(email);
		
		if(isEmailExist!=null) {
			throw new UserException("Email is Already Used With Another Account");
		}
		
		User createdUser=new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		createdUser.setRole(role);
		
		User savedUser=userRepository.save(createdUser);
		Cart cart=cartService.createCart(savedUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signup Success");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
		
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		
		Authentication authentication=authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signin Success");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
		
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails=customUserService.loadUserByUsername(username);
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid Username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}
