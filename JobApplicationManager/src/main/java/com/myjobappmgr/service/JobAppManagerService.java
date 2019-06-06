package com.myjobappmgr.service;

import static com.myjobappmgr.service.JobSpecifications.anyFieldContainsIgnoreCase;
import static com.myjobappmgr.service.JobSpecifications.appliedDateBetween;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.myjobappmgr.data.Job;
import com.myjobappmgr.data.JobRepository;
import com.myjobappmgr.data.JobSearchCriteria;

@Service
public class JobAppManagerService {
	
	@Autowired
	JobRepository jobRepository;
	
	public void saveJobApp(Job job) {
		jobRepository.save(job);
	}
	
	public void deleteJobApp(Job job) {
		jobRepository.delete(job);
	}
		
	public Iterable<Job> findAllJobApps() {
		return jobRepository.findAll();
	}

	public List<Job> searchForJobApp(JobSearchCriteria criteria) {
		
		List<Job> result = new ArrayList<Job>();

		if (criteria.getSearchTerm() != null && !criteria.getSearchTerm().isEmpty()) {
			
			if(criteria.getIsSearchByDate() != null && criteria.getIsSearchByDate().equals("1")) {
				result = jobRepository.findAll(Specifications.where(anyFieldContainsIgnoreCase(criteria.getSearchTerm())).and(appliedDateBetween(criteria.getFromDate(), criteria.getToDate())));
			} else {
				result = jobRepository.findAll(anyFieldContainsIgnoreCase(criteria.getSearchTerm()));
			}
			
		} else {
			result = jobRepository.findAll(appliedDateBetween(criteria.getFromDate(), criteria.getToDate()));
		}
		
		return result;
		
	}
}
