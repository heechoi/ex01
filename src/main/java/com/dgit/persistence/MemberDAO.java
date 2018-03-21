package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.MemberVO;

public interface MemberDAO {
	public void insertMember(MemberVO vo)throws Exception;
	public List<MemberVO> listAll()throws Exception;
	public MemberVO readMember(String userid)throws Exception;
	public void update(MemberVO vo)throws Exception;
	public void delete(String userid)throws Exception;
	public MemberVO readWidthPw(String userid,String userpw) throws Exception;
}
