package com.myjobappmgr.data;


import com.myjobappmgr.data.Job;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job,Integer>, JpaSpecificationExecutor<Job> {
	
}
