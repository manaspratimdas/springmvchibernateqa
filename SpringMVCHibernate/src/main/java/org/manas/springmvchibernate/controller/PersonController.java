package org.manas.springmvchibernate.controller;

import javax.annotation.Resource;

import org.manas.springmvchibernate.model.Person;
import org.manas.springmvchibernate.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller

public class PersonController {
	
//	private PersonService personService;
	
	@Resource(name="personService")
	private PersonService personService;
    
  /*  @Autowired(required=true)
    @Qualifier(value="personService")
    public void setPersonService(PersonService ps){
        this.personService = ps;
    }*/
     
   @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(ModelMap model) {
        model.addAttribute("person", new Person());
        model.addAttribute("listPersons", this.personService.listPersons());
       System.out.println("you are here in controller in listPersons ");
        return "person";
    }
   
   
 //For add and update person both
   @RequestMapping(value= "/person/add", method = RequestMethod.POST)
   public String addPerson(@ModelAttribute("person") Person p){
        
       if(p.getId() == 0){
           //new person, add it
           this.personService.addPerson(p);
       }else{
           //existing person, call update
           this.personService.updatePerson(p);
       }
        
       return "redirect:/persons";
        
   }
   
   @RequestMapping(value="/remove/{id}", method=RequestMethod.GET)
   public String deletePerson(@PathVariable("id") int id){
	   
	   this.personService.removePerson(id);
	      
	   return "redirect:/persons";
   }
   
   @RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
   public String editPerson(@PathVariable("id") int id,Model model){
	   System.out.println("in editperon "+id);
	   model.addAttribute("person",this.personService.getPersonById(id));
	   model.addAttribute("listPerson",this.personService.listPersons());
	   
	   
	   return "person";
   }

}
