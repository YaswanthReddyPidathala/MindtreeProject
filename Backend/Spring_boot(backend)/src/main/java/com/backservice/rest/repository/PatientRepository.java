package com.backservice.rest.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backservice.rest.entity.Patient;

@Repository

public interface PatientRepository extends CrudRepository<Patient,Integer> {
    
}
