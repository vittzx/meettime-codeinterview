package com.meet.time.interview.infra.adapters.input;

import com.meet.time.interview.infra.adapters.input.data.request.contact.CreateContactRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/contact")
public class ContactRestController {

    @PostMapping
    public ResponseEntity createContact(@RequestBody CreateContactRequestDTO request){
        log.debug("STARTED POST /v1/contact body request: {}", request);
        log.debug("FINISHED POST /v1/contact ");
        return ResponseEntity.ok(request.getContacts());
    }

}
