package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dgit.domain.Criteria;
import com.dgit.domain.ReplyVO;
import com.dgit.persistence.BoardDAO;
import com.dgit.persistence.ReplyDAO;

@Repository 
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	ReplyDAO dao;
	
	@Autowired
	BoardDAO boardDao;
	
	@Override
	public List<ReplyVO> listReply(int bno) throws Exception {
		
		return dao.list(bno);
	}

	@Override
	@Transactional
	public void addReply(ReplyVO vo) throws Exception {
		dao.create(vo);
		boardDao.updateReplyCnt(vo.getBno(), 1);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	@Transactional
	public void removeReply(int rno) throws Exception {
		int bno = dao.getBno(rno);
		dao.delete(rno);
		boardDao.updateReplyCnt(bno, -1);
	}

	@Override
	public List<ReplyVO> listReplyPage(int bno, Criteria cri) throws Exception {
		return dao.listPage(bno, cri);
	}

	@Override
	public int count(int bno) throws Exception {
		return dao.count(bno);
	}

}
