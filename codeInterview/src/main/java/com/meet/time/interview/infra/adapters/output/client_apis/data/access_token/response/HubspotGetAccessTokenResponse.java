package com.meet.time.interview.infra.adapters.output.client_apis.data.access_token.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HubspotGetAccessTokenResponse {

    private String refreshToken;
    private String accessToken;
    private Long expiresIn;
}
