package com.meet.time.interview.application.port.service;

import com.meet.time.interview.application.configuration.HubspotProperties;
import com.meet.time.interview.application.port.input.CreateContactUseCase;
import com.meet.time.interview.application.port.output.HubspotUseCase;
import com.meet.time.interview.domain.mapper.ContactRestMapper;
import com.meet.time.interview.domain.model.Contact;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.request.ContactCreatePropertiesPostRequestToHubSpot;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.response.CreateContactHubspotResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactService implements CreateContactUseCase {

    private final HubspotUseCase hubspotUseCase;
    private final ContactRestMapper contactRestMapper;

    @Override
    public List<Contact> createContact(List<Contact> contacts, String access_token) {
        log.debug("Started craetingContacts");
        for (Contact contact: contacts){
            log.debug("Contact fistName turn: {}", contact.getFirstName());
            ContactCreatePropertiesPostRequestToHubSpot request = contactRestMapper.toContactCreatePropertiesPostRequestToHubSpot(contact);
            CreateContactHubspotResponse response = hubspotUseCase.createContact(request,access_token);
            log.debug("Contact response firstName: {}", response.getProperties());
        }
        return List.of();
    }

    private String createUrltoCreateContact(){
        return "";
    }
}
