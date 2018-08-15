package com.bbssl.fin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Trigger Finacle webservice.
 * @author Satyajit
 */

public class FinRequest {
	
	/**
	 * Call Finacle webservice request and get response 
	 * @param pNumber
	 * @param msgType
	 * @param msg
	 * @param account Number
	 */
	
	public static void finRequest(String pNumber,String msgType,String msg,String acctnum ) {
		
		try {

			HttpURLConnection httpConnection = createRequest(msgType,msg,acctnum );

			if (httpConnection.getResponseCode() == java.net.HttpURLConnection.HTTP_OK) {
				System.out.println("connection ok");
				readSuccessResponse(httpConnection);

			} else {

				// Check for the Failure Response Code

			}

		} catch (IOException e) {

			System.out.println(e);
			
		}

	}

	/**
	 * Read Success Response Method
	 * @param responseString
	 * @param responseXML
	 */

	private static void readSuccessResponse(HttpURLConnection httpCon) {

		String responseString = "";
		String responseXML = "";
		boolean noMatchFlag = false;

		try {

			InputStreamReader isr = new InputStreamReader(httpCon.getInputStream());
			BufferedReader in = new BufferedReader(isr);

			System.out.println("test read response potocol");

			// Write the SOAP message response to a String.
			int count=1;
			
			while ((responseString = in.readLine()) != null) {

				responseXML = responseXML + responseString+"\n";
				count++;
			}
			
System.out.println("..................................Response...................................\n\n");
System.out.println(responseXML);

			Document xmlDoc = loadXMLString(responseXML);

		} catch (IOException e) {

			System.out.println(e);

		} catch (Exception e) {

			System.out.println(e);

		}

	}

	/**
	 * web service trigger
	 * @param msgType
	 * @param msg
	 * @param acctnum
	 * @return HttpURLConnection
	 */

	private static HttpURLConnection createRequest(String msgType,String msg,String acctnum ) {

		HttpURLConnection httpConn = null;

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		String xmlInput = " <?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		+ "<FIXML xsi:schemaLocation=\"http://www.finacle.com/fixml executeFinacleScript.xsd\" xmlns=\"http://www.finacle.com/fixml\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Header>"
		+ "<RequestHeader>"
		+ "<MessageKey>"
		+ "<RequestUUID>Req_1527751840883</RequestUUID>"
		+ "<ServiceRequestId>executeFinacleScript</ServiceRequestId>"
		+ "<ServiceRequestVersion>10.2</ServiceRequestVersion>"
		+ "<ChannelId>COR</ChannelId>"
		+ "<LanguageId></LanguageId>"
		+ "</MessageKey>"
		+ "<RequestMessageInfo>"
		+ "<BankId>ZM</BankId>"
		+ "<TimeZone></TimeZone>"
		+ "<EntityId></EntityId>"
		+ "<EntityType></EntityType>"
		+ "<ArmCorrelationId></ArmCorrelationId>"
		+ "<MessageDateTime>2018-04-31T13:00:40.883</MessageDateTime>"
		+ "</RequestMessageInfo>"
		+ "<Security>"
		+ "<Token>"
		+ "<PasswordToken>"
		+ "<UserId></UserId>"
		+ "<Password></Password>"
		+ "</PasswordToken>"
		+ "</Token>"
		+ "<FICertToken></FICertToken>"
		+ "<RealUserLoginSessionId></RealUserLoginSessionId>"
		+ "<RealUser></RealUser>"
		+ "<RealUserPwd></RealUserPwd>"
		+ "<SSOTransferToken></SSOTransferToken>"
		+ "</Security>"
		+ "</RequestHeader>"
		+ "</Header>"
		+ "<Body>"
		+ "<executeFinacleScriptRequest>"
		+ "<ExecuteFinacleScriptInputVO>"
		+ "<requestId>abc.scr</requestId>"
		+ "</ExecuteFinacleScriptInputVO>"
		+ "<executeFinacleScript_CustomData>"
		+ "<acctnum>"+acctnum+"</acctnum>"
		+ "<msgtype>"+msgType+"</msgtype>"
		+ "<msg>123</msg>"
		+ "</executeFinacleScript_CustomData>"
		+ "</executeFinacleScriptRequest>"
		+ "</Body>"
		+ "</FIXML>" ;

		System.out.println("XML Input" + xmlInput);

		try {

		//	URL url = new URL("https://bibm.bbssl.com:25000/FIUI/services/AcctInq.html");
			
			URL url = new URL("https://bibm.bbssl.com:25000/FISERVLET/fihttp");

			URLConnection connection = url.openConnection();

			httpConn = (HttpURLConnection) connection;
			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			bout.write(buffer);
			byte[] b = bout.toByteArray();

			String soapAction = "";

			// Set the appropriate HTTP parameters.

			httpConn.setRequestProperty("Content-Length",

					String.valueOf(b.length));

			httpConn.setRequestProperty("Content-Type",

					"text/xml; charset=utf-8");

			httpConn.setRequestProperty("SOAPAction", soapAction);

			httpConn.setRequestMethod("GET");

			httpConn.setDoOutput(true);

			httpConn.setDoInput(true);

			System.out.println("Test before potocol");

		//	System.setProperty("https.protocols", "TLSv1.2");

			System.out.println("Test after potocol");

			OutputStream out = httpConn.getOutputStream();

			System.out.println("Test response data");

			out.write(b);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return httpConn;

	}


	public static Document loadXMLString(String response) throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(response));

		return (Document) db.parse(is);

	}

}	


