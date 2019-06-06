package com.contactmgr.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactmgr.data.Contact;
import com.contactmgr.data.ContactRepository;
import com.contactmgr.data.ContactUpdateDto;
import com.contactmgr.data.ContactViewDto;
import com.contactmgr.misc.ExcelExporter;
import com.contactmgr.misc.Util;


@Service
public class ContactManagerService {
	
	@Autowired
	ContactRepository contactRepository;
	
	public Integer saveContacts(List<Contact> contacts) throws Exception {
		return contactRepository.insertContacts(contacts);
	
	}
	
	public Integer deleteContact(String id) throws Exception {
		return contactRepository.deleteContact(id);
	}
	
	public Integer deleteAllContacts() throws Exception {
		int res = contactRepository.deleteAll();
		System.out.println("######DELETED####### " + res);
		return res;
	}
	
	public Integer updateContcat(ContactUpdateDto contactUpdateDto) throws Exception {
		return contactRepository.updateContact(contactUpdateDto);
	}

	
	public List<Contact> getAll() throws Exception {
		List<Contact> contacts = contactRepository.getAll();
		return contacts;
	}
	
	private Map<String,List<Contact>> getSortedAndGroupedContacts() throws Exception {
		Map<String,List<Contact>> groupedContacts = new HashMap<String,List<Contact>>();
		List<Contact> list = null;
		List<Contact> contacts = contactRepository.getAll();
		contacts.sort(Comparator.comparing(Contact::getEmail));
		
		for(Contact c : contacts) {
			String startsWith = String.valueOf(c.getEmail().charAt(0));
				
			if(c.getEmail().toLowerCase().startsWith(startsWith.toLowerCase())) {
				if(groupedContacts.containsKey(startsWith.toLowerCase())) {
					list = groupedContacts.get(startsWith.toLowerCase());
					list.add(c);
				} else {
					list = new ArrayList<>();
					list.add(c);
				}
			}
			
			groupedContacts.put(startsWith.toLowerCase(), list);
		}
		
		return groupedContacts;
	}
	
	public List<ContactViewDto> getAllSorted() throws Exception {
		Map<String,List<Contact>> groupedContacts = getSortedAndGroupedContacts();
		List<ContactViewDto> finalContacts = new ArrayList<>();
		
		for(String key :  groupedContacts.keySet()) {
			String group = key;
			List<Contact> contacts = groupedContacts.get(key);
			
			finalContacts.add(new ContactViewDto(group,contacts));
		}
		
		return finalContacts;
	}
	
	public Boolean exportContacts() throws Exception {
		List<Contact> contacts = contactRepository.getAll();
		return ExcelExporter.exportContactsToExcel(contacts);
	}
}
