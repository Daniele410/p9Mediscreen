package com.medipatient.medipatient.repository;

import com.medipatient.medipatient.model.Patient;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository <Patient, Long> {

    List<Patient> findByFirstName(String firstName);
}
