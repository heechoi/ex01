package com.dgit.service;

import java.util.List;

import com.dgit.domain.MemberVO;

public interface MemberService {
	public List<MemberVO> listMember()throws Exception;
	public void addMember(MemberVO vo)throws Exception;
	public void modifyMember(MemberVO vo)throws Exception;
	public void removeMember(String userid)throws Exception;
	public MemberVO readMember(String userid)throws Exception;
	public MemberVO readWidthPw(String userid,String userpw)throws Exception;
}
