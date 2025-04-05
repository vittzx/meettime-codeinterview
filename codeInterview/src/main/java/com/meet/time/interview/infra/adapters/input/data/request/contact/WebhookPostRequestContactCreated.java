package com.meet.time.interview.infra.adapters.input.data.request.contact;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WebhookPostRequestContactCreated {
    private Long objectId;
    private String propertyName;
    private String propertyValue;
    private String changeSource;
    private Long eventId;
    private Integer subscriptionId;
    private Integer portalId;
    private Integer appId;
    private Long occurredAt;
    private String eventType;
    private Integer attemptNumber;
}
