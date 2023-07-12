package com.resume.dataClasses;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonData {
	@JsonProperty("template_id")
    private String template_id;
	
	@JsonProperty("personal_information")
    private Personal_Data personal;
	
	@JsonProperty("job_title")
    private String job_title;
	
	@JsonProperty("career_objective")
    private String summary;
	
	@JsonProperty("skills")
    private ArrayList<String> Skills;
	
	@JsonProperty("education")
    private ArrayList<Education> education;
	
	@JsonProperty("experience")
    private ArrayList<Experience> experience;
	
	@JsonProperty("achievements")
    ArrayList<Achievements> achievements;
	
    
    public JsonData(String template_id, Personal_Data personal, String job_title, String summary,
			ArrayList<String> skills, ArrayList<Education> education, ArrayList<Experience> experience,
			ArrayList<Achievements> achievements) {
		super();
		this.template_id = template_id;
		this.personal = personal;
		this.job_title = job_title;
		this.summary = summary;
		this.Skills = new ArrayList<String>();
        for (int i =0; i<skills.size(); i++){
            Skills.add("\"" + skills.get(i) + "\"");
        }
		this.education = education;
		this.experience = experience;
		this.achievements = achievements;
	}

	public String getTemplateID() {
    	return this.template_id;
    }

    public void setTemplateId(String template_id){
        this.template_id = template_id;
    }

    public void setPersonalData(String name, String last_name, String email, String phone, String linkedin){
        personal = new Personal_Data(name, last_name, email, phone, linkedin);
    }

    public void setJob_title(String jobTitle){
        this.job_title = jobTitle;
    }

    public void setSummary(String summary){
        this.summary = summary;
    }

    public void setSkills(ArrayList<String> skills){
        this.Skills = new ArrayList<String>();
        for (int i =0; i<skills.size(); i++){
            Skills.add("\"" + skills.get(i) + "\"");
        }
    }

    public void setEducation(ArrayList<String> school_name, ArrayList<String> year, ArrayList<String> description){
        this.education = new ArrayList<Education>();
        for (int i = 0; i<school_name.size(); i++){
            Education ed = new Education(school_name.get(i), year.get(i), description.get(i));
            education.add(ed);
        }
    }

    public void setExperience(ArrayList<String> companyName, ArrayList<String> year, ArrayList<String> description){
        this.experience = new ArrayList<Experience>();
        for (int i =0; i<companyName.size(); i++){
            Experience ex = new Experience(companyName.get(i), year.get(i), description.get(i));
            experience.add(ex);
        }
    }

    public void setAchievements(ArrayList<String> type, ArrayList<String> description){
        this.achievements = new ArrayList<Achievements>();
        for (int i = 0; i<type.size(); i++){
            Achievements a = new Achievements(type.get(i), description.get(i));
            achievements.add(a);
        }
    }

    public String write2json(){

        String json = "";

        json += "{";
        json += this.personal.tostring();
        json += "\"JobTitle\": " + "\""+ this.job_title + "\", ";
        json += "\"Summary\": " + "\"" + this.summary + "\", ";
        json += "\"Skills\": " + this.Skills + ", ";
        json += "\"Education\": [";
        for (int i =0; i<this.education.size(); i++){
            if (i==this.education.size()-1){
            	json += this.education.get(i).tostring() + "],";
            }else {
            	json += this.education.get(i).tostring() + ",";
            }

        }
        json += "\"Experience\": [";
        for (int i =0; i<this.experience.size(); i++) {
            if (i == this.experience.size() - 1) {
            	json += this.experience.get(i).tostring() + "],";
            } else {
            	json += this.experience.get(i).tostring() + ",";
            }

        }
        json += "\"Achievements\": [";
        for (int i =0; i<this.achievements.size(); i++){
            if (i==this.achievements.size()-1){
            	json += this.achievements.get(i).tostring() + "]}";
            }else {
            	json += this.achievements.get(i).tostring() + ",";
            }
        
        }return json;
        

    }

}