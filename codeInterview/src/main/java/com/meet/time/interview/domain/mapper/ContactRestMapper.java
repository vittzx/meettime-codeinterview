package com.meet.time.interview.domain.mapper;

import com.meet.time.interview.domain.model.Contact;
import com.meet.time.interview.infra.adapters.input.data.request.contact.ContactPropertiesRequestDTO;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.request.ContactCreatePropertiesPostRequestToHubSpot;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.response.CreateContactHubspotResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ContactRestMapper {

    List<Contact> toListContact(List<ContactPropertiesRequestDTO> contacts);

    @Mapping(target = "firstname", source = "firstName")
    @Mapping(target = "lastname", source = "lastName")
    ContactCreatePropertiesPostRequestToHubSpot toContactCreatePropertiesPostRequestToHubSpot(Contact response);


    @Mapping(target = "firstName", source = "properties.firstname")
    @Mapping(target = "lastName", source = "properties.lastname")
    @Mapping(target = "c")
    Contact toContact(CreateContactHubspotResponse response);

}
