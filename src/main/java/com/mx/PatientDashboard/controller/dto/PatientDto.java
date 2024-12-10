package com.mx.PatientDashboard.controller.dto;

import lombok.Data;

@Data
public class PatientDto {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String email;
}