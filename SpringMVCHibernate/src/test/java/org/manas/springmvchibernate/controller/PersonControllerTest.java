package org.manas.springmvchibernate.controller;

import java.util.ArrayList;
import java.util.List;

import org.manas.springmvchibernate.model.Person;
import org.manas.springmvchibernate.service.PersonService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;

public class PersonControllerTest {
	
	@Mock
	private PersonService personService;
	
	@InjectMocks
	PersonController personController;
	
	@Spy
	ModelMap model;
	
	@Spy
	List<Person> persons=new ArrayList<Person>();
	
	@BeforeClass
	public void setup(){
		MockitoAnnotations.initMocks(this);
		persons=getPersonList();
	}

	private List<Person> getPersonList() {
		
		Person p1=new Person();
		p1.setId(1);
		p1.setName("Aaron");
		p1.setCountry("USA");
		
		Person p2=new Person();
		p2.setId(2);
		p2.setName("Tiru");
		p2.setCountry("India");
		
		persons.add(p1);
		persons.add(p2);
		
		return persons;
		
		
		}
	
	@Test
	public void testListPerson(){
		
		when(personService.listPersons()).thenReturn(persons);
		Assert.assertEquals(personController.listPersons(model), "person");
		//Assert.assertEquals(model.get("persons"), persons);
		
		verify(personService, atLeastOnce()).listPersons();
		//verify(personService,times(2)).listPersons();
		
	}
	
	@Test
	public void testAddPerson(){
	
		
		//verify(personService, atLeastOnce()).addPerson(persons.get(0));
		
	//	verify(personService, atLeastOnce()).updatePerson(persons.get(0));
		
		
	}
	

}
