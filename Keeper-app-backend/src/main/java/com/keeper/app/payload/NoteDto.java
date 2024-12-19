package com.keeper.app.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NoteDto {
	
	
	private int id;
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	
	private UserDto user;

	
	@JsonIgnore
	public UserDto getUser() {
		return user;
	}

	@JsonProperty
	public void setUser(UserDto user) {
		this.user = user;
	}

	
	

}
