package com.contactmgr.data;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;


@Repository
public class ContactRepository {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Integer insertContacts(List<Contact> contacts) {
		try {
			mongoTemplate.insert(contacts, Contact.class);
			return new Integer(1);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new Integer(0);
		}
		
	}
	
	public Integer updateContact(ContactUpdateDto contactUpdateDto) {
		Query query = new Query();
		
		Criteria criteria = new Criteria().andOperator(where("id").is(contactUpdateDto.getId()),where("email").is(contactUpdateDto.getEmail()));
		
		query.addCriteria(criteria);
		Update update = new Update();
		update.set("email", contactUpdateDto.getNewVal());
		WriteResult result = mongoTemplate.upsert(query, update, Contact.class);
		System.out.println("ID " + result.getN());
		return  result.getN();
	}
	
	public Integer deleteContact(String id) {
        Query query = new Query();
		
		//Criteria criteria = new Criteria().andOperator(where("id").is(contact.getId()),where("email").is(contact.getEmail()));
		//query.addCriteria(criteria);
		query.addCriteria(Criteria.where("id").is(id));
		WriteResult result = mongoTemplate.remove(query, Contact.class);
		System.out.println("ID " + result.getN());
		return  result.getN();
	}
	
	public Integer deleteAll() {
		WriteResult result = mongoTemplate.remove(new Query(),Contact.class);
		int number = result.getN();
		
		return number;
	}
	
	public List<Contact> getAll() {
		return mongoTemplate.findAll(Contact.class);
	}
	
	
}
