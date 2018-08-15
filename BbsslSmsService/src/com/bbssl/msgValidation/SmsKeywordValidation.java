package com.bbssl.msgValidation;

import java.util.List;

public class SmsKeywordValidation {
	
	public String keywordValidation(String phoneNumber,String msg,String actno)
	{
		KeyWordsConstants keyConstants=new KeyWordsConstants();
		List<String> keyList=keyConstants.keywordList();
		String keyWord="Failed";
		if(msg.contains("{")){
			msg=msg.replace("{", "");
		}
		if(msg.contains("}")){
			msg=msg.replace("}", "");
		}
		
		for (String key : keyList) {
			
			if(key.equals(msg) || key==msg)
			{
				
				keyWord="Ok";
			}
		}
		
		return keyWord;
		
	}

}
