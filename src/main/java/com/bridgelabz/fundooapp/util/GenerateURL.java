package com.bridgelabz.fundooapp.util;

public class GenerateURL {
	
	public static String getUrl(String subDirectory,long id)
	{
		return "http://localhost:8080/fundoo/user/" + subDirectory + "/" + TokenUtil.generateToken(id);
	}

}
