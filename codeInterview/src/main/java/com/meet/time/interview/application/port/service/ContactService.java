package com.meet.time.interview.application.port.service;

import com.meet.time.interview.application.port.input.CreateContactUseCase;
import com.meet.time.interview.application.port.output.HubspotUseCase;
import com.meet.time.interview.domain.mapper.ContactRestMapper;
import com.meet.time.interview.domain.model.Contact;
import com.meet.time.interview.infra.adapters.input.data.response.contact.ContactCreatePropertiesRestResponse;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.request.ContactCreatePropertiesPostRequestToHubSpot;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.response.CreateContactHubspotResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactService implements CreateContactUseCase {

    private final HubspotUseCase hubspotUseCase;
    private final ContactRestMapper contactRestMapper;

    @Override
    public List<ContactCreatePropertiesRestResponse> createContact(List<Contact> contacts, String access_token) {
        log.debug("Started craetingContacts");

        List<ContactCreatePropertiesRestResponse> response = new ArrayList<>();

        for (Contact contact: contacts){
            log.debug("Contact fistName turn: {}", contact);
            ContactCreatePropertiesPostRequestToHubSpot request = contactRestMapper.toContactCreatePropertiesPostRequestToHubSpot(contact);
            CreateContactHubspotResponse createdContact = hubspotUseCase.createContact(request,access_token);
            contact = contactRestMapper.toContact(createdContact);
            response.add(contactRestMapper.toCreatedContactRestPropertiesResponse(contact));
            log.debug("Contact response firstName: {}", response);
        }
        return response;
    }

}
