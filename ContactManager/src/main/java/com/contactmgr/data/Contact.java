package com.contactmgr.data;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="contacts")
public class Contact {

	private String id;
	
	private String email;

	public Contact() {
		
	}
	
	public Contact(String email) {
		super();
		this.email = email;
	}

	public Contact(String id, String email) {
		super();
		this.id = id;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
