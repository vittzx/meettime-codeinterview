package com.meet.time.interview.infra.adapters.input.data.response.global;

import com.meet.time.interview.domain.enums.STATUS_RESPONSE_REST_REQUEST;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DefaultRestResponse<T> {

    private STATUS_RESPONSE_REST_REQUEST type;
    private T response;

}
