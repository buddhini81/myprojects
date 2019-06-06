package com.contactmgr.scheduledtasks;

/**
 * This class is intended to be used in future for the purpose of scheduling the task of uploading contacts to the database
 * by picking up the contacts excel file from the upload directly at a scheduled interval/time
 */
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	
	 private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	    @Scheduled(fixedRate = 5000)
	    public void reportCurrentTime() {
	    	System.out.println("The time is now {} " + dateFormat.format(new Date()));
	    }
}
