package com.myjobappmgr.data;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="jobs")
public class Job {

	private String id;
	
	private String title;
	
	private String company;
	
	private String board;
	
	private String refBoard;
	
	private String appliedDate;
	
	private String postUrl;
	
	private String comment;
	
	
	
	public Job() {
	}
	
	public Job(String title, String company, String board, String refBoard, String appliedDate, String postUrl,
			String comment) {
		super();
		this.title = title;
		this.company = company;
		this.board = board;
		this.refBoard = refBoard;
		this.appliedDate = appliedDate;
		this.postUrl = postUrl;
		this.comment = comment;
	}
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getRefBoard() {
		return refBoard;
	}
	public void setRefBoard(String refBoard) {
		this.refBoard = refBoard;
	}
	public String getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}
	public String getPostUrl() {
		return postUrl;
	}
	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setId(String id) {
		this.id = id;
	}
}
