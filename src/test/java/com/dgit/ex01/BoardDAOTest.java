package com.dgit.ex01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	
	@Autowired
	private BoardDAO dao;
	
	//@Test
	public void createTest() throws Exception{
		BoardVO vo = new BoardVO();
		vo.setContent("내용");
		vo.setTitle("제목");
		vo.setWriter("작성자");
		dao.create(vo);
		dao.addAttach("fullName", vo.getBno());
	}
	
	//@Test
	public void readTest() throws Exception{
		dao.read(1);
	}
	
	//@Test
	public void updateTest()throws Exception{
		BoardVO vo = new BoardVO();
		vo.setBno(1);
		vo.setContent("수정된 내용");
		vo.setTitle("수정된 제목");
		dao.update(vo);
	}
	
	//@Test
	public void listAllTest()throws Exception{
		dao.listAll();
	}
	//@Test
	public void deleteTest()throws Exception{
		dao.delete(1);
	}
	
	//@Test
	public void testListPage()throws Exception{
		dao.listPage(2);
	}
	
	//@Test
	public void testListCriteria()throws Exception{
		Criteria cri = new Criteria();
		cri.setPage(1);
		cri.setPerPageNum(20);
		dao.listCriteria(cri);
	}
	
}
