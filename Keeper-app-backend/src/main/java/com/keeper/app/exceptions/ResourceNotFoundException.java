package com.keeper.app.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	
	String resourceNameString;
	String fieldName;
	long fieldValue;
	
	public ResourceNotFoundException(String resourceNameString, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceNameString,fieldName,fieldValue));
		this.resourceNameString = resourceNameString;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
