package com.cg.springmvcone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cg.springmvcone.dto.Employee;
import com.cg.springmvcone.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeservice;
	
	@RequestMapping(value="/getemployee",method=RequestMethod.GET)
	public String myEmployee(@ModelAttribute("my") Employee emp,
			Map<String,Object> model){
		List<String> myQul=new ArrayList<String>();
		myQul.add("BSC");
		myQul.add("MCA");
		myQul.add("BE");
		myQul.add("ME");
		model.put("myq",myQul);
		return "employee";
		
	}
	@RequestMapping(value="/addemployee",method=RequestMethod.POST)
	public String addEmployeeDataBase(
			@Valid@ModelAttribute("my") Employee emp,
			BindingResult result
			,Map<String,Object> model
			){

		if(result.hasErrors()){
			
			List<String> myQul=new ArrayList<String>();
			myQul.add("BSC");
			myQul.add("MCA");
			myQul.add("BE");
			myQul.add("ME");
			model.put("myq",myQul);
			return "employee";
		}
		employeeservice.addEmployee(emp);
		return "redirect:/showall";
		
	}
	@RequestMapping(value="/showall",method=RequestMethod.GET)
   public ModelAndView showAllData(){
   List<Employee> empList=employeeservice.showAllEmployee();		
	return new ModelAndView("show","empdata",empList);
	
}
	@RequestMapping(value="/showone",method=RequestMethod.GET)
	public String showSingle(@ModelAttribute("aa") Employee emp){
		return "singleemployee";
		
	}
	@RequestMapping(value="/single",method=RequestMethod.POST)
	public ModelAndView getSingleEmployee
	(@ModelAttribute("aa") Employee emp) {
		List<Employee> empOne=employeeservice.searchEmployee(emp);
		return new ModelAndView("show","empdata",empOne);
		
	}
}
