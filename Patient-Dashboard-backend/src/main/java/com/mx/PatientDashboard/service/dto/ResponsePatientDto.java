package com.mx.PatientDashboard.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponsePatientDto {

  private String code;
  private List<String> errors;
  private List<PatientData> data;

  @Data
  public static class PatientData {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String email;
  }
}
