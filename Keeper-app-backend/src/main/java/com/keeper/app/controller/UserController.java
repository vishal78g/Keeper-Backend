package com.keeper.app.controller;

//new one

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keeper.app.payload.LoginUser;
import com.keeper.app.payload.UserDto;
import com.keeper.app.payload.ApiResponse;
import com.keeper.app.payload.JWTResponse;
import com.keeper.app.serviceImpl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	//register(Create)
	@PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
		UserDto registeredUser= this.userService.registerUser(userDto);
		if (registeredUser == null) {
        	return new ResponseEntity<ApiResponse>(new ApiResponse("Email already used !",false),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
	}
	
	//login
	@PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> login(@RequestBody LoginUser loginUser){
		if(userService.isUserRegistered(loginUser.getEmail())) {
    		UserDto currentUser= userService.getUserByEmail(loginUser.getEmail());
    		currentUser.setPassword(loginUser.getPassword());
    		String token= userService.verify(currentUser);
    		return new ResponseEntity<JWTResponse>(new JWTResponse(token,true,currentUser),HttpStatus.OK);
    	}else {
    		return new ResponseEntity<ApiResponse>(new ApiResponse("User not found",false),HttpStatus.BAD_REQUEST);
    	}
	}
	
	//get user
	
	//update
	
	//delete

}
