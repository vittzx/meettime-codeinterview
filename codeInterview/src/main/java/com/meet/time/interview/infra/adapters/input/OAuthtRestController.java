package com.meet.time.interview.infra.adapters.input;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/contact")
@RequiredArgsConstructor
public class ContactRestController {

    @PostMapping
    public ResponseEntity createCo
}
