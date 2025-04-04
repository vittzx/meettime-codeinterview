package com.meet.time.interview.infra.adapters.output.client_apis;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateClient<Req, Res> {
    public  RestTemplate initRequest(){
        return new RestTemplate();
    }

    public  ResponseEntity<Res> postMultiPartEntity(RestTemplate request, String url, HttpEntity<MultiValueMap<String, String>> body, Class<Res> type){
        return request.postForEntity(url, body, type);
    }

    public  ResponseEntity<Res> postJsonEntity(RestTemplate request, String url, HttpEntity<Req> body, Class<Res> type){
        return request.postForEntity(url, body, type);
    }

    public HttpEntity<MultiValueMap<String, String>> buildMultipartBody(MultiValueMap<String, String> params, HttpHeaders headers){
        return new HttpEntity<>(params, headers);
    }

    public HttpEntity<Req> buildJsonBody(Req params, HttpHeaders headers){
        return new HttpEntity<>(params, headers);
    }

    public HttpHeaders initHeaders(){
        return new HttpHeaders();
    }

    public void setHeaderContentType(HttpHeaders httpHeaders, MediaType mediaType){
        httpHeaders.setContentType(mediaType);
    }

    public void addHeadersParam(HttpHeaders headers, String headerName, String headerValue){
        headers.add(headerName, headerValue);
    }
}
