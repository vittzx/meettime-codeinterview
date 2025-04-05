package com.meet.time.interview.domain.mapper;

import com.meet.time.interview.domain.model.Webhook;
import com.meet.time.interview.infra.adapters.input.data.request.contact.WebhookPostRequestContactCreated;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@NoArgsConstructor
public class WebhookRestMapper {

    public Webhook toWebhook(WebhookPostRequestContactCreated request){
        return ObjectUtils.isEmpty(request) ? null : Webhook
                .builder()
                .appId(request.getAppId())
                .attemptNumber(request.getAttemptNumber())
                .changeFlag(request.getChangeFlag())
                .changeSource(request.getChangeSource())
                .eventId(request.getEventId())
                .objectId(request.getObjectId())
                .occurredAt(request.getOccurredAt())
                .portalId(request.getPortalId())
                .subscriptionId(request.getSubscriptionId())
                .subscriptionType(request.getSubscriptionType())
                .build();
    }

}
