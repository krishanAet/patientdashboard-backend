package com.mx.PatientDashboard.service.impl;

import com.mx.PatientDashboard.entity.Patient;
import com.mx.PatientDashboard.exception.PatientNotFoundException;
import com.mx.PatientDashboard.repository.PatientRepository;
import com.mx.PatientDashboard.service.PatientService;
import com.mx.PatientDashboard.service.dto.RequestPatientDto;
import com.mx.PatientDashboard.service.dto.ResponsePatientDto;
import com.mx.PatientDashboard.service.transformer.PatientTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
    try {
      final Patient savedPatient =
          patientRepository.save(patientTransformer.toEntity(requestPatientDto));
      final ResponsePatientDto.PatientData patientData =
          patientTransformer.toResponseData(savedPatient);

      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("200");
      response.setErrors(new ArrayList<>());
      response.setData(List.of(patientData));

      return response;
    } catch (DataIntegrityViolationException ex) {
      // Handle specific case of duplicate email
      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("400");
      response.setErrors(
          List.of(
              "Error on Creating Patient",
              "Duplicate entry: The provided email is already in use."));
      response.setData(null);
      return response;
    } catch (Exception ex) {
      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("500");
      response.setErrors(List.of("Error on Creating Patient", "Failed to create patient"));
      response.setData(null);
      return response;
    }
  }

  @Override
  public ResponsePatientDto getPatientById(final Long id) {
    try {
      final Patient patient =
          patientRepository
              .findById(id)
              .orElseThrow(() -> new PatientNotFoundException("Patient not found in the record"));

      final ResponsePatientDto.PatientData patientData = patientTransformer.toResponseData(patient);

      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("200");
      response.setErrors(new ArrayList<>());
      response.setData(List.of(patientData));

      return response;

    } catch (PatientNotFoundException ex) {
      // Handle specific case of patient not found
      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("404");
      response.setErrors(List.of("Update Patient", "Error on Retrieving Patient"));
      response.setData(null);
      return response;

    } catch (Exception ex) {
      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("500");
      response.setErrors(List.of("Update Patient", "Error on Retrieving Patient"));
      response.setData(null);
      return response;
    }
  }

  @Override
  public List<ResponsePatientDto> getAllPatients() {
    try {
      final List<Patient> patients = patientRepository.findAll();

      final List<ResponsePatientDto.PatientData> patientDataList =
          patients.stream().map(patientTransformer::toResponseData).collect(Collectors.toList());

      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("200");
      response.setErrors(new ArrayList<>());
      response.setData(patientDataList);

      return List.of(response);

    } catch (Exception ex) {
      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("500");
      response.setErrors(List.of("Retrieve Patients", "Error on Retrieving All Patients"));
      response.setData(null);
      return List.of(response);
    }
  }

  @Override
  public ResponsePatientDto updatePatient(
      final Long id, final RequestPatientDto requestPatientDto) {
    try {
      final Patient patient =
          patientRepository
              .findById(id)
              .orElseThrow(() -> new PatientNotFoundException("Patient not found in the record"));

      patientTransformer.updateEntityFromDto(patient, requestPatientDto);

      final Patient updatedPatient = patientRepository.save(patient);
      final ResponsePatientDto.PatientData patientData =
          patientTransformer.toResponseData(updatedPatient);

      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("200");
      response.setErrors(new ArrayList<>());
      response.setData(List.of(patientData));

      return response;

    } catch (PatientNotFoundException ex) {
      // Handle specific case of patient not found
      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("404");
      response.setErrors(List.of("Update Patient", "Error on Updating Patient: Patient not found"));
      response.setData(null);
      return response;

    } catch (Exception ex) {
      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("500");
      response.setErrors(List.of("Update Patient", "Error on Updating Patient"));
      response.setData(null);
      return response;
    }
  }

  @Override
  public ResponsePatientDto deletePatient(final Long id) {
    try {
      final Patient patient =
          patientRepository
              .findById(id)
              .orElseThrow(() -> new PatientNotFoundException("Patient not found in the record"));

      patientRepository.delete(patient);

      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("200");
      response.setErrors(new ArrayList<>());
      response.setData(null);

      return response;

    } catch (PatientNotFoundException ex) {
      // Handle specific case of patient not found
      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("404");
      response.setErrors(List.of("Delete Patient", "Error on Deleting Patient: Patient not found"));
      response.setData(null);
      return response;

    } catch (Exception ex) {
      // generic errors
      ResponsePatientDto response = new ResponsePatientDto();
      response.setCode("500");
      response.setErrors(List.of("Delete Patient", "Error on Deleting Patient"));
      response.setData(null);
      return response;
    }
  }
}
