package com.meet.time.interview.infra.adapters.input.data.request.contact;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactPropertiesRequestDTO {

    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String company;
    private String website;
    private String lifeCycleStage;

}
