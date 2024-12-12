package com.mx.PatientDashboard.service;

import com.mx.PatientDashboard.service.dto.ResponsePatientDto;
import com.mx.PatientDashboard.service.dto.RequestPatientDto;

import java.util.List;

public interface PatientService {

  ResponsePatientDto createPatient(RequestPatientDto requestPatientDto);

  ResponsePatientDto getPatientById(Long id);

  List<ResponsePatientDto> getAllPatients();

  ResponsePatientDto updatePatient(Long id, RequestPatientDto requestPatientDto);

  ResponsePatientDto deletePatient(Long id);
}
