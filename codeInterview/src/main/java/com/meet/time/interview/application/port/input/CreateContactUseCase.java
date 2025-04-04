package com.meet.time.interview.application.port.input;

import com.meet.time.interview.domain.model.Contact;

import java.util.List;

public interface CreateContactUseCase {

    List<Contact> createContact(List<Contact> contacts, String access_token);
}
