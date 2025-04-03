package com.meet.time.interview.domain.mapper;

import com.meet.time.interview.domain.model.Contact;
import com.meet.time.interview.infra.adapters.input.data.request.contact.ContactPropertiesRequestDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ContactRestMapper {

    List<Contact> toListContact(List<ContactPropertiesRequestDTO> contacts);
}
