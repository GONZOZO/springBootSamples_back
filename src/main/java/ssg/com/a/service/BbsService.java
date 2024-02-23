package ssg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ssg.com.a.dao.BbsDao;
import ssg.com.a.dto.BbsComment;
import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;

@Service
@Transactional
public class BbsService {
	
	@Autowired
	BbsDao dao;
	
	public List<BbsDto> bbslist(BbsParam param) {
		return dao.bbslist(param);
	}
	
	public int getallbbs(BbsParam param) {
		return dao.getallbbs(param);
	}
	
	public boolean bbswrite(BbsDto bbs) {
		return dao.bbswrite(bbs)>0?true:false;
	}
	
	public BbsDto bbsdetail(int seq) {
		return dao.bbsdetail(seq);
	}
	
	public void readcount(int seq) {
		dao.readcount(seq);
	}
	
	public boolean bbsupdate(BbsDto dto) {
		int count = dao.bbsupdate(dto);
		return count>0?true:false;
	}
	
	public boolean bbsdelete(int seq) {	
		int count = dao.bbsdelete(seq);
		return count>0?true:false;
	}
	
	public boolean bbsAnswerInsert(BbsDto bbs) {
		dao.bbsAnswerUpdate(bbs);
		return dao.bbsAnswerInsert(bbs)>0?true:false;
	}
	
	public List<BbsComment> bbsCommentList(int seq) {		
		return dao.bbsCommentList(seq);
	}
	
	public boolean bbsCommentWrite(BbsComment com) {
		int count = dao.bbsCommentWrite(com);
		return count>0?true:false;
	};
}









