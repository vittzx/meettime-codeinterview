package com.meet.time.interview.infra.adapters.input.data.response.contact;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactCreateRestResponse {

    private String id;
    private String email;
    private String fullName;
    private String company;

}
