//package com.myjobappmgr;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.myjobappmgr.data.Job;
//import com.myjobappmgr.data.JobRepository;
//import com.myjobappmgr.data.JobSearchCriteria;
//
//import junit.framework.TestCase;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class JobAppManagerTest extends TestCase {
//
//	@Autowired
//	JobRepository jobRepository;
//	
//	@Before
//	public void setup() {
//		Job job = new Job("java","sfsd","sad","lksla","SDAD","trt","tryr");
//        //save product, verify has ID value after save
//        assertNull(job.getId());
//        jobRepository.saveJobApp(job);
//        assertNotNull(job.getId());
//	}
//	
//	@Test
//	public void getData() {
//		JobSearchCriteria criteria = new JobSearchCriteria("java");
//		List<Job> jobs = jobRepository.searchForJobApp(criteria);
//		assertNotNull(jobs);
//		assertEquals(4,jobs.size());
//	}
//
//}
