package com.backservice.rest.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backservice.rest.entity.Doctor;
import com.backservice.rest.entity.Patient;
import com.backservice.rest.repository.DoctorRepository;
import com.backservice.rest.repository.PatientRepository;
import com.backservice.rest.service.HospitalService;

@Repository
@Transactional
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;

    @Override
    public Doctor showDoctorInformation(String name) {
        return doctorRepository.findByName(name);
    }

    @Override
    public Patient showPatientInformation(Integer id) {
        return patientRepository.findById(id).get();
    }

    @Override
    public boolean saveDoctorInformation(Doctor doctor) {
        doctorRepository.save(doctor);
        return true;
    }

    @Override
    public boolean savePatientInformation(Patient patient) {

        Doctor doctor = doctorRepository.findByName(patient.getDoctor_name());
        doctor.addPatients(patient);
        patientRepository.save(patient);
        doctor.setPatient_count();
        return true;
    }

    @Override
    public List<Patient> getPatientListOfDoctor(String name, Doctor doctor) {
        return doctor.getPatients();
    }

    @Override
    public List<Doctor> getDoctors() {
        return (List<Doctor>) doctorRepository.findAll();
    }
}
