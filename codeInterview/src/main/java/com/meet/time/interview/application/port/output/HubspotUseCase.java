package com.meet.time.interview.application.port.output;

import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.request.ContactCreatePropertiesPostRequestToHubSpot;
import com.meet.time.interview.infra.adapters.output.client_apis.data.contact.response.CreateContactHubspotResponse;
import com.meet.time.interview.infra.adapters.output.client_apis.data.access_token.response.HubspotGetAccessTokenResponse;

public interface HubspotUseCase {

    HubspotGetAccessTokenResponse getAccessToken(String clientId, String secretKeys, String redirectUri, String code);

    CreateContactHubspotResponse createContact(ContactCreatePropertiesPostRequestToHubSpot request, String accessToken);
}
