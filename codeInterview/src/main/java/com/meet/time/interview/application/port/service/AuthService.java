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


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService implements AccessTokenUseCase {


    private final HubspotProperties hubspotProperties;
    private final HubspotUseCase hubspotUseCase;
    private final AccessTokenRestMapper accessTokenMapper;



    @Override
    public String createRedirectUserUrl() {
        final String redirectUri = hubspotProperties.getRedirectUri();
        final String authUrl = hubspotProperties.getBaseOauthUrl() + hubspotProperties.getAuthorizationEndpoint();
        final String client_id = hubspotProperties.getClientId();

        return authUrl + "?client_id=" + client_id + "&redirect_uri=" + redirectUri + "&scope=oauth&optional_scope=crm.objects.line_items.read%20crm.objects.carts.write%20crm.objects.line_items.write%20crm.objects.carts.read%20media_bridge.read%20crm.objects.goals.write%20crm.objects.orders.write%20crm.objects.orders.read%20crm.objects.leads.read%20crm.objects.leads.write%20crm.objects.partner-clients.read%20tickets%20crm.objects.partner-clients.write%20e-commerce%20crm.objects.goals.read%20crm.objects.companies.read%20crm.objects.deals.read%20crm.objects.deals.write%20crm.objects.contacts.read%20crm.objects.subscriptions.read%20crm.objects.commercepayments.read%20crm.objects.invoices.read%20crm.objects.courses.read%20crm.objects.courses.write%20crm.objects.listings.read%20crm.objects.listings.write%20crm.objects.services.read%20crm.objects.users.read%20crm.objects.services.write%20crm.objects.contacts.write%20crm.objects.users.write%20crm.objects.appointments.read%20crm.objects.appointments.write%20crm.objects.invoices.write%20crm.objects.custom.read%20crm.objects.custom.write%20crm.objects.companies.write%20crm.objects.quotes.write%20crm.objects.quotes.read";
    }

    @Override
    public AccessToken returnAccessToken(String code) {
        log.debug("Started return access token");
        final String client_id = hubspotProperties.getClientId();
        final String redirectUri = hubspotProperties.getRedirectUri();
        final String secret_keys = hubspotProperties.getClientSecret();
        HubspotGetAccessTokenResponse hbAccessTokenResponse = hubspotUseCase.getAccessToken(client_id, secret_keys, redirectUri, code);
        AccessToken token = accessTokenMapper.toAccessToken(hbAccessTokenResponse);
        log.debug("Finished return access token");
        return token;

    }


}
