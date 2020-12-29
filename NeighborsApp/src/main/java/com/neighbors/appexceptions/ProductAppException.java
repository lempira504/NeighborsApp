package com.neighbors.appexceptions;

public class ProductAppException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ProductAppException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public ProductAppException(String message)
	{
		super(message);
	}

}
