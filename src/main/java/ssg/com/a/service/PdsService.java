package ssg.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.com.a.dao.PdsDao;
import ssg.com.a.dto.PdsDto;
import ssg.com.a.dto.PdsParam;

@Service
@Transactional
public class PdsService {

	@Autowired
	PdsDao dao;
	
	public List<PdsDto> pdslist(PdsParam param) {		
		return dao.pdslist(param);
	}
	
	public int getallpds(PdsParam param) {
		return dao.getallpds(param);
	}
	
	public PdsDto pdsdetail(int seq) {
		return dao.pdsdetail(seq);
	}
	
	public void readcount(int seq) {
		dao.readcount(seq);
	}
	
	public void downcount(int seq) {
		dao.downcount(seq);
	}
	
	public boolean pdsupload(PdsDto pds) {
		return dao.pdsupload(pds)>0?true:false;
	}
}
