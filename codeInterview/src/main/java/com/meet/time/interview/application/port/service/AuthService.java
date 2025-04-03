package com.meet.time.interview.application.port.service;

import com.meet.time.interview.application.configuration.HubspotProperties;
import com.meet.time.interview.application.port.input.AccessTokenUseCase;
import com.meet.time.interview.application.port.output.HubspotUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.meet.time.interview.domain.utils.MessageConstants.REDIRECT_HUBSPOT_ACCESS_TOKEN_URL;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService implements AccessTokenUseCase {


    private final HubspotProperties hubspotProperties;
    private final HubspotUseCase hubspotUseCase;

    private final String redirectUri = REDIRECT_HUBSPOT_ACCESS_TOKEN_URL;


    @Override
    public String createRedirectUserUrl() {
        final String authUrl = hubspotProperties.getAuthorizationUrl();
        final String client_id = hubspotProperties.getClientId();

        return authUrl + "?client_id=" + client_id + "&redirect_uri=" + redirectUri + "&scope=oauth&state=WeHH_yy2irpl8UYAvv-my";
    }

    @Override
    public String returnAccessToken(String code) {
        final String client_id = hubspotProperties.getClientId();
        final String secret_keys = hubspotProperties.getClientSecret();

        return response.toString();
    }


}
