package com.meet.time.interview.domain.mapper;

import com.meet.time.interview.domain.model.AccessToken;
import com.meet.time.interview.infra.adapters.input.data.response.AccessTokenResponseDTO;
import com.meet.time.interview.infra.adapters.output.client_apis.data.response.HubspotGetAccessTokenResponse;
import org.mapstruct.Mapper;

@Mapper
public interface AccessTokenRestMapper {

    AccessToken toAccessToken(HubspotGetAccessTokenResponse response);

    AccessTokenResponseDTO toAccessTokenResponseDTO(AccessToken response);
}
