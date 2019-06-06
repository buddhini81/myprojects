package com.contactmgr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.contactmgr.service.StorageService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/contact")
public class UploadController {
	@Autowired
	  StorageService storageService;
	 
	  List<String> files = new ArrayList<String>();
	 
	  @PostMapping("/upload")
	  public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
	      System.out.println("Before Calling store");
	      System.out.println(file);
	      storageService.store(file);
	      files.add(file.getOriginalFilename());
	 
	      message = "You successfully uploaded " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.OK).body(message);
	    } catch (Exception e) {
	      message = "FAIL to upload " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	    }
	  }

	 
	  @GetMapping("/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = storageService.loadFile(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
	        .body(file);
	  }
}
