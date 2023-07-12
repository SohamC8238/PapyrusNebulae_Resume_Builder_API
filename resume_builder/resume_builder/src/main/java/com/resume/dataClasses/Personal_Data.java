package com.resume.dataClasses;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Personal_Data {
	
	@JsonProperty("name")
    private String name;
	
	@JsonProperty("last_name")
    private String last_name;
	
	@JsonProperty("email_address")
    private String email_address;
	
	@JsonProperty("phone_number")
    private String phone_number;
	
	@JsonProperty("linkedin_url")
    private String linkedin_url;

    public Personal_Data(String name, String last_name, String email, String phone, String linkedin){

        this.name = name;
        this.last_name = last_name;
        this.email_address = email;
        this.phone_number = phone;
        this.linkedin_url = linkedin;
    }

    public String tostring(){

        String ans = "";

        ans += "\"Name\" : " + "\"" + this.name + "\", " ;
        ans += "\"LastName\" : " + "\"" + this.last_name + "\", ";
        ans += "\"EmailAddress\" : "+ "\"" + this.email_address + "\", ";
        ans += "\"PhoneNumber\" : " + "\"" + this.phone_number + "\", ";
        String url_format = "\"<a href=\\\"" + this.linkedin_url + "\\\">linkedIn</a>\", ";
        ans += "\"LinkedIn\" : " + url_format;

        return ans;
    }

}