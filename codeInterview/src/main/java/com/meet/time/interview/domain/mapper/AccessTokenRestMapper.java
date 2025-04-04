package com.meet.time.interview.domain.mapper;

import com.meet.time.interview.domain.model.AccessToken;
import com.meet.time.interview.infra.adapters.input.data.response.AccessTokenResponseDTO;
import com.meet.time.interview.infra.adapters.output.client_apis.data.response.HubspotGetAccessTokenResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@NoArgsConstructor
public class AccessTokenRestMapper {

    public AccessToken toAccessToken(HubspotGetAccessTokenResponse response){
        return ObjectUtils.isEmpty(response) ? null: AccessToken
                .builder()
                .accessToken(response.getAccessToken())
                .refreshToken(response.getRefreshToken())
                .build();
    };

    public AccessTokenResponseDTO toAccessTokenResponseDTO(AccessToken response){
        return ObjectUtils.isEmpty(response) ? null: AccessTokenResponseDTO
                .builder()
                .accessToken(response.getAccessToken())
                .refreshToken(response.getRefreshToken())
                .build();
    };
}
