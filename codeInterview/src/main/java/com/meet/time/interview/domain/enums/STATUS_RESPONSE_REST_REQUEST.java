package com.meet.time.interview.domain.enums;

public enum STATUS_RESPONSE_REST_REQUEST {

    SUCCESS("S"),
    ERROR("E");

    private String value;

    STATUS_RESPONSE_REST_REQUEST(String value){
        this.value =value;
    }

    public String getValue(){return value;}
}
