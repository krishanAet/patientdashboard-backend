package com.mx.PatientDashboard.service.transformer;

import com.mx.PatientDashboard.entity.Patient;
import com.mx.PatientDashboard.service.dto.RequestPatientDto;
import com.mx.PatientDashboard.service.dto.ResponsePatientDto;
import org.springframework.stereotype.Component;

@Component
public class PatientTransformer {

  public Patient toEntity(final RequestPatientDto dto) {
    Patient patient = new Patient();
    patient.setFirstName(dto.getFirstName());
    patient.setLastName(dto.getLastName());
    patient.setAddress(dto.getAddress());
    patient.setCity(dto.getCity());
    patient.setState(dto.getState());
    patient.setZipCode(dto.getZipCode());
    patient.setPhoneNumber(dto.getPhoneNumber());
    patient.setEmail(dto.getEmail());
    return patient;
  }

  public ResponsePatientDto.PatientData toResponseData(final Patient entity) {
    ResponsePatientDto.PatientData patientData = new ResponsePatientDto.PatientData();
    patientData.setId(entity.getId());
    patientData.setFirstName(entity.getFirstName());
    patientData.setLastName(entity.getLastName());
    patientData.setAddress(entity.getAddress());
    patientData.setCity(entity.getCity());
    patientData.setState(entity.getState());
    patientData.setZipCode(entity.getZipCode());
    patientData.setPhoneNumber(entity.getPhoneNumber());
    patientData.setEmail(entity.getEmail());
    return patientData;
  }

  public Patient updateEntityFromDto(Patient entity, final RequestPatientDto dto) {
    entity.setFirstName(dto.getFirstName());
    entity.setLastName(dto.getLastName());
    entity.setAddress(dto.getAddress());
    entity.setCity(dto.getCity());
    entity.setState(dto.getState());
    entity.setZipCode(dto.getZipCode());
    entity.setPhoneNumber(dto.getPhoneNumber());
    entity.setEmail(dto.getEmail());
    return entity;
  }
}
