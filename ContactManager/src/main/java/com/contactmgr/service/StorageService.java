package com.contactmgr.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
	
	  private final Path rootLocationUpload = Paths.get("upload-dir");
	  private final Path rootLocationExport = Paths.get("export-dir");
	 
	  public void store(MultipartFile file) {
	    try {
	    	System.out.println("In StorageService " + file);
	    	System.out.println(file.getOriginalFilename());
	      Files.copy(file.getInputStream(), this.rootLocationUpload.resolve(file.getOriginalFilename()));
	      System.out.println("After copy");
	    } catch (Exception e) {
	    	System.out.println(e);
	      throw new RuntimeException("FAIL!");
	    }
	  }
	 
	  public Resource loadFile(String filename) {
	    try {
	      Path file = rootLocationExport.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());
	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("FAIL!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("FAIL!");
	    }
	  }
	 
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(rootLocationUpload.toFile());
	    FileSystemUtils.deleteRecursively(rootLocationExport.toFile());
	  }
	 
	  public void init() {
	    try {
	      Files.createDirectory(rootLocationUpload);
	      Files.createDirectory(rootLocationExport);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize storage!");
	    }
	  }

}
