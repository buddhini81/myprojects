package com.contactmgr.controller;

import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactmgr.service.StorageService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/contact")
public class DownloadController {
	
	@Autowired
	StorageService storageService;
	
	private static final String FILE_NAME = "exported-contacts.xlsx";

	@GetMapping("/download")
	public ResponseEntity<byte[]> downloadFile() throws Exception {
        // Load file as Resource
        Resource resource = storageService.loadFile(FILE_NAME);
        String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(Files.readAllBytes(resource.getFile().toPath()));
    }

}
