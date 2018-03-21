package com.dgit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.PageMaker;
import com.dgit.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
		
	@Autowired
	private BoardService service;
	//board/listAll이 되도록, void면 jsp파일명이 mapping과동일 다르게 하고 싶으면 string으로 하면 return /board/listAll
	@RequestMapping("/listAll")
	public void listAll(Model model) throws Exception{
		logger.info("listAll");
		List<BoardVO> list = service.listAll();
		model.addAttribute("list", list);
	}
	
	@RequestMapping("/listCri")
	public void listAll(Criteria cri,Model model) throws Exception{
		logger.info("listCri");
		List<BoardVO> list = service.listCriteria(cri);
		model.addAttribute("list", list);
	
	}
	
	@RequestMapping("/listPage")
	public void listPage(Criteria cri,Model model) throws Exception{
		logger.info("listPage");
		List<BoardVO> list = service.listCriteria(cri);
		PageMaker pageMaker = new  PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria());
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public void registerGET(){
		logger.info("register GET");
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerPost(BoardVO vo) throws Exception{
		logger.info("register POST");
		service.regist(vo);
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/read",method=RequestMethod.GET)
	public void read(int bno,Model model) throws Exception{
		logger.info("read");
		BoardVO vo = service.read(bno);
		model.addAttribute("boardVO", vo);
	}
	
	@RequestMapping(value="/readPage",method=RequestMethod.GET)
	public void readPage(@ModelAttribute("cri") Criteria cri,int bno,Model model,String cnt) throws Exception{
		logger.info("read");
		BoardVO vo = service.read(bno);
	
		if(cnt ==null){
			vo.setViewcnt(vo.getViewcnt()+1);
			service.modifyViewcnt(vo);
		}
		model.addAttribute("boardVO", vo);
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void getModify(int bno,Model model,Criteria cri) throws Exception{
		logger.info("modify Get");
		BoardVO vo = service.read(bno);
		model.addAttribute("boardVO", vo);
		model.addAttribute("cri", cri);
	
	}
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String postModify(BoardVO vo,RedirectAttributes rttr,Criteria cri)throws Exception{
		logger.info("modify Post");
		service.modify(vo,null);
		System.out.println(vo.getViewcnt());
		rttr.addAttribute("cnt", "1");
		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		return "redirect:/board/readPage";
	}
	
	@RequestMapping("/remove")
	public String remove(int bno,Criteria cri,RedirectAttributes rttr)throws Exception{
		service.remove(bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		return "redirect:/board/listPage";
	}

}
