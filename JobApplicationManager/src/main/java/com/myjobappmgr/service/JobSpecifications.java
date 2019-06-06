package com.myjobappmgr.service;

import org.springframework.data.jpa.domain.Specification;

import com.myjobappmgr.data.Job;

public final class JobSpecifications{
	
	private JobSpecifications() {}
	
	static Specification<Job> anyFieldContainsIgnoreCase(String searchTerm) {
        return (root, query, cb) -> {
            String containsLikePattern = getContainsLikePattern(searchTerm);
            return cb.or(
                    cb.like(cb.lower(root.<String>get("title")), containsLikePattern),
                    cb.like(cb.lower(root.<String>get("company")), containsLikePattern),
                    cb.like(cb.lower(root.<String>get("board")), containsLikePattern),
                    cb.like(cb.lower(root.<String>get("refBoard")), containsLikePattern),
                    cb.like(cb.lower(root.<String>get("comment")), containsLikePattern)
            );
        };
    }
	
	
	static Specification<Job> appliedDateBetween(String fromDate, String toDate) {
        return (root, query, cb) -> {
            return cb.between(root.<String>get("appliedDate"), fromDate, toDate);
        };
    }
	
	private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        }
        else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }

}
