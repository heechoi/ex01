package com.dgit.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgit.domain.LoginDTO;
import com.dgit.domain.MemberVO;
import com.dgit.service.MemberService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public void loginGet(){
		
	}
	
	@RequestMapping(value="/loginPost",method=RequestMethod.POST)
	public void loginPost(LoginDTO dto,Model model) throws Exception{
		logger.info("[loginPost]--------");
		logger.info(dto.toString());
		
		MemberVO vo = service.readWidthPw(dto.getUserid(), dto.getUserpw());
		
		if(vo==null){
			logger.info("user 없음.......");
			logger.info("loginPost   return......");
			return;
		}
		//membervo에는 많은 정보만 가지고 있으므로 dto를 따로 만들어서 일부정보만 session에 저장
		dto.setUsername(vo.getUsername());
		dto.setUserpw("");
		model.addAttribute("loginDto",dto);
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logoutGet(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/";
	}
	
}
