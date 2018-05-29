package com.bbssl.SpringClient;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.bbssl.spring.controller.RestURIConstants;
import com.bbssl.spring.model.Pojo;

public class TestClient {

	public static final String SERVER_URI = "http://localhost:8080/SpringRestExample";
	
	public static void main(String args[]){
		
		testGetDummyEmployee();
		System.out.println("*****");
		testCreateEmployee();
		System.out.println("*****");
		testGetEmployee();
		System.out.println("*****");
		testGetAllEmployee();
	}

	private static void testGetAllEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<Employee> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		List<LinkedHashMap> emps = restTemplate.getForObject(SERVER_URI+RestURIConstants.GET_ALL_EMP, List.class);
		System.out.println(emps.size());
		for(LinkedHashMap map : emps){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",CreatedDate="+map.get("createdDate"));;
		}
	}

	private static void testCreateEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Pojo emp = new Pojo();
		emp.setPhoneNo(1);emp.setName("Pankaj Kumar");
		Pojo response = restTemplate.postForObject(SERVER_URI+RestURIConstants.CREATE_EMP, emp, Pojo.class);
		printEmpData(response);
	}

	private static void testGetEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Pojo emp = restTemplate.getForObject(SERVER_URI+"/rest/emp/1", Pojo.class);
		printEmpData(emp);
	}

	private static void testGetDummyEmployee() {
		RestTemplate restTemplate = new RestTemplate();
		Pojo emp = restTemplate.getForObject(SERVER_URI+RestURIConstants.DUMMY_EMP, Pojo.class);
		printEmpData(emp);
	}
	
	public static void printEmpData(Pojo emp){
		//System.out.println("ID="+emp.getPhoneNo()+",Name="+emp.getName()+",CreatedDate="+emp.getCreatedDate());
	}
}