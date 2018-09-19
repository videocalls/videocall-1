package com.coderby.myapp.hr.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderby.myapp.hr.model.EmpVO;
import com.coderby.myapp.hr.service.IEmpService;
import com.coderby.myapp.hr.service.ClientService;
import com.coderby.myapp.hr.model.ClientVO;

@Controller
public class EmpController {

	@Autowired
	IEmpService empService;

	@Autowired
	ClientService clntServiceImpl;

	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);

	@RequestMapping(value="/hr/count")
	public String empCount(
		@RequestParam(value="deptid", required=false, defaultValue="0") int deptid, 
		Model model) {
		if(deptid==0) {
			model.addAttribute("count", empService.getEmpCount());
		}else {
			model.addAttribute("count", empService.getEmpCount(deptid));
		}
		return "hr/count";
	}

	@RequestMapping(value={"/hr", "/hr/list"})
	public String getAllEmps(Model model) {
		List<EmpVO> empList = empService.getEmpList();
		model.addAttribute("empList", empList);
		return "hr/list";
	}
	
	@RequestMapping(value="/hr/{employeeId}")
	public String getEmpInfo(@PathVariable int employeeId, Model model) {
		EmpVO emp = empService.getEmpInfo(employeeId);
		model.addAttribute("emp", emp);
		return "hr/view";
	}
	
	@RequestMapping(value="/hr/insert", method=RequestMethod.GET)
	public String insertEmp(Model model) {
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("managerList", empService.getAllManagerId());
		return "hr/insertform";
	}
	
	@RequestMapping(value="/hr/insert", method=RequestMethod.POST)
	public String insertEmp(EmpVO emp, Model model) {
		empService.insertEmp(emp);
		return "redirect:/hr";
	}
	
	@RequestMapping(value="/hr/update", method=RequestMethod.GET)
	public String updateEmp(int empid, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empid));
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("managerList", empService.getAllManagerId());
		return "hr/updateform";
	}
	
	@RequestMapping(value="/hr/update", method=RequestMethod.POST)
	public String updateEmp(EmpVO emp, Model model) {
		empService.updateEmp(emp);
		return "redirect:/hr/"+emp.getEmployeeId();
	}
	
	@RequestMapping(value="/hr/delete", method=RequestMethod.GET)
	public String deleteEmp(int empid, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empid));
		return "hr/deleteform";
	}

	@RequestMapping(value="/hr/delete", method=RequestMethod.POST)
	public String deleteEmp(int empid, String email, Model model) {
		empService.deleteEmp(empid, email);
		return "redirect:/hr";
	}
	
	@RequestMapping(value = "/ivst", method = RequestMethod.GET)
	public String ivst(Locale locale, Model model) {
		logger.info("Welcome ivst! The client locale is {}.", locale);
		return "hr/ivstpsnlanly";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String inputmember(Locale locale, Model model) {
		logger.info("Welcome register! The client locale is {}.", locale);
		return "hr/register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome login! The client locale is {}.", locale);
		return "hr/login";
	}

	@RequestMapping(value="/list" , method = RequestMethod.GET)
	public String getAllClient(Model model) {
		List<ClientVO> ClientList = clntServiceImpl.getClientList();
		model.addAttribute("ClientList", ClientList);
		return "list";
	}
}//end class