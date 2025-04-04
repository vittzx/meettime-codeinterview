package com.meet.time.interview.infra.adapters.output.client_apis;

import com.meet.time.interview.application.configuration.HubspotProperties;
import com.meet.time.interview.application.port.output.HubspotUseCase;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.request.ContactCreatePostRequestToHubSpot;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.request.ContactCreatePropertiesPostRequestToHubSpot;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.response.CreateContactHubspotResponse;
import com.meet.time.interview.infra.adapters.output.client_apis.data.access_token.response.HubspotGetAccessTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@Service
@Slf4j
@RequiredArgsConstructor
public class HubspotApiRestAdapter implements HubspotUseCase {

    private final HubspotProperties hubspotProperties;
    private final RestTemplateClient<MultiValueMap<String,String>, HubspotGetAccessTokenResponse> accessTokenRequest;
    private final RestTemplateClient<ContactCreatePostRequestToHubSpot, CreateContactHubspotResponse> createContactOnHubspot;

    @Override
    public HubspotGetAccessTokenResponse getAccessToken(String clientId, String secretKeys, String redirectUri, String code) {
        HttpEntity<MultiValueMap<String, String>> request = buildMultiPartRequest(buildAccessTokenMultiPartBody(clientId, secretKeys, redirectUri, code));
        String url = hubspotProperties.getBaseUrl() + hubspotProperties.getAccessTokenEndpoint();
        return callAccessTokenRequest(url, request);
    }

    @Override
    public CreateContactHubspotResponse createContact(ContactCreatePropertiesPostRequestToHubSpot request, String accessToken) {
        HttpEntity<ContactCreatePostRequestToHubSpot> body = buildJsonRequest(accessToken, ContactCreatePostRequestToHubSpot.builder().properties(request).build());
        String url = hubspotProperties.getBaseUrl() + hubspotProperties.getContactEndpoint();
        return callCreateContactRequest(url, body);

    }

    private HttpEntity<MultiValueMap<String, String>> buildMultiPartRequest(MultiValueMap<String, String> params)
    {
        log.debug("Building body to hubspot get token endpoint");
        HttpHeaders headers = accessTokenRequest.initHeaders();
        accessTokenRequest.setHeaderContentType(headers, MediaType.APPLICATION_FORM_URLENCODED);
        return accessTokenRequest.buildMultipartBody(params, headers);
    }

    private HttpEntity<ContactCreatePostRequestToHubSpot> buildJsonRequest(String accessToken, ContactCreatePostRequestToHubSpot params){
        log.debug("Bulding request to createContact: {}", params);
        HttpHeaders headers = createContactOnHubspot.initHeaders();
        createContactOnHubspot.addHeadersParam(headers, "authorization", accessToken);
        createContactOnHubspot.setHeaderContentType(headers, MediaType.APPLICATION_JSON);
        return createContactOnHubspot.buildJsonBody(params, headers);
    }

    private MultiValueMap<String, String> buildAccessTokenMultiPartBody(String clientId, String secretKeys,
                                                                        String redirectUri, String code)
    {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", secretKeys);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        return params;
    }

    private HubspotGetAccessTokenResponse callAccessTokenRequest(String url, HttpEntity<MultiValueMap<String, String>> request){
        log.debug("Started GET to access token on {}", url);
        ResponseEntity<HubspotGetAccessTokenResponse> response = accessTokenRequest.postMultiPartEntity(accessTokenRequest.initRequest(), url, request, HubspotGetAccessTokenResponse.class);
        isResponseValid(response);
        log.debug("Finished callRequest with successfully, return body");
        return response.getBody();
    }

    private CreateContactHubspotResponse callCreateContactRequest(String url, HttpEntity<ContactCreatePostRequestToHubSpot> body){
        log.debug("Started POST to create contact on {}", url);
        ResponseEntity<CreateContactHubspotResponse> response = createContactOnHubspot.postJsonEntity( createContactOnHubspot.initRequest() ,url ,body, CreateContactHubspotResponse.class);
        isResponseValid(response);
        return response.getBody();
    };

    private void isResponseValid(ResponseEntity<?> response){
        if (!(response.getStatusCode().is2xxSuccessful())){
            throw new IllegalStateException(("Requisição inválida"));
        }
    }
}
