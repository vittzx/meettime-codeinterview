package com.meet.time.interview.application.port.service;

import com.meet.time.interview.application.port.input.CreateContactUseCase;
import com.meet.time.interview.domain.model.Contact;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactService implements CreateContactUseCase {


    @Override
    public List<Contact> createContact(List<Contact> contacts) {
        return List.of();
    }
}
