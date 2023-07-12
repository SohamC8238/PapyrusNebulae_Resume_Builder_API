package com.resume.dataClasses;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Achievements{

	@JsonProperty("field")
    private String type;
	
	@JsonProperty("awards")
    String description;

    public Achievements(String type, String description){

        this.type = type;
        this.description = description;
    }

    public String tostring(){

        String ans = "{";


        ans += "\"Type\": " + "\"" + this.type + "\", ";
        ans += "\"Description\": " + "\"" + this.description + "\"}";

        return ans;
    }
}