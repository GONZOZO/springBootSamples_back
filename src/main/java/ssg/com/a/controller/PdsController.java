package ssg.com.a.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import ssg.com.a.dto.PdsDto;
import ssg.com.a.dto.PdsParam;
import ssg.com.a.service.PdsService;

@RestController
public class PdsController {

	@Autowired
	PdsService service;
	
	@GetMapping("pdslist")
	public Map<String, Object> pdslist(PdsParam param) {
		System.out.println("PdsController pdslist" + new Date());		
		
		// 글목록
		List<PdsDto> list = service.pdslist(param);
		
		// 글의 총갯수
		int count = service.getallpds(param);
		int pagePds = count / 10;
		if((count % 10) > 0) {
			pagePds = pagePds + 1;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pdslist", list);
		map.put("pagePds", pagePds);
		
		return map;
	}
	
	@GetMapping("pdsdetail")
	public PdsDto pdsdetail(int seq) {
		System.out.println("PdsController pdsdetail " + new Date());
		
		service.readcount(seq);
		
		return service.pdsdetail(seq);
	}
	

	@Autowired
	ServletContext sevletContext;
	@GetMapping("filedownload")
	public ResponseEntity<InputStreamResource> filedownload(String filename,
												jakarta.servlet.http.HttpServletRequest request, int seq)throws FileNotFoundException{
		System.out.println("PdsController filedownload " + new Date());
		
		// 파일업로드 경로
		String path = request.getServletContext().getRealPath("/upload");				
		//	String path = "d:\tmp";
		
		// 파일 타입을 조사
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(sevletContext, filename);
		
		File file = new File(path + "/" + filename);
				
		InputStreamResource is = new InputStreamResource(new FileInputStream(file));
		
		// download counter 증가
		service.downcount(seq);
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())	// 원본파일명
				.contentType(mediaType)
				.contentLength(file.length())
				.body(is);
	}	
	
	public class MediaTypeUtils {
		public static MediaType getMediaTypeForFileName(ServletContext sc, String filename) {
			String mimType = sc.getMimeType(filename);
			
			try {
				MediaType mediaType = MediaType.parseMediaType(mimType);
				return mediaType;
			}catch (Exception e) {
				return MediaType.APPLICATION_OCTET_STREAM;
			}			
		}
	}
	
	
	@PostMapping("fileupload")
	public String fileupload(PdsDto pds, 	// form field
							 @RequestParam("uploadfile")MultipartFile uploadfile,
							 HttpServletRequest request) {	// 파일업로드 경로
		System.out.println("PdsController fileupload " + new Date());
		System.out.println(pds.toString());
		
		// 파일업로드 경로
		String path = request.getServletContext().getRealPath("/upload");
		
	//	String path = "d:\tmp";
		
		String filename = uploadfile.getOriginalFilename();
		
		// 파일명을 변경!
		String newfilename = getNewFileName(filename);
		
		String filepath = path + "/" + filename;
		System.out.println(filepath);
		
		File file = new File(filepath);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
						
			os.write(uploadfile.getBytes());	// 실제 업로드 되는 부분
			os.close();
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}	
		
		// db에 접속하기 전 filename, newfilename 저장
		pds.setFilename(filename);
		pds.setNewfilename(newfilename);
		System.out.println(pds.toString());
		
		boolean b = service.pdsupload(pds);
		if(!b) {
			return "NO";
		}
		
		return "YES";
	}
	
	// 파일명 변환 함수 ( abc.txt -> 43534534.txt )
	public static String getNewFileName(String filename) {
		String newfilename = "";
		String fpost = "";	// .jpg .txt
		
		if(filename.indexOf('.') >= 0) {	// 확장명이 있음
			fpost = filename.substring(filename.indexOf('.'));	// .txt
			newfilename = new Date().getTime() + fpost;			// 43534534.txt			
		}else {								// -1 확장명이 없음
			newfilename = new Date().getTime() + ".back";
		}
		
		return newfilename;
	}
	
	public static MediaType getMediaTypeForFileName(ServletContext sc, String filename) {
		
		String mimType = sc.getMimeType(filename);
		
		try {
			MediaType mediaType = MediaType.parseMediaType(mimType);
			return mediaType;
		}catch (Exception e) {
			return MediaType.APPLICATION_OCTET_STREAM;
		}			
	}
	
}
