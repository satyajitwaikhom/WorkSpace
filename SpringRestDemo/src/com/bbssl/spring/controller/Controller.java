package com.bbssl.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbssl.spring.model.Pojo;
/**
 * Handles requests for the Employee service.
 */
@org.springframework.stereotype.Controller
public class Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	//Map to store employees, ideally we should use database
	Map<Integer, Pojo> empData = new HashMap<Integer, Pojo>();
	
	@RequestMapping(value = RestURIConstants.DUMMY_EMP, method = RequestMethod.GET)
	public @ResponseBody Pojo getDummyEmployee() {
		logger.info("Start getDummyEmployee");
		Pojo emp = new Pojo();
		emp.setPhoneNo(9999);
		emp.setName("Dummy");
		emp.setAccountNo("0001");
		empData.put(9999, emp);
		return emp;
	}
	
	@RequestMapping(value = RestURIConstants.GET_EMP, method = RequestMethod.GET)
//	public @ResponseBody Pojo getEmployee(@PathVariable("id") int empId,@PathVariable("msg") String msg,@PathVariable("actno") String actno ){
	public @ResponseBody String getEmployee(@PathVariable("id") int empId,@PathVariable("msg") String msg,@PathVariable("actno") String actno ){
		logger.info("Start getEmployee. ID="+empId);
		
		System.out.println("empId :"+empId);
		System.out.println("msg :"+msg);
		System.out.println("actno :"+actno);
		
		
		
//		return empData.get(empId);
		return "ok";
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_EMP, method = RequestMethod.GET)
	public @ResponseBody List<Pojo> getAllEmployees() {
		logger.info("Start getAllEmployees.");
		List<Pojo> emps = new ArrayList<Pojo>();
		Set<Integer> empIdKeys = empData.keySet();
		for(Integer i : empIdKeys){
			emps.add(empData.get(i));
		}
		return emps;
	}
	
	@RequestMapping(value = RestURIConstants.CREATE_EMP, method = RequestMethod.POST)
	public @ResponseBody Pojo createEmployee(@RequestBody Pojo emp) {
		logger.info("Start createEmployee.");
		empData.put(emp.getPhoneNo(), emp);
		return emp;
	}
	
	@RequestMapping(value = RestURIConstants.DELETE_EMP, method = RequestMethod.PUT)
	public @ResponseBody Pojo deleteEmployee(@PathVariable("id") int empId) {
		logger.info("Start deleteEmployee.");
		Pojo emp = empData.get(empId);
		empData.remove(empId);
		return emp;
	}
	
}