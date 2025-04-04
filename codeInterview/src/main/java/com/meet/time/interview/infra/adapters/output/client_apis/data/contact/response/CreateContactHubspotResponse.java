package com.meet.time.interview.infra.adapters.output.client_apis.data.contact.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
public class CreateContactHubspotResponse {

    private String id;
    private CreateContactHubspotResponseProperties properties;
    private String createdAt;
    private String updatedAt;
    private boolean archived;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    @JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
    private static class CreateContactHubspotResponseProperties {

        private String company;
        private String createdate;
        private String email;
        private String firstname;
        private String hsAllContactVids;
        private String hsAssociatedTargetAccounts;
        private String hsCurrentlyEnrolledInProspectingAgent;
        private String hsEmailDomain;
        private String hsFullNameOrEmail;
        private String hsIsContact;
        private String hsIsUnworked;
        private String hsLifecycleStageMarketingQualifiedLeadDate;
        private String hsMarketableStatus;
        private String hsMarketableUntilRenewal;
        private String hsMembershipHasAccessedPrivateContent;
        private String hsObjectId;
        private String hsObjectSource;
        private String hsObjectSourceId;
        private String hsObjectSourceLabel;
        private String hsPipeline;
        private String hsProspectingAgentActivelyEnrolledCount;
        private String hsRegisteredMember;
        private String hsSearchableCalculatedPhoneNumber;
        private String hsSequencesActivelyEnrolledCount;
        private String lastmodifieddate;
        private String lastname;
        private String lifecyclestage;
        private String numNotes;
        private String phone;
        private String website;
    }
}
