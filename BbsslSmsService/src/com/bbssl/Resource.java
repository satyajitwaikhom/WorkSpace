package com.bbssl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.bbssl.fin.FinRequest;
import com.bbssl.msgValidation.SmsKeywordValidation;

import javax.ws.rs.PathParam;

@Path("/pullmessages&Mobile={number}&Msg={msg}+{actno}")
public class Resource {
	SmsKeywordValidation smskey=new SmsKeywordValidation();
	FinRequest finreq=new FinRequest();
	String keyresult="";
	@GET
	@Produces({"application/xml", "application/json"})
	public String sayPlainTextHello(@PathParam("number") String number,
		    @PathParam("msg") String msg , @PathParam("actno")  String actno ) {
		System.out.println(number);
		System.out.println(msg);
		System.out.println(actno);
		
		keyresult=smskey.keywordValidation(number, msg, actno);
		finreq.finRequest(number,msg,msg,actno);
		
		return keyresult;
	}


}
