package com.dgit.service;

import java.util.List;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.SearchCriteria;

public interface BoardService {
	public void regist(BoardVO board)throws Exception;
	public BoardVO read(int bno)throws Exception;
	public void modify(BoardVO board,String[] deletefiles)throws Exception;
	public void remove(int bno)throws Exception;
	public List<BoardVO> listAll()throws Exception;
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int listCountCriteria()throws Exception;
	public void modifyViewcnt(BoardVO vo)throws Exception;
	
	public List<BoardVO> listSearchCriteria(SearchCriteria cri)throws Exception;
	public int listCountSearchCriteria(SearchCriteria cri)throws Exception;
	
	

}
