package com.meet.time.interview.application.port.output;

import org.springframework.web.client.RestTemplate;

public interface HubspotUseCase {

    Object getAccessToken(String url, RestTemplate restTemplate, Class type);
}
