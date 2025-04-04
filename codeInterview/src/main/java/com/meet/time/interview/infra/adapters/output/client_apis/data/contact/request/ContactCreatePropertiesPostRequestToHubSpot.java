package com.meet.time.interview.infra.adapters.output.client_apis.data.contact.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ContactCreatePropertiesPostRequestToHubSpot {

    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private String company;
    private String website;
    private String lifecyclestage;

}
