package com.coderby.myapp.member.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coderby.myapp.member.model.MemberVO;
import com.coderby.myapp.member.service.IMemberService;

@Controller
public class MemberController {
	static final Logger logger = Logger.getLogger(MemberController.class);

	@Autowired
	IMemberService memberService;

	@RequestMapping(value="/member/insert", method=RequestMethod.GET)
	public String joinForm() {
		return "/member/form";
	}
	
	@RequestMapping(value="/member/insert", method=RequestMethod.POST)
	public String memberInsert(MemberVO member, Model model, HttpSession session) {
		memberService.insertMember(member);
		session.invalidate();
		return "/index";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public String login(HttpSession session, Model model) {
		String url = (String)session.getAttribute("url");
		String param = (String)session.getAttribute("param");

		session.setAttribute("url", url);
		session.setAttribute("param", param);

		String clientId = "RqJsLSrddui54UiouGVM";//애플리케이션 클라이언트 아이디값";
	    String redirectURI;
		try {
			redirectURI = URLEncoder.encode("http://javaspecialist.co.kr/naverlogin", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    
	    model.addAttribute("apiURL", apiURL);
	    session.setAttribute("state", state);

		return "/member/login";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(String userid, String password, HttpSession session, Model model) {

		MemberVO member = memberService.selectMember(userid);
		if(member != null) {
			String dbPassword = member.getPassword();
	
			if(dbPassword == null) {
				//아이디가 없음
				model.addAttribute("message", "NOT_VALID_USER");
			}else {
				//아이디 있음
				if(dbPassword.equals(password)) {
					//비밀번호 일치
					session.setAttribute("login", "Custom");
					session.setAttribute("userid", userid);
					session.setAttribute("name", userid);
					session.setAttribute("email", member.getEmail());
	
					if(session!=null && session.getAttribute("url")!=null) {
						String param = (String)session.getAttribute("param");
						if(param == null) {
							return "redirect:"+(String)session.getAttribute("url");
						}else {
							return "redirect:"+(String)session.getAttribute("url") + "?" + param;	
						}
					}
					return "/member/login";
				}else {
					//비밀번호 불일치
					model.addAttribute("message", "WRONG_PASSWORD");
				}
			}
		}else {
			model.addAttribute("message", "USER_NOT_FOUND");
		}

		session.removeAttribute("login");
		session.removeAttribute("userid");
		session.removeAttribute("name");
		session.removeAttribute("email");
		return "/member/login";
	}
	
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request) {
		String url = (String)session.getAttribute("url");
		String param = (String)session.getAttribute("param");
		session.invalidate(); //로그아웃
		session = request.getSession();
		session.setAttribute("url", url);
		session.setAttribute("param", param);
		if(session!=null && session.getAttribute("url")!=null) {
			if(param == null) {
				return "redirect:"+(String)session.getAttribute("url");
			}else {
				return "redirect:"+(String)session.getAttribute("url") + "?" + param;	
			}
		}else {
			return "/index";
		}
	}
	
	@RequestMapping(value="/member/update", method=RequestMethod.GET)
	public String updateMember(HttpSession session, Model model) {
		String userid = (String)session.getAttribute("userid");
		if(userid != null && !userid.equals("")) {
			MemberVO member = memberService.selectMember(userid);
			model.addAttribute("member", member);
			model.addAttribute("message", "UPDATE_USER_INFO");
			return "/member/update";
		}else {
			//userid가 세션에 없을 경우 (로그인 하지 않았을 경우)
			model.addAttribute("message", "NOT_LOGIN_USER");
			return "/member/login";
		}
	}
	
	@RequestMapping(value="/member/update", method=RequestMethod.POST)
	public String updateMember(MemberVO member, HttpSession session, Model model) {
		try{
			memberService.updateMember(member);
			model.addAttribute("message", "UPDATED_MEMBER_INFO");
			model.addAttribute("member", member);
			return "/member/login";
		}catch(Exception e){
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			return "/member/error";
		}
	}
	
	@RequestMapping(value="/member/delete", method=RequestMethod.GET)
	public String deleteMember(HttpSession session, Model model) {
		String userid = (String)session.getAttribute("userid");
		if(userid != null && !userid.equals("")) {
			MemberVO member = memberService.selectMember(userid);
			model.addAttribute("member", member);
			return "/member/delete";
		}else {
			//userid가 세션에 없을 경우 (로그인 하지 않았을 경우)
			model.addAttribute("message", "NOT_LOGIN_USER");
			return "/member/login";
		}
	}
	
	@RequestMapping(value="/member/delete", method=RequestMethod.POST)
	public String deleteMember(String password, HttpSession session, Model model) {
		try {
			MemberVO member = new MemberVO();
			member.setUserid((String)session.getAttribute("userid"));
			String dbpw = memberService.getPassword(member.getUserid());
			if(password != null && password.equals(dbpw)) {
				member.setPassword(password);
				memberService.deleteMember(member) ;
				session.invalidate();//삭제되었으면 로그아웃 처리
				return "/member/login";
			}else {
				model.addAttribute("message", "WRONG_PASSWORD");
				return "/member/delete";
			}
		}catch(Exception e){
			model.addAttribute("message", "DELETE_FAIL");
			e.printStackTrace();
			return "/member/delete";
		}
	}
	

}

