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

public class FinRequest {

	
	
	public static void finRequest(int pNumber,String msgType,String msg,String acctnum ) {



		try {

			//CreditCheckInputDetails input = getF_IN_CreditCheckInput();

		//	CreditCheckoutputDetails output = getF_OUT_CreditCheckOutput();



			HttpURLConnection httpConnection = createRequest(msgType,msg,acctnum );



			if (httpConnection.getResponseCode() == java.net.HttpURLConnection.HTTP_OK) {
				System.out.println("connection ok");
				boolean noMatchFlag = readSuccessResponse(httpConnection);

		//		setF_OUT_noMatchFlag(noMatchFlag);

			} else {

				// Check for the Failure Response Code

	//			String failureResponseCode = readFailureResponse(httpConnection, output);



	//			if (failureResponseCode.equals(LZGB_PTY_EWSConsumerServiceConstants.ERROR_CODE_TIMEOUT)) {

				/*	for (int retryCount = 1; retryCount < LZGB_PTY_EWSConsumerServiceConstants.RETRY_COUNT; retryCount++) {

						System.out.println("RETRY COUNT : " + retryCount);

						httpConnection = createRequest(input);

						System.out.println("RETRY: Response Code for Request ["

								+ retryCount + "]: "

								+ httpConnection.getResponseCode());

						if (httpConnection.getResponseCode() == java.net.HttpURLConnection.HTTP_OK) {

							boolean noMatchFlag = readSuccessResponse(

									httpConnection, output);

							setF_OUT_noMatchFlag(noMatchFlag);

							break;

						}

					}*/

		//		}

			}



		} catch (IOException e) {

			System.out.println(e);

		}



	}



	/**

	 * Read Failure Response Method

	 *

	 * @param errorResponseString

	 * @param errorResponseXML

	 * @param errorCode

	 * @return EquifaxErrorCode

	 */

/*

	private static String readFailureResponse(HttpURLConnection httpConn,CreditCheckoutputDetails output) {



		String errorResponseString = "";

		String errorResponseXML = "";

		String errorCode = "";



		try {

			int responseCode = httpConn.getResponseCode();



			InputStream errorStream = httpConn.getErrorStream();



			System.out.println("Response Code: " + responseCode);



			if (responseCode == 500) {



				InputStreamReader isr = new InputStreamReader(errorStream);

				BufferedReader in = new BufferedReader(isr);



				while ((errorResponseString = in.readLine()) != null) {

					errorResponseXML = errorResponseXML + errorResponseString;



					System.out.println("Error Response XML: " + errorResponseXML);



				}



				Document xmlDoc = loadXMLString(errorResponseXML);

				if(xmlDoc.getElementsByTagName("detail") != null){

				NodeList nList1 = xmlDoc.getElementsByTagName("detail");

				Node nNode1 = nList1.item(0);



				Element eElement1 = (Element) nNode1;

				errorCode = eElement1.getElementsByTagName("code").item(0)

						.getTextContent();



				System.out.println("Error code: " + errorCode);

				output.setEquifaxErrorCode(errorCode);

				}

			}

		} catch (IOException e) {

			System.out.println(e);

		} catch (Exception e) {



			System.out.println(e);

		}



		httpConn.disconnect();

		return errorCode;

	}*/



	/**

	 * Read Success Response Method

	 *

	 * @param responseString

	 * @param responseXML

	 * @param creditScore

	 * @return noMatchFlag

	 */



	private static boolean readSuccessResponse(HttpURLConnection httpCon) {



		String responseString = "";

		String responseXML = "";

		String creditScore = "";

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


			// no match check

			/*if (xmlDoc.getElementsByTagName("addressMatchStatus") != null) {



				NodeList addressMatchStatusNode = xmlDoc

						.getElementsByTagName("addressMatchStatus");



				String addressMatchStatusValue = addressMatchStatusNode.item(0)

						.getTextContent();



				if (addressMatchStatusValue.equalsIgnoreCase("noMatch")) {



					System.out.println("no address found");

					noMatchFlag = true;



				}



				else {



					NodeList nList1 = xmlDoc.getElementsByTagName("scores");

					if (nList1 != null) {

						Node nNode1 = nList1.item(0);

						Element eElement1 = (Element) nNode1;



						creditScore = eElement1.getElementsByTagName("value")

								.item(0).getTextContent();



						int creditScoreint = Integer.parseInt(creditScore);

						System.out.println("Score " + creditScore);



			//			output.setScore(creditScoreint);

					}



			//		output.setResponseXML(responseXML);

				}

			}*/

		} catch (IOException e) {

			System.out.println(e);

		} catch (Exception e) {



			System.out.println(e);

		}



		return noMatchFlag;



	}





	/**

	 * web service trigger

	 * @param userName

	 * @param password

	 * @param dateOfBirth

	 * @param forename

	 * @param surname

	 * @param postCode

	 * @return HttpURLConnection

	 */



	private static HttpURLConnection createRequest(String msgType,String msg,String acctnum ) {

		HttpURLConnection httpConn = null;
		/*String userName = inputdata.getUserName();
		String password = inputdata.getPassword();
		Date dateOfBirth = inputdata.getDateOfBirth();
		String doorNumber = inputdata.getDoorNumber();
		String forename = inputdata.getForename();
		String surname = inputdata.getSurname();
		String postCode = inputdata.getPostCode();
*/


		ByteArrayOutputStream bout = new ByteArrayOutputStream();


/*
		String xmlInput = " <?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		+ "<FIXML xsi:schemaLocation=\"http://www.finacle.com/fixml AcctInq.xsd\" xmlns=\"http://www.finacle.com/fixml\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Header>"
		+ "<RequestHeader>"
		+ "<MessageKey>"
		+ "<RequestUUID>Req_1527677292996</RequestUUID>"
		+ "<ServiceRequestId>AcctInq</ServiceRequestId>"
		+ "<ServiceRequestVersion>10.2</ServiceRequestVersion>"
		+ "<ChannelId>COR</ChannelId>"
		+ "<LanguageId></LanguageId>"
		+ "</MessageKey>"
		+ "<RequestMessageInfo>"
		+ "<BankId>01</BankId>"
		+ "<TimeZone></TimeZone>"
		+ "<EntityId></EntityId>"
		+ "<EntityType></EntityType>"
		+ "<ArmCorrelationId></ArmCorrelationId>"
		+ "<MessageDateTime>2018-04-30T16:18:12.996</MessageDateTime>"
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
		+ "<AcctInqRequest>"
		+ "<AcctInqRq>"
		+ "<AcctId>"
		+ "<AcctId>CASSANPR2</AcctId>"
		+ "</AcctId>"
		+ "</AcctInqRq>"
		+ "</AcctInqRequest>"
		+ "</Body>"
		+ "</FIXML>";*/
		
		
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


