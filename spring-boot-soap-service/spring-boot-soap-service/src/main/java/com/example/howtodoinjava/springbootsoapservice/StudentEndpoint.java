package com.example.howtodoinjava.springbootsoapservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.howtodoinjava.xml.school.StudentDetailsRequest;
import com.howtodoinjava.xml.school.StudentDetailsResponse;





@Endpoint
public class StudentEndpoint
{ 
	private static final String NAMESPACE_URI = "http://wow.exemple.org/student";
	private StudentRepository StudentRepository; 
	
	@Autowired 
	public StudentEndpoint(StudentRepository StudentRepository) 
	{ 
		this. StudentRepository = StudentRepository; 
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "StudertDelailsRequest") 
	@ResponsePayload 
	public StudentDetailsResponse getStudent(@RequestPayload StudentDetailsRequest request) 
	{
		StudentDetailsResponse response = new StudentDetailsResponse(); 
		response.setStudent(StudentRepository.findStudent(request.getName())); 
		return response; 
	} 
} 
