package com.bbssl.spring.model;

import java.util.List;

public class SmsKeywordValidation {
	
	public String keywordValidation(int phoneNumber,String msg,String actno)
	{
		KeyWordsConstants keyConstants=new KeyWordsConstants();
		List<String> keyList=keyConstants.keywordList();
		String keyWord="Failed";
		
		for (String key : keyList) {
			
			if(key.equals(msg) || key==msg)
			{
				
				keyWord="Ok";
			}
		}
		
		return keyWord;
		
	}

}
