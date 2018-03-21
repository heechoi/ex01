package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.MemberVO;
import com.dgit.persistence.MemberDAO;
@Repository
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberDAO dao;
	
	@Override
	public List<MemberVO> listMember() throws Exception {
		return dao.listAll();
	}

	@Override
	public void addMember(MemberVO vo) throws Exception {
		dao.insertMember(vo);
		
	}

	@Override
	public void modifyMember(MemberVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void removeMember(String userid) throws Exception {
		dao.delete(userid);
	}

	@Override
	public MemberVO readMember(String userid) throws Exception {
		return dao.readMember(userid);
	}

	@Override
	public MemberVO readWidthPw(String userid, String userpw) throws Exception {
	
		return dao.readWidthPw(userid, userpw);
	}

}
