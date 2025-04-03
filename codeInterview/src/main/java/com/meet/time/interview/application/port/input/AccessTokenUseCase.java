package com.meet.time.interview.application.port.input;

import com.meet.time.interview.domain.model.AccessToken;

public interface AccessTokenUseCase {

    String createRedirectUserUrl();

    AccessToken returnAccessToken(String code);
}
