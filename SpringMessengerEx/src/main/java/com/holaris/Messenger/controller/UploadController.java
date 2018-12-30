package com.holaris.Messenger.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.holaris.Messenger.component.MimeMediaUtil;
import com.holaris.Messenger.component.UploadFileUtils;

@Controller
public class UploadController {

	private String uploadPath = "D:\\SpringMessengerExFile";
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		
	}
	
	@PostMapping("/uploadForm")
	public void uploadForm(MultipartFile file, Model model) throws Exception {
		/*System.out.println("originalName: " + file.getOriginalFilename());
		System.out.println("size: " + file.getSize());
		System.out.println("contentType: " + file.getContentType());*/
		
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
		
		model.addAttribute("savedName", savedName);
	}

	private String uploadFile(String originalFilename, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		
		String savedName = uid.toString() + "_" +originalFilename;
		
		File target = new File(savedName);
		
		FileCopyUtils.copy(fileData, target);
		
		return savedName;
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		
	}
	
	@ResponseBody
	@PostMapping(value="/uploadAjax", produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file, HttpServletRequest request) throws Exception{
		
		/*String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		String uploadPath = rootDirectory + "\\resources\\images\\" + file.getOriginalFilename();*/
		
		
		
		return new ResponseEntity<>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
	}
	
	@ResponseBody
	@GetMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
		InputStream in = null;
		ResponseEntity<byte []> entity = null;
		
		
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MimeMediaUtil.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath+fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			}else {
				fileName = fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers,HttpStatus.CREATED);
					
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
	}
	
	
	@ResponseBody
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		MediaType mType = MimeMediaUtil.getMediaType(formatName);

		if(mType != null) {
			String front = fileName.substring(0,12);
			String end = fileName.substring(14);
			new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
		}
		
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
}
