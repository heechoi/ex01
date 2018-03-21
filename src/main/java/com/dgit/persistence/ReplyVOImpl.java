package com.dgit.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.Criteria;
import com.dgit.domain.ReplyVO;

@Repository
public class ReplyVOImpl implements ReplyDAO{
	
	
	private static final String namespace = "com.dgit.mapper.ReplyMapper";
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		return session.selectList(namespace+".list",bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert(namespace+".create",vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace+".update",vo);
	}

	@Override
	public void delete(int rno) throws Exception {
		session.delete(namespace+".delete",rno);
	}

	@Override
	public List<ReplyVO> listPage(int bno, Criteria cri) throws Exception {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("cri", cri);
		return session.selectList(namespace+".listPage",map);
	}

	@Override
	public int count(int bno) throws Exception {
		
		return session.selectOne(namespace+".count",bno);
	}

	@Override
	public int getBno(int rno) throws Exception {
		return session.selectOne(namespace+".getBno",rno);
	}

}
