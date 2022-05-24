package com.backservice.rest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backservice.rest.entity.Doctor;
import com.backservice.rest.service.HospitalService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {

    @Autowired
    HospitalService service;

    @PostMapping("/doctors/doctor")
    public boolean addUser(@RequestBody Doctor doctor) {
        service.saveDoctorInformation(doctor);
		return true;

    }

    @GetMapping("doctors/doctor/{name}")
	public MappingJacksonValue getDoctorInformation(@PathVariable String name) {
		Doctor doctor = service.showDoctorInformation(name);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "field","patient_count");

		FilterProvider filters = new SimpleFilterProvider().addFilter("DoctorFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(doctor);

		mapping.setFilters(filters);

		return mapping;
	}

	@GetMapping("doctors/doc/{name}")
	public Doctor getDoctorInfo(@PathVariable String name) {
		Doctor doctor = service.showDoctorInformation(name);
		return doctor;
	}

    @GetMapping("doctors")
	public MappingJacksonValue getListOfDoctor() {
		List<Doctor> doctors = service.getDoctors() ;
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");

		FilterProvider filters = new SimpleFilterProvider().addFilter("DoctorFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(doctors);

		mapping.setFilters(filters);

		return mapping;
	}
}
