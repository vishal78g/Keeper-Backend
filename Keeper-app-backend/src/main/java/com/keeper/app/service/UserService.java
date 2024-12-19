package com.keeper.app.service;

import com.keeper.app.payload.UserDto;

public interface UserService {
	
	
	//create
	public UserDto registerUser(UserDto userDto);

	//update
	public UserDto updateUser(Integer id, UserDto userDto);
	
	//get
	public UserDto getUser(Integer id);
	
	//delete 
	public boolean deleteUser(Integer id);
	
	public boolean isUserRegistered(String email);
	
	public UserDto getUserByEmail(String email);
	
	public String verify(UserDto user);
	
}
