package ssg.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ssg.com.a.dto.PdsDto;
import ssg.com.a.dto.PdsParam;

@Mapper
@Repository
public interface PdsDao {

	List<PdsDto> pdslist(PdsParam param);
	int getallpds(PdsParam param);

	PdsDto pdsdetail(int seq);
	int readcount(int seq);

	int downcount(int seq);
	
	int pdsupload(PdsDto pds);
}
