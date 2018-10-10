package com.jlcindia.spring.service;

import java.util.List;

import com.jlcindia.spring.to.ContactTO;

public interface ContactService {

	public boolean addContact(ContactTO cto);

	public List<ContactTO> getAllContacts();
}
