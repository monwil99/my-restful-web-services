package com.microservices.rest.restfulwebservices.versioning;

public class PersonV2 {

	private Name Name;

	public PersonV2() {
		super();
	}

	
	public PersonV2(Name name) {
		super();
		Name = name;
	}


	public Name getName() {
		return Name;
	}

	public void setName(Name name) {
		Name = name;
	}
	
	
}
