package com.meet.time.interview.application.port.service;

import com.meet.time.interview.application.configuration.HubspotProperties;
import com.meet.time.interview.application.port.input.AccessTokenUseCase;
import com.meet.time.interview.application.port.output.HubspotUseCase;
import com.meet.time.interview.domain.mapper.AccessTokenRestMapper;
import com.meet.time.interview.domain.model.AccessToken;
import com.meet.time.interview.infra.adapters.output.client_apis.data.response.HubspotGetAccessTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.meet.time.interview.domain.utils.MessageConstants.REDIRECT_HUBSPOT_ACCESS_TOKEN_URL;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService implements AccessTokenUseCase {


    private final HubspotProperties hubspotProperties;
    private final HubspotUseCase hubspotUseCase;
    private final AccessTokenRestMapper accessTokenMapper;

    private final String redirectUri = REDIRECT_HUBSPOT_ACCESS_TOKEN_URL;


    @Override
    public String createRedirectUserUrl() {
        final String authUrl = hubspotProperties.getAuthorizationUrl();
        final String client_id = hubspotProperties.getClientId();

        return authUrl + "?client_id=" + client_id + "&redirect_uri=" + redirectUri + "&scope=oauth&state=WeHH_yy2irpl8UYAvv-my";
    }

    @Override
    public AccessToken returnAccessToken(String code) {
        log.debug("Started return access token");
        final String client_id = hubspotProperties.getClientId();
        final String secret_keys = hubspotProperties.getClientSecret();
        HubspotGetAccessTokenResponse hbAccessTokenResponse = hubspotUseCase.getAccessToken(client_id, secret_keys, redirectUri, code);
        AccessToken token = accessTokenMapper.toAccessToken(hbAccessTokenResponse);
        log.debug("Finished return access token");
        return token;

    }


}
