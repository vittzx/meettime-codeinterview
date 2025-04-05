package com.meet.time.interview.domain.mapper;

import com.meet.time.interview.domain.model.Contact;
import com.meet.time.interview.infra.adapters.input.data.request.contact.ContactPropertiesRequestDTO;
import com.meet.time.interview.infra.adapters.input.data.response.contact.ContactCreatePropertiesRestResponse;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.request.ContactCreatePropertiesPostRequestToHubSpot;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.response.CreateContactHubspotResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
@NoArgsConstructor
public class ContactRestMapper {

    public List<Contact> toListContact(List<ContactPropertiesRequestDTO> contacts){
        if(ObjectUtils.isEmpty(contacts)) return null;

        return contacts.stream().map((contactRequest) -> {
            return Contact
                    .builder()
                    .firstName(contactRequest.getFirstName())
                    .lastName(contactRequest.getLastName())
                    .phone(contactRequest.getPhone())
                    .company(contactRequest.getCompany())
                    .website(contactRequest.getWebsite())
                    .email(contactRequest.getEmail())
                    .lifeCycleStage(contactRequest.getLifeCycleStage())
                    .build();
        }).toList();
    }

    public ContactCreatePropertiesPostRequestToHubSpot toContactCreatePropertiesPostRequestToHubSpot(Contact contact){
        return ObjectUtils.isEmpty(contact) ? null : ContactCreatePropertiesPostRequestToHubSpot
                .builder()
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .lifecyclestage(contact.getLifeCycleStage())
                .company(contact.getCompany())
                .website(contact.getWebsite())
                .firstname(contact.getFirstName())
                .lastname(contact.getLastName())
                .build();
    }

    public Contact toContact(CreateContactHubspotResponse response){
        return ObjectUtils.isEmpty(response) ? null : Contact.
                builder()
                .phone(response.getProperties().getPhone())
                .id(response.getId())
                .company(response.getProperties().getCompany())
                .email(response.getProperties().getEmail())
                .firstName(response.getProperties().getFirstname())
                .lastName(response.getProperties().getLastname())
                .website(response.getProperties().getWebsite())
                .lifeCycleStage(response.getProperties().getWebsite())
                .build();
    }

    public ContactCreatePropertiesRestResponse toCreatedContactRestPropertiesResponse(Contact response){
        return ObjectUtils.isEmpty(response) ? null : ContactCreatePropertiesRestResponse
                .builder()
                .fullName(response.getFirstName() + " " + response.getLastName())
                .id(response.getId())
                .email(response.getEmail())
                .company(response.getCompany())
                .build();
    }

}
