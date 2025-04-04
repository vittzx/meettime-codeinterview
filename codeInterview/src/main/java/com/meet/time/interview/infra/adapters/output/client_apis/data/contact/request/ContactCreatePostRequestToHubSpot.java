package com.meet.time.interview.infra.adapters.output.client_apis.data.contact.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactCreatePostRequestToHubSpot {
    private ContactCreatePropertiesPostRequestToHubSpot properties;
}
