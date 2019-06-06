package com.myjobappmgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobAppManagerApplicationV2 {
	
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SimpleToDoApplication.class);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(JobAppManagerApplicationV2.class, args);
	}
}
