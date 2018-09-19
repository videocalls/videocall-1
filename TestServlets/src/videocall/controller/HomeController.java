package com.koscom.videocall.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koscom.videocall.service.service;
import com.koscom.videocall.dao.Repository;
import com.koscom.videocall.model.ClientVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	service clntServiceImpl;
	@Autowired
	Repository Repo;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginform(Locale locale, Model model) {
		logger.info("Welcome loginform! The client locale is {}.", locale);
		ClientVO clntvo = new ClientVO();
		model.addAttribute("clntvo", clntvo);

		return "loginForm";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Locale locale, Model model) {
		logger.info("Welcome login! The client locale is {}.", locale);
		
		ClientVO clntvo = new ClientVO();
		model.addAttribute("clntvo", clntvo);
		
		return "login";
	}

	@RequestMapping(value = "/registerform", method = RequestMethod.GET)
	public String registerform(Locale locale, Model model) {
		logger.info("Welcome registerform! The client locale is {}.", locale);
		ClientVO clntvo = new ClientVO();
		model.addAttribute("clntvo", clntvo);

		return "registerForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(ClientVO clntvo, Locale locale, Model model) {
		
		logger.info("Welcome register! The client locale is {}.", locale);
		clntvo.setClntci("sdfjasjdlfjlkasjdf");
		clntvo.setClntid(clntServiceImpl.getNextClientId());
		System.out.println(clntvo.getClntid());
		System.out.println(clntvo.getClntci());
		System.out.println(clntvo.toString());
		clntServiceImpl.insertClient(clntvo);
		return "loginForm";
	}

	@RequestMapping(value = "/firmform", method = RequestMethod.GET)
	public String firmform(Locale locale, Model model) {
		logger.info("Welcome firmform! The client locale is {}.", locale);
		return "firmForm";
	}

	@RequestMapping(value = "/firm", method = RequestMethod.GET)
	public String firm(Locale locale, Model model) {
		logger.info("Welcome firm! The client locale is {}.", locale);
		return "questionForm";
	}

	@RequestMapping(value = "/questionform", method = RequestMethod.GET)
	public String questionform(Locale locale, Model model) {
		logger.info("Welcome questionform! The client locale is {}.", locale);
		return "questionForm";
	}
	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public String question(Locale locale, Model model) {
		logger.info("Welcome question! The client locale is {}.", locale);
		return "question";
	}

}
