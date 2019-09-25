package com.bridgelabz.fundooapp.util;

public class GenerateURL {
	
	public static String getUrl(String subDirectory,long id)
	{
		return "http://localhost:4200/" + subDirectory + "/" + TokenUtil.generateToken(id);
	}

}
