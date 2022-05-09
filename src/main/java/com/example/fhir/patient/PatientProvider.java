package com.example.fhir.patient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.param.DateRangeParam;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;

import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.OperationOutcome;

import org.hl7.fhir.dstu3.model.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class PatientProvider implements IResourceProvider {

    @Autowired
    private FhirContext ctx;

    @Autowired
    private PatientDao patientDao;

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return  org.hl7.fhir.dstu3.model.Patient.class;
    }

    private Map<String, Patient> patientMap = new HashMap<String, Patient>();

    @Search
    public List<Resource> searchPatient(HttpServletRequest request,
                                        @OptionalParam(name= Patient.SP_BIRTHDATE) DateRangeParam birthDate,
                                        @OptionalParam(name = Patient.SP_FAMILY) StringParam familyName,
                                        @OptionalParam(name= Patient.SP_GENDER) StringParam gender,
                                        @OptionalParam(name= Patient.SP_GIVEN) StringParam givenName,
                                        @OptionalParam(name = Patient.SP_IDENTIFIER) TokenParam identifier,
                                        @OptionalParam(name= Patient.SP_NAME) StringParam name,
                                        @OptionalParam(name = Patient.SP_RES_ID) TokenParam resid) {
        return patientDao.search(ctx, birthDate, familyName, gender, givenName, identifier, name);
    }

    @Read()
    public Patient read(@IdParam IdType theId) {
        return patientDao.read(ctx, theId);
    }

    @Create()
    public MethodOutcome createPatient(HttpServletRequest theRequest, @ResourceParam Patient patient) {
        MethodOutcome method = new MethodOutcome();
        method.setCreated(true);
        OperationOutcome opOutcome = new OperationOutcome();
        method.setOperationOutcome(opOutcome);
        try {
            Patient mongoPatient = patientDao.create(ctx, patient);

            method.setId(mongoPatient.getIdElement());
            method.setResource(mongoPatient);
        } catch (Exception ex) {
        }
        return method;
    }
}