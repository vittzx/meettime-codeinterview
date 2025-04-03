package com.meet.time.interview.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccessToken {

    private String accessToken;
    private String refreshToken;
}
