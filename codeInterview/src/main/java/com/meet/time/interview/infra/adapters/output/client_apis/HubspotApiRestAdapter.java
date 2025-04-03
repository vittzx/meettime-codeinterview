package com.meet.time.interview.infra.adapters.output.client_apis;

import com.meet.time.interview.application.port.output.HubspotUseCase;
import com.meet.time.interview.infra.adapters.output.client_apis.data.response.HubspotGetAccessTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static com.meet.time.interview.domain.utils.MessageConstants.HUBSPOT_ACCESS_TOKEN_URL;

@Service
@Slf4j
public class HubspotApiRestAdapter implements HubspotUseCase {


    @Override
    public HubspotGetAccessTokenResponse getAccessToken(String clientId, String secretKeys, String redirectUri, String code) {
        HttpEntity<MultiValueMap<String, String>> request = buildRequest(clientId, secretKeys, redirectUri, code);
        HubspotGetAccessTokenResponse response = callRequest(HUBSPOT_ACCESS_TOKEN_URL, request);
        return response;
    }

    private HttpEntity<MultiValueMap<String, String>> buildRequest
            (String clientId, String secretKeys,
             String redirectUri, String code)
    {
        log.debug("Building body to hubspot get token endpoint");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", secretKeys);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        return new HttpEntity<>(params, headers);

    }

    private HubspotGetAccessTokenResponse callRequest(String url, HttpEntity<MultiValueMap<String, String>> request){
        log.debug("Started callRequest to url {}", url);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HubspotGetAccessTokenResponse> response = restTemplate.postForEntity(url, request, HubspotGetAccessTokenResponse.class);

        if(!response.getStatusCode().is2xxSuccessful()){
            log.error("ERROR: Response status code is != 200.");
        }
        log.debug("Finished callRequest with successfully, return body");
        return response.getBody();
    }
}
