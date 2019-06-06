package com.contactmgr;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.contactmgr.service.StorageService;


@SpringBootApplication
@EnableScheduling
public class ContactManager implements CommandLineRunner {
	
	@Resource
	StorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(ContactManager.class, args);
	}
	
	public void run(String...args) throws Exception {
	    storageService.deleteAll();
	    storageService.init(); 
	  }

}
