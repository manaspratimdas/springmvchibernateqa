package org.manas.springmvchibernate.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.manas.springmvchibernate.dao.PersonDAO;
import org.manas.springmvchibernate.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOImpl implements PersonDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);
	
	
	//@Resource(name="sessionFactory") 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

	@Override
	public void addPerson(Person p) {
	
		Session session = this.sessionFactory.getCurrentSession();
	        session.persist(p);
	        logger.info("Person saved successfully, Person Details="+p);

	}

	@Override
	public void updatePerson(Person p) {
		 Session session = this.sessionFactory.getCurrentSession();
	        session.update(p);
	        logger.info("Person updated successfully, Person Details="+p);
		
	}

	@Override
	public List<Person> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
        List<Person> personsList = session.createQuery("from Person").list();
        for(Person p : personsList){
            logger.info("Person List::"+p);
        }
        return personsList;
	
	}

	@Override
	public Person getPersonById(int id) {
		
		System.out.println("in dao "+id);
	
		Session session=this.sessionFactory.getCurrentSession();
		Person p=(Person) session.load(Person.class, new Integer(id));
		System.out.println("-->"+p.getName());
		return p;
	}

	@Override
	
	public void removePerson(int id) {
		
		System.out.println("in remove DAO class  "+id);
		Session session=this.sessionFactory.getCurrentSession();
		Person p=(Person) session.load(Person.class, new Integer(id));
		if(p!=null){
			session.delete(p);
		}
		
	}

}
