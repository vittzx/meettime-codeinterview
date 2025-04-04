package com.meet.time.interview.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String company;
    private String website;
    private String lifeCycleStage;

}
