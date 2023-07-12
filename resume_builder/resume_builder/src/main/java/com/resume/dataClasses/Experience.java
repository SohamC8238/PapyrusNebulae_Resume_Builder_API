package com.resume.dataClasses;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Experience {
	
	@JsonProperty("company_name")
    private String companyName;
    
	@JsonProperty("passing_year")
	private String year;
	
	@JsonProperty("responsibilities")
    private String description;

    public Experience(String companyName, String year, String description){

        this.companyName = companyName;
        this.year = year;
        this.description = description;
    }

    public String tostring(){

        String ans = "{";


        ans += "\"CompanyName\": " + "\"" + this.companyName+ "\", ";
        ans += "\"Year\": " + "\"" + this.year+ "\", ";
        ans += "\"Description\": " + "\"" + this.description + "\"}";

        return ans;
    }
}