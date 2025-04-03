package com.meet.time.interview.application.port.input;

public interface AccessTokenUseCase {

    String createRedirectUserUrl();

    String returnAccessToken(String code);
}
