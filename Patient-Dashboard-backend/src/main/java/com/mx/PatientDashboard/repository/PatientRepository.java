package com.mx.PatientDashboard.repository;

import com.mx.PatientDashboard.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
