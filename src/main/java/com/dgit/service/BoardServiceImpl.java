package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.SearchCriteria;
import com.dgit.persistence.BoardDAO;

@Repository
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	@Override
	@Transactional
	public void regist(BoardVO board) throws Exception {
		dao.create(board);
		String[] files = board.getFiles();
		
		if(files==null){
			return;
		}
		for(String fileName:files){
			dao.addAttach(fileName, board.getBno());
		}
	
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		
		BoardVO vo = dao.read(bno);
		List<String> list = dao.getAttach(bno);
		
		vo.setFiles(list.toArray(new String[list.size()]));//string 배열로 반환
		
		return vo;
	}

	@Override
	@Transactional
	public void modify(BoardVO board,String[] deletefiles) throws Exception {
		
		if(deletefiles !=null){
			for(int i=0;i<deletefiles.length;i++){
				dao.deleteFileName(deletefiles[i]);
			}
		}
		
		dao.update(board);

		String[] files = board.getFiles();
	
		if(files !=null){
			for(String fileName:files){
				dao.addAttach(fileName, board.getBno());
			}
		}
	
	
	}

	@Override
	@Transactional
	public void remove(int bno) throws Exception {
		dao.deleteAttach(bno);
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria() throws Exception {
		return dao.countPagin();
	}

	@Override
	public void modifyViewcnt(BoardVO vo) throws Exception {
		dao.updateViewcnt(vo);
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public int listCountSearchCriteria(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchCount(cri);
	}



}
