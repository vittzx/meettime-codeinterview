package com.meet.time.interview.application.port.output;

import com.meet.time.interview.infra.adapters.output.client_apis.data.response.HubspotGetAccessTokenResponse;

public interface HubspotUseCase {

    HubspotGetAccessTokenResponse getAccessToken(String clientId, String secretKeys, String redirectUri, String code);

}
