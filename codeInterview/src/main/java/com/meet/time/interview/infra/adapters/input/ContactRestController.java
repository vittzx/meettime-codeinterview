package com.meet.time.interview.infra.adapters.input;

import com.meet.time.interview.application.port.input.CreateContactUseCase;
import com.meet.time.interview.domain.mapper.ContactRestMapper;
import com.meet.time.interview.domain.model.Contact;
import com.meet.time.interview.infra.adapters.input.data.request.contact.CreateContactRequestDTO;
import com.meet.time.interview.infra.adapters.input.data.response.contact.ContactCreateRestResponse;
import com.meet.time.interview.infra.adapters.input.data.response.global.DefaultRestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/contact")
public class ContactRestController {

    private final CreateContactUseCase createContactUseCase;
    private final ContactRestMapper contactRestMapper;

    @PostMapping
    public ResponseEntity<DefaultRestResponse<List<ContactCreateRestResponse>>> createContact(@RequestBody CreateContactRequestDTO request, @RequestHeader(name = "authorization") String accessToken){
        log.debug("STARTED POST /v1/contact body request: {}", request);
        List<Contact> contacts = contactRestMapper.toListContact(request.getContacts());
        contacts = createContactUseCase.createContact(contacts, accessToken);
        log.debug("FINISHED POST /v1/contact ");
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new DefaultRestResponse<>()
        );
    }

    @PostMapping("/webhook/created")
    public ResponseEntity webhookContactCreatedEventListener(){
        return null;
    }

}
