package com.contactmgr.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.contactmgr.data.ApiResponse;
import com.contactmgr.data.Contact;
import com.contactmgr.data.ContactUpdateDto;
import com.contactmgr.data.ContactViewDto;
import com.contactmgr.misc.ExcelExporter;
import com.contactmgr.misc.ExcelReader;
import com.contactmgr.service.ContactManagerService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/contact")
public class ContactManagerController {
	
	private final static Logger LOGGER = Logger.getLogger(ContactManagerController.class.getName());

	@Autowired
	ContactManagerService contactManagerService;
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ApiResponse<Object> save() {
		List<Contact> contacts = null;

		try {
			List<String> emails = ExcelReader.readExcelContacts();

			if (emails != null) {
				contacts = new ArrayList<>();
				System.out.print("####Emails### " + emails.size());
				for (String contact : emails) {
					contacts.add(new Contact(contact));
				}

				System.out.print("####Size### " + contacts.size());

				Integer result = contactManagerService.saveContacts(contacts);
				if (result == 1) {
					System.out.print("####result### " + result);
					return new ApiResponse<Object>(HttpStatus.OK.value(), "Contacts saved successfully.", result);
				} else {
					System.out.print("####result### " + result);
					return new ApiResponse<Object>(HttpStatus.NOT_FOUND.value(), "Contacts could not be saved.", result);
				}
			} else {
				return new ApiResponse<Object>(HttpStatus.NOT_FOUND.value(), "Contacts could not be obtained for saving.", new Integer(0));
			}
		} catch (Exception e) {
			return new ApiResponse<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "System Error. Please contact the Administrator", new Integer(0));
		}
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ApiResponse<List<Contact>> getAll() {
		List<Contact> result = null;
		try {
			result = contactManagerService.getAll();
			return new ApiResponse<List<Contact>>(HttpStatus.OK.value(), "Contacts fetched successfully.",result);
		} catch (Exception e) {
			return new ApiResponse<List<Contact>>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"System Error. Please contact the Administrator", new Integer(0));
		}
	}
	
	@RequestMapping(value = "/getAllSorted", method = RequestMethod.GET)
	public ApiResponse<List<ContactViewDto>> getAllSorted() {
		List<ContactViewDto> result = null;
		try {
			result = contactManagerService.getAllSorted();
		return new ApiResponse<List<ContactViewDto>>(HttpStatus.OK.value(), "Contacts fetched successfully.",contactManagerService.getAllSorted());
		} catch (Exception e) {
			return new ApiResponse<List<ContactViewDto>>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
					"System Error. Please contact the Administrator", result);
		}
	}
	
	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	public ApiResponse<Object> deleteAll() {
		try {
			return new ApiResponse<Object>(HttpStatus.OK.value(), "Contacts deleted successfully.",
					contactManagerService.deleteAllContacts());
		} catch (Exception e) {
			return new ApiResponse<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "System Error. Please contact the Administrator", new Integer(0));
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ApiResponse<Object> delete(@PathVariable String id) {
		try {
		return new ApiResponse<Object>(HttpStatus.OK.value(), "Contact deleted successfully.",contactManagerService.deleteContact(id));
		} catch (Exception e) {
			return new ApiResponse<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "System Error. Please contact the Administrator", new Integer(0));
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ApiResponse<Object> update(@RequestBody ContactUpdateDto updateDto) {
		try {
			System.out.println(updateDto.getEmail());
			System.out.println(updateDto.getNewVal());
			return new ApiResponse<Object>(HttpStatus.OK.value(), "Contact updated successfully.",
					contactManagerService.updateContcat(updateDto));
		} catch (Exception e) {
			return new ApiResponse<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"System Error. Please contact the Administrator", new Integer(0));
		}
	}
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public ApiResponse<Object> export() {
		try {
			return new ApiResponse<Object>(HttpStatus.OK.value(), "Contacts exported successfully.",
					contactManagerService.exportContacts());
		} catch (Exception e) {
			return new ApiResponse<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"System Error. Please contact the Administrator", new Integer(0));
		}
	}

}
