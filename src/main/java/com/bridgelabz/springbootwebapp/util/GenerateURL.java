package com.bridgelabz.springbootwebapp.util;

public class GenerateURL {
	
	public static String getUrl(String subDirectory,long id)
	{
		return "http://localhost:8080/fundoo/" + subDirectory + "/" + TokenUtil.generateToken(id);
	}

}
