package ssg.com.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ssg.com.a.dto.MemberDto;
import ssg.com.a.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService service;
	
	@GetMapping("test")
	public String test() {
		System.out.println("MemberController test() " + new Date());
		
		return "테스트 성공!";
	}
	
	@PostMapping("idcheck")
	public String idcheck(String id) {
		System.out.println("MemberController idcheck() " + new Date());
		
		boolean isS = service.idcheck(id);
		if(isS) {
			return "NO";
		}		
		return "YES";
	}
	
	@PostMapping("addmember")
	public String addmember(MemberDto mem) {
		System.out.println("MemberController addmember() " + new Date());
		
		boolean isS = service.addmember(mem);
		if(!isS) {
			return "NO";
		}
		
		return "YES";
	}
	
	@PostMapping("login")
	public MemberDto login(MemberDto mem) {
		System.out.println("MemberController login() " + new Date());
		
		return service.login(mem);
	}
}








