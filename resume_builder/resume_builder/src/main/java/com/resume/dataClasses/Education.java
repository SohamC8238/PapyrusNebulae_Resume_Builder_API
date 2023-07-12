package com.resume.dataClasses;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Education {

	@JsonProperty("school_name")
    private String schoolName;
    
	@JsonProperty("passing_year")
	private String year;
	
	@JsonProperty("description")
    String description;

    public Education(String school_name, String year, String description){
        this.schoolName = school_name;
        this.year = year;
        this.description = description;
    }

    public String tostring(){

        String ans = "{";

        ans += "\"SchoolName\": " + "\""+ this.schoolName + "\", ";
        ans += "\"Year\": " + "\"" + this.year + "\", ";
        ans += "\"Description\": " + "\"" + this.description + "\" }";


        return ans;
    }
}