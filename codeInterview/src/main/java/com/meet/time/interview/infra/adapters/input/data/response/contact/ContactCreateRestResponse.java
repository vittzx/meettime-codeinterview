package com.meet.time.interview.infra.adapters.input.data.response.contact;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactCreateRestResponse {

    List<ContactCreatePropertiesRestResponse> contacts;
}
