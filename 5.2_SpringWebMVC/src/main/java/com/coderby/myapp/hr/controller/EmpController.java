package com.coderby.myapp.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderby.myapp.hr.service.IEmpService;

@Controller
public class EmpController {

	@Autowired
	IEmpService empService;
	
	@RequestMapping(value="/hr/count")
	public String empCount(
			@RequestParam(value="deptid", required=false, defaultValue="0")int deptid, Model model) {
		if(deptid==0) {
			model.addAttribute("count", empService.getEmpCount());
		}else {
			model.addAttribute("count", empService.getEmpCount(50));
		}
		return "hr/count";
	}
}
