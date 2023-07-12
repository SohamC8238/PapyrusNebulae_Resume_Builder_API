# PapyrusNebulae_Resume_Builder_API
This repository contains the code for Adobe's Papyrus Nebuale Hackathon Round2. The goal of this project was to make a Resume Builder API using Adobe's Document Generation API.

## Pre Requisites for running the code:

  1. pdfservices-sdk credentials
  2. maven
  3. java 17
     
Run these commands for Ubuntu:

```
sudo apt install openjdk-17-jdk
sudo apt install maven -y
```
Get Adobe PDF Services SDK credentials from Adobe PDF Services. Replace the pdfservices-api-credentials.json in this repository with your credentials in the same directory where pom.xml file is located.

## Code Structure:

There are three code folders:

### Errors
This folder contains two files that handle the 4 types of errors that the API handles.
 Code   Description 
 400    Bad Request 
 401    Unauthorised
 404    Template not found 
 500    Internal Server Error

### dataClasses
This folder contains classes for the various objects for resumes like:
Eduction
Achievements
Experience
Personal
It also contains a class called JsonData that contains all the information.

### resume_builder
This folder is for Springboot functionalities. It has a ResumeBuilderApplication Class that runs the SPringBoot Application. It also has two other folders that contain the apicontroller and service layer.

## How to run the code.
You can run this through command line:
After satisfying the pre requisites Run the following command on the terminal in the same directory where the pom.xml is present:

```
./mvnw clean
./mvnw install
./mvnw spring-boot:run
```

You can also use Spring Tool Suites. Import the projet into STS and then run it as a SPringBoot App.

Either way, once the connection is extablished. You can run a curl request or postman.
