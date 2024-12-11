package com.mx.PatientDashboard.controller;

import com.mx.PatientDashboard.service.PatientService;
import com.mx.PatientDashboard.service.dto.RequestPatientDto;
import com.mx.PatientDashboard.service.dto.ResponsePatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${patient.base.url}")
public class PatientController {

  private final PatientService patientService;

  // Add new patient record
  @PostMapping
  public ResponseEntity<ResponsePatientDto> createPatient(@RequestBody final RequestPatientDto requestPatientDto) {
    final ResponsePatientDto response = patientService.createPatient(requestPatientDto);
    return ResponseEntity.ok(response);
  }

  // Get a patient by patient id
  @GetMapping("/{id}")
  public ResponseEntity<ResponsePatientDto> getPatientById(@PathVariable final Long id) {
    final ResponsePatientDto response = patientService.getPatientById(id);
    return ResponseEntity.ok(response);
  }

  // Get all patients information
  @GetMapping
  public ResponseEntity<List<ResponsePatientDto>> getAllPatients() {
    final List<ResponsePatientDto> response = patientService.getAllPatients();
    return ResponseEntity.ok(response);
  }

  // Update a patient by patient id
  @PutMapping("/{id}")
  public ResponseEntity<ResponsePatientDto> updatePatient(
          @PathVariable final Long id,
          @RequestBody final RequestPatientDto requestPatientDto
  ) {
    final ResponsePatientDto response = patientService.updatePatient(id, requestPatientDto);
    return ResponseEntity.ok(response);
  }

  // Delete a patient by patiet id
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponsePatientDto> deletePatient(@PathVariable final Long id) {
    final ResponsePatientDto response = patientService.deletePatient(id);
    return ResponseEntity.ok(response);
  }
}
