package com.meet.time.interview.infra.adapters.output.handler_exceptions.data.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExceptionResponse {
    private String message;
}
