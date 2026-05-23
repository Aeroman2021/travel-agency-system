package com.myproject.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Sex {
    MALE,FEMALE;


    @JsonCreator
    public static Sex from(String value){
        return Sex.valueOf(value.toUpperCase());
    }
}
