package com.bbssl;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



@Controller
public class RaedXmlController {

	@RequestMapping("/hello")

	  public ModelAndView helloWorld() {  
        String message = "";     
			try {
				File fXmlFile = new File("C:\\Users\\bbssl\\Desktop\\read.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("Employee");

				for(int loop=0;loop < nList.getLength();loop++ ) 
				{
				Node nNode1 = nList.item(loop);
				Element eElement1 = (Element) nNode1;
				message =message+"|"+ eElement1.getElementsByTagName("name").item(0).getTextContent();
				}
				
			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}

        return new ModelAndView("hellopage", "message", message);  
    }
	
    
}
