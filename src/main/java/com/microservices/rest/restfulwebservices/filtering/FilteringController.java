package com.microservices.rest.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBeans() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value12", "value22", "value32"));
	}
	
	@GetMapping("/filtering-other")
	public List<OtherBean> retrieveListOfOtherBeans() {
		return Arrays.asList(new OtherBean("value1", "value2", "value3"),
				new OtherBean("value12", "value22", "value32"));
	}
	
	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue retrieveDynamicCleanBean() {
		
		CleanBean cleanBean = new CleanBean("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("CleanBeanFilter", filter);
				
		MappingJacksonValue mapping = new MappingJacksonValue(cleanBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/filtering-dynamic-list")
	public MappingJacksonValue retrieveDynamicCleanBeanList() {
		List<CleanBean> list = Arrays.asList(new CleanBean("value1", "value2", "value3"),
				new CleanBean("value12", "value22", "value32"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("CleanBeanFilter", filter);
				
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	
	
}
