package com.meet.time.interview.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Webhook {
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
