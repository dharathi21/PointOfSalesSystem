package com.wipro.pos.util;

public class InvalidInputException extends Exception {
	
	public InvalidInputException(String message)  {
		super(message);
	}
	@Override
	public String toString() {
		return getMessage();
	}

}
