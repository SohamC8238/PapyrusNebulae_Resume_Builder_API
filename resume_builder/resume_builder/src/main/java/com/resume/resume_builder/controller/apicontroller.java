package com.resume.resume_builder.controller;

import com.resume.Errors.TemplateError;
import com.resume.dataClasses.JsonData;
import com.resume.resume_builder.service.GeneratePDF;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.pdfservices.operation.internal.FileRefImpl;
import com.adobe.pdfservices.operation.io.FileRef;
import com.nimbusds.jose.shaded.json.parser.JSONParser;

//import com.resume.resume_builder.service.AdobeClient;


@RestController
public class apicontroller {
	
	
	@PostMapping(value = "/resume", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<byte[]> createResponseData(@RequestBody JsonData jd){
		
		if (Integer.parseInt(jd.getTemplateID()) <= 0 || Integer.parseInt(jd.getTemplateID()) >= 4 ) {
			throw new TemplateError("Template Not Found");
		}
		
         
        
        Path fileName = Path.of("./pdfservices-api-credentials.json");
        String client_details = null;
		try {
			client_details = Files.readString(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject client_json = new JSONObject(client_details);
		JSONObject client_credentials = client_json.getJSONObject("client_credentials");
		String client_id = client_credentials.getString("client_id");
		String client_secret = client_credentials.getString("client_secret");
		//System.out.print(jd.write2json());
        
		try {
	        
			GeneratePDF generatepdf = new GeneratePDF(client_id, client_secret, jd, 2);
			FileRef result = generatepdf.downloadPDF();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        
			result.saveAs(outputStream);
			
	        byte[] fileBytes = outputStream.toByteArray();
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.set("Content-Disposition", "attachment; filename=\"resume.pdf\"");
	        return ResponseEntity.ok().headers(headers).body(fileBytes);
		}catch (Exception E) {
			System.out.println(E);
			return null;
		}
       

	}

	
}
