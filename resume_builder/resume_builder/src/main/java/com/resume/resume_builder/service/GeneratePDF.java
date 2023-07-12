package com.resume.resume_builder.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.validation.ValidatorFactory;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.auth.Credentials;
import com.adobe.pdfservices.operation.exception.SdkException;
import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.exception.ServiceUsageException;
import com.adobe.pdfservices.operation.internal.exception.UnauthorizedClientException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.DocumentMergeOperation;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.DocumentMergeOptions;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.OutputFormat;
import com.resume.dataClasses.JsonData;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

//@Service
public class GeneratePDF {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(GeneratePDF.class);
	 private String client_id = "c8af4a1312e445dca74758d5024b9f5e";
	 private String client_secret = "p8e-JpM6LfoDMcGn2wOUADYSDGxVxwvdOYtz";
	 private JsonData json;
	 private int templateID;
	 


    public GeneratePDF(String client_id, String client_secret, JsonData json, int templateID) {
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.json = json;
		this.templateID = templateID;
	}

	private void Validation() {

    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
    
        Set<ConstraintViolation<JsonData>> violations = validator.validate(this.json);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<JsonData> violation : violations) {
                LOGGER.error("Validation error: {}", violation.getMessage());
            }
            return; // Exit the method if there are validation errors
        }
    }
    
    public FileRef downloadPDF() {
    	
    	this.Validation();
    	try {
	        Credentials credentials = Credentials.servicePrincipalCredentialsBuilder()
	                .withClientId(client_id)
	                .withClientSecret(client_secret)
	                .build();
	
	        // Setup input data for the document merge process
	        String content = this.json.write2json();
	        JSONObject jsonDataForMerge = new JSONObject(content);
	
	        // Create an ExecutionContext using credentials.
	        ExecutionContext executionContext = ExecutionContext.create(credentials);
	
	        // Create a new DocumentMergeOptions instance
	        DocumentMergeOptions documentMergeOptions = new DocumentMergeOptions(jsonDataForMerge, OutputFormat.PDF);
	
	        // Create a new DocumentMergeOperation instance with the DocumentMergeOptions instance
	        DocumentMergeOperation documentMergeOperation = DocumentMergeOperation.createNew(documentMergeOptions);
	        
	        
	        // Set the operation input document template from a source file.
	        if (this.templateID == 1) {
	        	FileRef documentTemplate = FileRef.createFromLocalFile("./src/main/resources/static/BasicTemplate.docx");
	            documentMergeOperation.setInput(documentTemplate);
	        }else if (this.templateID == 2) {
	        	FileRef documentTemplate = FileRef.createFromLocalFile("./src/main/resources/static/LinkTemplate.docx");
	            documentMergeOperation.setInput(documentTemplate);
	        }else {
	        	FileRef documentTemplate = FileRef.createFromLocalFile("./src/main/resources/static/ImageTemplate.docx");
	        	documentMergeOperation.setInput(documentTemplate);
	        
	        }
	        
	        // Execute the operation
	        FileRef result = null;
			
				result = documentMergeOperation.execute(executionContext);
	
	        // Save the result to the specified location.
//	        String outputFilePath = createOutputFilePath();
//	
//				result.saveAs(outputFilePath);
				
			return result;


    	}catch (Exception E) {
    		System.out.print(E);
    		return null;
    	}

        
    }

    // Generates a string containing a directory structure and file name for the output file.
    private String createOutputFilePath(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String timeStamp = dateTimeFormatter.format(now);
        return("output/TemplateID_" + this.templateID +"/Resume"+ timeStamp  +".pdf");
    }
}
//package com.resume.resume_builder.service;
//import com.resume.dataClasses.JsonData;
//import com.adobe.pdfservices.operation.ExecutionContext;
//import com.adobe.pdfservices.operation.auth.Credentials;
//import com.adobe.pdfservices.operation.exception.SdkException;
//import com.adobe.pdfservices.operation.exception.ServiceApiException;
//import com.adobe.pdfservices.operation.exception.ServiceUsageException;
//import com.adobe.pdfservices.operation.io.FileRef;
//import com.adobe.pdfservices.operation.pdfops.DocumentMergeOperation;
//import com.adobe.pdfservices.operation.pdfops.options.documentmerge.DocumentMergeOptions;
//import com.adobe.pdfservices.operation.pdfops.options.documentmerge.OutputFormat;
//import com.adobe.pdfservices.operation.proxy.ProxyScheme;
//import com.adobe.pdfservices.operation.proxy.ProxyServerConfig;
//
//
//import org.apache.http.HttpHost;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Set;
//
//@Service
//public class GeneratePDF {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratePDF.class);
//
//    public static void GeneratePDF(JsonData request) {
//
//        try {
//            // Create a ValidatorFactory and Validator
//            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//            Validator validator = factory.getValidator();
//
//            // Validate the request object
//            Set<ConstraintViolation<JsonData>> violations = validator.validate(request);
//            if (!violations.isEmpty()) {
//                for (ConstraintViolation<JsonData> violation : violations) {
//                    LOGGER.error("Validation error: {}", violation.getMessage());
//                }
//                return; // Exit the method if there are validation errors
//            }            
//
//            // Initial setup, create credentials instance.
//            Credentials credentials = Credentials.servicePrincipalCredentialsBuilder()
//                    .withClientId("066973b6590a4421a7b40418099e8824")
//                    .withClientSecret("p8e-CvyF4HkpkUJc_0jsCTX3QSbsn2pTjoNu")
//                    .build();
//
//            // Setup input data for the document merge process
//            String content = request.write2json();
//            JSONObject jsonDataForMerge = new JSONObject(content);
//
//            // Create an ExecutionContext using credentials.
//            ExecutionContext executionContext = ExecutionContext.create(credentials);
//
//            // Create a new DocumentMergeOptions instance
//            DocumentMergeOptions documentMergeOptions = new DocumentMergeOptions(jsonDataForMerge, OutputFormat.PDF);
//
//            // Create a new DocumentMergeOperation instance with the DocumentMergeOptions instance
//            DocumentMergeOperation documentMergeOperation = DocumentMergeOperation.createNew(documentMergeOptions);
//
//            // Set the operation input document template from a source file.
//            FileRef documentTemplate = FileRef.createFromLocalFile("C:\\Users\\A V Support\\OneDrive\\Desktop\\projects\\APIGeneration\\resume\\src\\main\\resources\\BasicTemplate.docx");
//            documentMergeOperation.setInput(documentTemplate);
//
//            // Execute the operation
//            FileRef result = documentMergeOperation.execute(executionContext);
//
//            // Save the result to the specified location.
//            String outputFilePath = createOutputFilePath();
//            result.saveAs(outputFilePath);
//
//        } catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
//            LOGGER.error("Exception encountered while executing operation", ex);
//        }
//    }
//
//    // Generates a string containing a directory structure and file name for the output file.
//    public static String createOutputFilePath(){
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
//        LocalDateTime now = LocalDateTime.now();
//        String timeStamp = dateTimeFormatter.format(now);
//        return("output/MergeDocumentToPDF/merge" + timeStamp + ".pdf");
//    }
//}
//
//	            
	            
	            

