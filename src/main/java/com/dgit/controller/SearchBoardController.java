package com.dgit.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.PageMaker;
import com.dgit.domain.SearchCriteria;
import com.dgit.service.BoardService;
import com.dgit.util.UploadFileUtils;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Resource(name="uploadPath")
	private String outUploadPath;
	
	@Autowired
	private BoardService service;

	@RequestMapping("/listPage")
	public void listPage(@ModelAttribute("cri")SearchCriteria cri,Model model) throws Exception{
		logger.info("listPage");
		List<BoardVO> list = service.listSearchCriteria(cri);
		PageMaker pageMaker = new  PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountSearchCriteria(cri));
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public void registerPage(){
		logger.info("register GET");
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerPost(BoardVO vo,List<MultipartFile> imageFile) throws Exception{
		logger.info("register POST");
		if(imageFile !=null && imageFile.get(0).getSize()>0){
			String[] files = new String[imageFile.size()];
			for(int i=0;i<imageFile.size();i++){
				String savedName =  UploadFileUtils.uploadFile(outUploadPath, imageFile.get(i).getOriginalFilename(), imageFile.get(i).getBytes());
				files[i]=savedName;
			}
			
			vo.setFiles(files);
		}

		service.regist(vo);
		return "redirect:/sboard/listPage";
	}
	
	@RequestMapping(value="/readPage",method=RequestMethod.GET)
	public void readPage(@ModelAttribute("cri") SearchCriteria cri,int bno,Model model,String cnt) throws Exception{
		logger.info("read");
		BoardVO vo = service.read(bno);
		if(cnt ==null){
			vo.setViewcnt(vo.getViewcnt()+1);
			service.modifyViewcnt(vo);
		}
		logger.info(vo.toString());
		model.addAttribute("boardVO", vo);
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void getModify(int bno,Model model,SearchCriteria cri) throws Exception{
		logger.info("modify Get");
		BoardVO vo = service.read(bno);
		model.addAttribute("boardVO", vo);
		model.addAttribute("cri", cri);
	
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String postModify(BoardVO vo,RedirectAttributes rttr,SearchCriteria cri,String[] delfiles,List<MultipartFile> imageFile)throws Exception{
		
		logger.info("modify Post");
		
		
		if(imageFile !=null && imageFile.get(0).getSize()>0){
			logger.info("들어왔냐");
			String[] files = new String[imageFile.size()];
			for(int i=0;i<imageFile.size();i++){
				String savedName =  UploadFileUtils.uploadFile(outUploadPath, imageFile.get(i).getOriginalFilename(), imageFile.get(i).getBytes());
				files[i]=savedName;
			}
			
			vo.setFiles(files);
		}
		
		if(delfiles !=null){
			for(int i=0;i<delfiles.length;i++){
				String filename = delfiles[i];
				
				String front = filename.substring(0,12);
				String end = filename.substring(14);
				
				String orignalFileName = front+end;
			
				
				File showfile = new File(outUploadPath+filename);
				showfile.delete();
				
				System.gc();
				
				File orignalFile = new File(outUploadPath+orignalFileName);
			
				orignalFile.delete();
			}
			
		}
		
		service.modify(vo,delfiles);
		
		rttr.addAttribute("cnt", "1");
		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/sboard/readPage";
	}
	
	@RequestMapping("/remove")
	public String remove(int bno,SearchCriteria cri,RedirectAttributes rttr)throws Exception{
		
		BoardVO vo = service.read(bno);
		
		String[] file = vo.getFiles();
		for(int i=0;i<file.length;i++){
			
			String filename = file[i];
			
			String front = filename.substring(0,12);
			String end = filename.substring(14);
			
			String orignalFileName = front+end;
		
			
			File showfile = new File(outUploadPath+filename);
			showfile.delete();
			
			System.gc();
			
			File orignalFile = new File(outUploadPath+orignalFileName);
		
			orignalFile.delete();
		}
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/sboard/listPage";
	}
}
