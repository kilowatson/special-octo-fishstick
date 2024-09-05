package com.tools.controllers;

import java.util.Base64;

public class Base64Controller{
	public static String base64Encode(String stringToBeEncoded){
		return Base64.getEncoder().encodeToString(stringToBeEncoded.getBytes());
	}

	public static String base64Decode(String stringToBeDecoded){
		byte[] decodedText = Base64.getDecoder().decode(stringToBeDecoded);
		return new String(decodedText);
	}
}
