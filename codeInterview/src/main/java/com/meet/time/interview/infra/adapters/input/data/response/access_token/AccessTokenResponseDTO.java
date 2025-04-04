package com.meet.time.interview.infra.adapters.input.data.response.access_token;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccessTokenResponseDTO {

    private String accessToken;
    private String refreshToken;
}
