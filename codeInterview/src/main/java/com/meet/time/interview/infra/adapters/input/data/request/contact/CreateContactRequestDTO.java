package com.meet.time.interview.infra.adapters.input.data.request.contact;


import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateContactRequestDTO {

    private List<ContactPropertiesRequestDTO> contacts;

}
