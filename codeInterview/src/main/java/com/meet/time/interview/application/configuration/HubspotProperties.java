package com.meet.time.interview.application.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.hubspot-app")
public class HubspotProperties {

    private String clientSecret;
    private String clientApp;
    private String clientId;
    private String authorizationUrl;

}

