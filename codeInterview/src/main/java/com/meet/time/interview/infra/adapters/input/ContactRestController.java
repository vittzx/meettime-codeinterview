package com.meet.time.interview.infra.adapters.input;

import com.meet.time.interview.application.port.input.CreateContactUseCase;
import com.meet.time.interview.domain.mapper.ContactRestMapper;
import com.meet.time.interview.domain.model.Contact;
import com.meet.time.interview.infra.adapters.input.data.request.contact.CreateContactRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/contact")
public class ContactRestController {

    private final CreateContactUseCase createContactUseCase;
    private final ContactRestMapper contactRestMapper;

    @PostMapping
    public ResponseEntity createContact(@RequestBody CreateContactRequestDTO request){
        log.debug("STARTED POST /v1/contact body request: {}", request);
        List<Contact> contacts = contactRestMapper.toListContact(request.getContacts());
        log.debug("Contacts: {}",contacts);
        contacts = createContactUseCase.createContact(contacts);
        log.debug("FINISHED POST /v1/contact ");
        return ResponseEntity.ok(request.getContacts());
    }

}
