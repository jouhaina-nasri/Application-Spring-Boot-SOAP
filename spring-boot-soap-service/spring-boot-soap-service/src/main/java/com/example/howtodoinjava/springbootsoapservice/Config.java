package com.example.howtodoinjava.springbootsoapservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema; 

@EnableWs
@Configuration
public class Config extends WsConfigurerAdapter{
	
	@Bean 
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) 
	{ 
		MessageDispatcherServlet servlet = new MessageDispatcherServlet(); 
		servlet.setApplicationContext(applicationContext); 
		servlet.setTransformWsdlLocations(true); 
		return new ServletRegistrationBean(servlet, "/service/*"); 
	}

	@Bean(name = "studentDetailsWsdl") 
	public DefaultWsdl11Definition defaultWsd111Definition(XsdSchema countriesSchema) 
	{
		DefaultWsdl11Definition wsd111Definition = new DefaultWsdl11Definition(); 
		wsd111Definition.setPortTypeName("StudentDetailsPort"); 
		wsd111Definition.setLocationUri("/service/student-details"); 
		wsd111Definition.setTargetNamespace("http://www.exemple.org/student"); 
		wsd111Definition.setSchema(countriesSchema); 
		return wsd111Definition; 
		
	} 

	@Bean 
	public XsdSchema countriesSchema() 
	{
		return new SimpleXsdSchema(new ClassPathResource("school.xsd")); 
	} 

}
