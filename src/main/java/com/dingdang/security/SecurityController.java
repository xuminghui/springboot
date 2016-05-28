package com.dingdang.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping(value="/")
	public String home(){
		return "/security/home";
	}
	@RequestMapping(value="/login")
	public String login(){
		return "/security/login";
	}
	
	@RequestMapping(value="/securityError")
	public String error(){
		System.out.println("error");
		return "/security/error";
	}
	@RequestMapping(value="/user")
	public String user(){
		System.out.println("error");
		return "/security/user";
	}
	@RequestMapping(value="/admin")
	public String admin(){
		System.out.println("error");
		return "/security/admin";
	}
	
}
