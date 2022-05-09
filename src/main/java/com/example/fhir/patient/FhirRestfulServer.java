package com.example.fhir.patient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.RestfulServer;
import org.springframework.context.ApplicationContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.Arrays;

@WebServlet("/*")
public class FhirRestfulServer extends RestfulServer {

    private ApplicationContext applicationContext;

    FhirRestfulServer(ApplicationContext context) {

        this.applicationContext = context;
    }

    @Override
    protected void initialize() throws ServletException{
        super.initialize();
        setFhirContext(FhirContext.forDstu3());
        //setFhirContext(FhirContext.forR4());
        setResourceProviders(Arrays.asList(
                applicationContext.getBean(PatientProvider.class)));
    }
}
