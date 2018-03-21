package com.dgit.ex01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgit.domain.ReplyVO;
import com.dgit.persistence.ReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ReplyDAOTest {
	
	@Autowired
	ReplyDAO dao;
	
	//@Test
	public void create()throws Exception{
		ReplyVO vo = new ReplyVO();
		vo.setReplytext("내용3");
		vo.setBno(2359);
		vo.setReplyer("작성자3");
		
		dao.create(vo);	
	}
	
	//@Test
	public void listTest()throws Exception{
		dao.list(2359);
	}
	
	//@Test
	public void updateTest()throws Exception{
		ReplyVO vo = new ReplyVO();
		vo.setRno(1);
		vo.setReplytext("수정된 내용입니다");
		dao.update(vo);
	}
	
	//@Test
	public void deleteTest()throws Exception{
		dao.delete(1);
	}
}
