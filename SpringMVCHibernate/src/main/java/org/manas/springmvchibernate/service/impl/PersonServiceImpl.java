package org.manas.springmvchibernate.service.impl;


import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.manas.springmvchibernate.dao.PersonDAO;
import org.manas.springmvchibernate.model.Person;
import org.manas.springmvchibernate.service.PersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{

	@Resource(name="personDAO")
	private PersonDAO personDAO;
	 
 /*   public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
 */
    
	
	@Override
	@Transactional
	public void addPerson(Person p) {
		
		personDAO.addPerson(p);
	}


	@Override
	@Transactional
	public void updatePerson(Person p) {
		personDAO.updatePerson(p);
		
	}


	@Override
	@Transactional
	public List<Person> listPersons() {
		return personDAO.listPersons();
	}


	@Override
	@Transactional
	public Person getPersonById(int id) {
		
		System.out.println("in service getPerspnbyID "+id);
		Person p=personDAO.getPersonById(id);
		System.out.println("--->"+p.getName());
		return p;
	}


	@Override
	@Transactional
	public void removePerson(int id) {
		
	 personDAO.removePerson(id);
		
	}

	

}
