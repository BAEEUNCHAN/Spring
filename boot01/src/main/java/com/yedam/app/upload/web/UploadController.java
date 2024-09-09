package com.yedam.app.upload.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	@GetMapping("formUpload")
	public void formUploadPage() {}
	
	@PostMapping("uploadForm")
	public String formUploadFile
			(@RequestPart MultipartFile file) {
		System.out.println(file.getOriginalFilename());
		return "formUpload";
	}
}
