package com.meet.time.interview.application.port.input;

import com.meet.time.interview.domain.model.Contact;
import com.meet.time.interview.infra.adapters.input.data.response.contact.ContactCreatePropertiesRestResponse;

import java.util.List;

public interface CreateContactUseCase {

    List<ContactCreatePropertiesRestResponse> createContact(List<Contact> contacts, String access_token);
}
