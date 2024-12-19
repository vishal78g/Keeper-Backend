package com.keeper.app.payload;

import java.util.ArrayList;
import java.util.List;

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
public class UserDto {
	
	private int id;
	@NotEmpty(message = "Name Required !")
	private String name;
	@NotEmpty(message = "Email Required !")
	private String email;
	@NotEmpty(message = "Password Required !")
	private String password;
	
	private List<NoteDto> notes=new ArrayList<>();
	
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
