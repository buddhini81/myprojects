package com.myjobappmgr.data;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class JobRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public void saveJobApp(Job job) {
		mongoTemplate.save(job);
	}
	
	public void deleteJobApp(Job job) {
		mongoTemplate.remove(query(where("id").is(job.getId())), Job.class);
	}

	public List<Job> searchForJobApp(JobSearchCriteria criteria) {

		List<Job> result = new ArrayList<Job>();

		if (criteria.getSearchTerm() != null && !criteria.getSearchTerm().isEmpty()) {
			
			
			Criteria searchTermsOr = getCriteriaSearchTermsIs(criteria.getSearchTerm());
			
			Criteria dateBetween = getCriteriaAppliedDateBetween(criteria.getFromDate(),criteria.getToDate());

			if (criteria.getIsSearchByDate() != null && criteria.getIsSearchByDate().equals("1")) {
				
				result = mongoTemplate.find(query(searchTermsOr.andOperator(dateBetween)), Job.class);

			} else {
				result = mongoTemplate.find(query(searchTermsOr), Job.class);
			}

		} else {
			result = mongoTemplate.find(query(getCriteriaAppliedDateBetween(criteria.getFromDate(),criteria.getToDate())), Job.class);
		}

		

		return result;
	}
	
	private static Criteria getCriteriaSearchTermsIs(String searchTerm) {
		Criteria searchTermsIs = new Criteria().orOperator(where("title").regex(searchTerm, "i"),
				where("company").regex(searchTerm, "i"), where("board").regex(searchTerm, "i"),
				where("refBoard").regex(searchTerm, "i"), where("comment").regex(searchTerm, "i"));
		
		return searchTermsIs;
    }

	private static Criteria getCriteriaAppliedDateBetween(String fromDate, String toDate) {
		return where("appliedDate").gte(fromDate).lte(toDate);
    }
}
