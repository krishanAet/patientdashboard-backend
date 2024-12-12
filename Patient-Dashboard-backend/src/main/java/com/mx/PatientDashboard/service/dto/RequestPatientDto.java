package com.mx.PatientDashboard.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestPatientDto {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("zip_code")
    private String zipCode;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;
}
