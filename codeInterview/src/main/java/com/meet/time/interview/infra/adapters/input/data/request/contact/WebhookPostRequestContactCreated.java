package com.meet.time.interview.infra.adapters.input.data.request.contact;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WebhookPostRequestContactCreated {
    private Long appId;
    private Long eventId;
    private Long portalId;
    private Long objectId;
    private Long subscriptionId;
    private String subscriptionType;
    private String changeSource;
    private String changeFlag;
    private Long occurredAt;
    private Long attemptNumber;

}
