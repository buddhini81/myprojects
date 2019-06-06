package com.myjobappmgr.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myjobappmgr.data.Job;
import com.myjobappmgr.data.JobSearchCriteria;
import com.myjobappmgr.service.JobAppManagerService;



@RestController
@RequestMapping("/job")
public class JobAppManagerController {
	
	private final static Logger LOGGER = Logger.getLogger(JobAppManagerController.class.getName());

	@Autowired
	JobAppManagerService jobAppManagerService;


	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addNew(@RequestBody Job job) {
		jobAppManagerService.saveJobApp(job);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(@RequestBody Job job) {
		jobAppManagerService.saveJobApp(job);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void delete(@RequestBody Job job) {
		jobAppManagerService.deleteJobApp(job);
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public List<Job> findByCriteria(@RequestBody JobSearchCriteria criteria) {

		LOGGER.log(Level.INFO, "  " + criteria.getSearchTerm());
		
		return jobAppManagerService.searchForJobApp(criteria);
	}

}
