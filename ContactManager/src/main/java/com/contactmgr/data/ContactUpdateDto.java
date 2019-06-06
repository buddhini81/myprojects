package com.contactmgr.data;

public class ContactUpdateDto {
	
	private String id;
	
	private String email;
	
	private String newVal;
	
	public ContactUpdateDto() {
	}

	public ContactUpdateDto(String id, String email, String newVal) {
		this.id = id;
		this.email = email;
		this.newVal = newVal;
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

	public String getNewVal() {
		return newVal;
	}

	public void setNewVal(String newVal) {
		this.newVal = newVal;
	}

}
