package com.keeper.app.payload;


import com.keeper.app.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class JWTResponse {
	
	private String token;
	
	private boolean success;
	
	private UserDto user;
	

}
