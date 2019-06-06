package com.myjobappmgr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myjobappmgr.data.Job;
import com.myjobappmgr.data.JobRepository;
import com.myjobappmgr.data.JobSearchCriteria;

@Service
public class JobAppManagerService {
	
	@Autowired
	JobRepository jobRepository;
	
	public void saveJobApp(Job job) {
		jobRepository.saveJobApp(job);
	}
	
	public void deleteJobApp(Job job) {
		jobRepository.deleteJobApp(job);
	}


	public List<Job> searchForJobApp(JobSearchCriteria criteria) {
		return jobRepository.searchForJobApp(criteria);
	}
}
