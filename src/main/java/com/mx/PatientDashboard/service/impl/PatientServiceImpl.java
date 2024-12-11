package com.mx.PatientDashboard.service.impl;

import com.mx.PatientDashboard.entity.Patient;
import com.mx.PatientDashboard.repository.PatientRepository;
import com.mx.PatientDashboard.service.PatientService;
import com.mx.PatientDashboard.service.dto.RequestPatientDto;
import com.mx.PatientDashboard.service.dto.ResponsePatientDto;
import com.mx.PatientDashboard.service.transformer.PatientTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

  private final PatientRepository patientRepository;
  private final PatientTransformer patientTransformer;

  @Override
  public ResponsePatientDto createPatient(final RequestPatientDto requestPatientDto) {

    final Patient savedPatient = patientRepository.save(patientTransformer.toEntity(requestPatientDto));
    final ResponsePatientDto.PatientData patientData = patientTransformer.toResponseData(savedPatient);

    ResponsePatientDto response = new ResponsePatientDto();
    response.setCode("200");
    response.setErrors(new ArrayList<>());
    response.setData(List.of(patientData));

    return response;
  }

  @Override
  public ResponsePatientDto getPatientById(final Long id) {

    final Patient patient =
        patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found in the record"));

    final ResponsePatientDto.PatientData patientData = patientTransformer.toResponseData(patient);

    ResponsePatientDto response = new ResponsePatientDto();
    response.setCode("200");
    response.setErrors(new ArrayList<>());
    response.setData(List.of(patientData));

    return response;
  }

  @Override
  public List<ResponsePatientDto> getAllPatients() {

    final List<Patient> patients = patientRepository.findAll();

    final List<ResponsePatientDto.PatientData> patientDataList =
        patients.stream().map(patientTransformer::toResponseData).collect(Collectors.toList());

    ResponsePatientDto response = new ResponsePatientDto();
    response.setCode("200");
    response.setErrors(new ArrayList<>());
    response.setData(patientDataList);

    return List.of(response);
  }

  @Override
  public ResponsePatientDto updatePatient(final Long id, final RequestPatientDto requestPatientDto) {

    final Patient patient =
        patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found in the record"));

    patientTransformer.updateEntityFromDto(patient, requestPatientDto);

    final ResponsePatientDto.PatientData patientData = patientTransformer.toResponseData(patientRepository.save(patient));

    ResponsePatientDto response = new ResponsePatientDto();
    response.setCode("200");
    response.setErrors(new ArrayList<>());
    response.setData(List.of(patientData));

    return response;
  }

  @Override
  public ResponsePatientDto deletePatient(final Long id) {

    final Patient patient =
        patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found in the record"));

    patientRepository.delete(patient);

    ResponsePatientDto response = new ResponsePatientDto();
    response.setCode("200");
    response.setErrors(new ArrayList<>());
    response.setData(null);

    return response;
  }
}
