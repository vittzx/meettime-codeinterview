package com.meet.time.interview.services;

import com.meet.time.interview.application.port.output.HubspotUseCase;
import com.meet.time.interview.application.port.service.ContactService;
import com.meet.time.interview.domain.mapper.ContactRestMapper;
import com.meet.time.interview.domain.model.Contact;
import com.meet.time.interview.infra.adapters.input.data.response.contact.ContactCreatePropertiesRestResponse;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.request.ContactCreatePropertiesPostRequestToHubSpot;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.response.CreateContactHubspotResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private HubspotUseCase hubspotUseCase;

    @Mock
    private ContactRestMapper contactRestMapper;

    @InjectMocks
    private ContactService service;

    private String accessToken;
    private List<Contact> contacts;



    @BeforeEach
    void setUp(){
        accessToken = "accessToken";
        contacts = List.of(
                Contact
                        .builder()
                        .website("website")
                        .lifeCycleStage("lifecyclestage")
                        .email("email")
                        .company("company")
                        .phone("phone")
                        .lastName("lastname")
                        .firstName("firstname")
                        .id("1")
                        .build()
        );
    }

    @Test
    void shouldReturnCreateContactByListSuccessfully(){
        for(Contact contact: contacts){
            ContactCreatePropertiesPostRequestToHubSpot request = ContactCreatePropertiesPostRequestToHubSpot
                    .builder()
                    .lifecyclestage(contact.getLifeCycleStage())
                    .lastname(contact.getLastName())
                    .firstname(contact.getFirstName())
                    .company(contact.getCompany())
                    .website(contact.getWebsite())
                    .email(contact.getEmail())
                    .phone(contact.getPhone())
                    .build();

            CreateContactHubspotResponse hubspotResponse = CreateContactHubspotResponse
                    .builder()
                    .id("1")
                    .createdAt("2025-01-01:10-10-10")
                    .updatedAt("2025-01-01:10-10-10")
                    .archived(false)
                    .properties(CreateContactHubspotResponse.CreateContactHubspotResponseProperties.builder()
                            .company(request.getCompany())
                            .email(request.getEmail())
                            .firstname(request.getFirstname())
                            .lastname(request.getLastname())
                            .lifecyclestage(request.getLifecyclestage())
                            .phone(request.getPhone())
                            .website(contact.getWebsite())
                            .build())
                    .build();

            ContactCreatePropertiesRestResponse response = ContactCreatePropertiesRestResponse
                    .builder()
                    .id("1")
                    .company(request.getCompany())
                    .email(request.getEmail())
                    .fullName(request.getFirstname() + " " + request.getLastname())
                    .build();

            when(contactRestMapper.toContactCreatePropertiesPostRequestToHubSpot(contact)).thenReturn(
                    request
            );

            when(contactRestMapper.toContact(hubspotResponse)).thenReturn(
                    contact
            );

            when(contactRestMapper.toCreatedContactRestPropertiesResponse(contact)).thenReturn(
                    response
            );

            when(hubspotUseCase.createContact(request, accessToken)).thenReturn(
                    hubspotResponse
            );

            List<ContactCreatePropertiesRestResponse> result = service.createContact(contacts, accessToken);

            assertNotNull(result);
            assertEquals(contacts.get(0).getEmail(), result.get(0).getEmail());
            assertEquals(contacts.get(0).getId(), result.get(0).getId());
            assertEquals(contacts.get(0).getFirstName() + " " + contacts.get(0).getLastName(), result.get(0).getFullName());
            assertEquals(contacts.get(0).getCompany(), result.get(0).getCompany());

        }
    }


}
