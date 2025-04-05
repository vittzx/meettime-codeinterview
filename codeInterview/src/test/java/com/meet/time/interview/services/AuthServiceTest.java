package com.meet.time.interview.services;

import com.meet.time.interview.application.configuration.HubspotProperties;
import com.meet.time.interview.application.port.output.HubspotUseCase;
import com.meet.time.interview.application.port.service.AuthService;
import com.meet.time.interview.domain.mapper.AccessTokenRestMapper;
import com.meet.time.interview.domain.model.AccessToken;
import com.meet.time.interview.infra.adapters.output.client_apis.data.access_token.response.HubspotGetAccessTokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {


    @Mock
    private HubspotProperties hubspotProperties;

    @Mock
    private AccessTokenRestMapper accessTokenRestMapper;

    @Mock
    private HubspotUseCase hubspotUseCase;

    @InjectMocks
    private AuthService service;

    @BeforeEach
    void setUp(){
        // propriedades feitas para teste.
        hubspotProperties.setRedirectUri("REDIRECT_URI");
        hubspotProperties.setBaseOauthUrl("https://oauth");
        hubspotProperties.setAuthorizationEndpoint("/token");
        hubspotProperties.setClientId("CLIENT_ID");
        hubspotProperties.setClientSecret("SECRET_KEYS");

    }


    @Test
    void shouldReturnUrlSuccessfully(){
        final String expectedRedirectUri = hubspotProperties.getRedirectUri();
        final String expectedAuthUrl = hubspotProperties.getBaseOauthUrl() + hubspotProperties.getAuthorizationEndpoint();
        final String expectedClientId = hubspotProperties.getClientId();

        String expectedUrl = expectedAuthUrl + "?client_id=" + expectedClientId + "&redirect_uri=" + expectedRedirectUri + "&scope=oauth&optional_scope=crm.objects.line_items.read%20crm.objects.carts.write%20crm.objects.line_items.write%20crm.objects.carts.read%20media_bridge.read%20crm.objects.goals.write%20crm.objects.orders.write%20crm.objects.orders.read%20crm.objects.leads.read%20crm.objects.leads.write%20crm.objects.partner-clients.read%20tickets%20crm.objects.partner-clients.write%20e-commerce%20crm.objects.goals.read%20crm.objects.companies.read%20crm.objects.deals.read%20crm.objects.deals.write%20crm.objects.contacts.read%20crm.objects.subscriptions.read%20crm.objects.commercepayments.read%20crm.objects.invoices.read%20crm.objects.courses.read%20crm.objects.courses.write%20crm.objects.listings.read%20crm.objects.listings.write%20crm.objects.services.read%20crm.objects.users.read%20crm.objects.services.write%20crm.objects.contacts.write%20crm.objects.users.write%20crm.objects.appointments.read%20crm.objects.appointments.write%20crm.objects.invoices.write%20crm.objects.custom.read%20crm.objects.custom.write%20crm.objects.companies.write%20crm.objects.quotes.write%20crm.objects.quotes.read";
        String result = service.createRedirectUserUrl();

        assertNotNull(result);
        assertEquals(expectedUrl, result);
    }

    @Test
    void shouldReturnAccessTokenSuccessfully(){
        String code = "CODE";
        final String mockClientId = hubspotProperties.getClientId();
        final String mockRedirectUri = hubspotProperties.getRedirectUri();
        final String mockSecretKeys = hubspotProperties.getClientSecret();

        AccessToken expectedAccessToken = AccessToken.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .build();

        HubspotGetAccessTokenResponse responseHubspot = HubspotGetAccessTokenResponse
                .builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .expiresIn(Long.valueOf("1800"))
                .build();

        when(hubspotUseCase.getAccessToken(mockClientId, mockSecretKeys, mockRedirectUri, code)).thenReturn(responseHubspot);

        when(accessTokenRestMapper.toAccessToken(responseHubspot)).thenReturn(
                        AccessToken
                                .builder()
                                .accessToken("accessToken")
                                .refreshToken("refreshToken")
                                .build());

        AccessToken result = service.returnAccessToken(code);

        assertNotNull(result);
        assertEquals(expectedAccessToken.getAccessToken(), result.getAccessToken());
        assertEquals(expectedAccessToken.getRefreshToken(), result.getRefreshToken());
    }



}
