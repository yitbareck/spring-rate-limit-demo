package net.manyahl.apiratelimitdemo.exceptions;

@SuppressWarnings("serial")
public class TooManyRequestException extends RuntimeException{
	public TooManyRequestException(String message, Throwable cause) {
		super(message,cause);
	}
	
	public TooManyRequestException(String message) {
		super(message);
	}
	
	public TooManyRequestException(Throwable cause) {
		super(cause);
	}
}
