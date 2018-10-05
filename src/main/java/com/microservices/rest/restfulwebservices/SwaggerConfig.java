package com.microservices.rest.restfulwebservices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Configuration
//Enable Swagger
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final Contact DEFAULT_CONTACT = new Contact(
			"Wilmon", "", "monwil_99@yahoo.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			"Awesome API Title", 
			"Awesome Api Description", 
			"1.0", 
			"urn:tos",
	         DEFAULT_CONTACT, 
	         "Apache 2.0", 
	         "http://www.apache.org/licenses/LICENSE-2.0");
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
			new HashSet<String>(Arrays.asList("application/json","application/xml"));


	//Bean - Docket
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}
	//Swagger 2
	//All the paths
	//All the apis
	//http://localhost:8080/v2/api-docs
	//http://localhost:8080/swagger-ui.html
	
/*	Other Swagger info for User Add Annotation
 * 
 * @ApiModel(description="All details about the user")
	public class User {

		@Size(min=2, message="Name should have at least 2 characters")
		@ApiModelProperty(notes="Name should have at least 2 characters")
		private String name;
		
		@Past
		@ApiModelProperty(notes="Birthdate should be in the past")
		private Date birthdate;*/

}
