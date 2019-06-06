package com.contactmgr.data;

import java.util.List;

public class ContactViewDto {
	
	private String group;
	private List<Contact> contacts;
	
	public ContactViewDto(String group, List<Contact> contacts) {
		this.group = group;
		this.contacts = contacts;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
}
