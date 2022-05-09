package com.example.fhir.patient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hl7.fhir.dstu3.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PatientApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		Patient patient = new Patient();
		patient.setId("1");
		patient.addIdentifier().setSystem("http://optum.com/MRNs").setValue("007");
		patient.addName().setFamily("Chakravarty").addGiven("Mithun").addGiven("A");
		patient.addAddress().addLine("Address Line 1");
		patient.addAddress().setCity("Mumbai");
		patient.addAddress().setCountry("India");
		patient.addTelecom().setValue("111-111-1111");
		ObjectMapper mapper = new ObjectMapper();
		Patient person = new Patient();
		String str = mapper.writeValueAsString(person);
		System.out.println(str);
	}

}
