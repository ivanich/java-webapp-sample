
package com.sample.service.rest;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.sample.common.dto.PersonDto;
import com.sample.service.dao.PersonDao;

/** Example resource class hosted at the URI path "/person"
 */
@Controller
@RequestMapping("person")
public class PersonResource extends RestTemplate {
    
	@Autowired
	private PersonDao personDao;
	
	public PersonResource() {
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON)
	@ResponseBody
    public List<PersonDto> getAll() {
    	
        return getPersonDao().getAll();
    }
	
	@RequestMapping(
			value="{id}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON)
	@ResponseBody
    public PersonDto getPerson(@PathVariable String id) {
    	
		return getPersonDao().get(id);
    }
    
    @RequestMapping(
    		method=RequestMethod.PUT,
    		consumes=MediaType.APPLICATION_JSON)
    @ResponseBody
    public void save(PersonDto person){
    	
    	getPersonDao().save(person);
    }

    
    /**
     * create a person with a random ID
     * @return
     */
    @RequestMapping(
    		value="random",
    		method=RequestMethod.GET,
    		produces=MediaType.APPLICATION_JSON)
    @ResponseBody
    public String test(){
    	
    	String random = UUID.randomUUID().toString();
    	getPersonDao().save(
    			new PersonDto(random));
    	
    	return String.format("Created a person with the folowing ID: %s", random);
    }

	/**
	 * @return the personDao
	 */
	public PersonDao getPersonDao() {
		return personDao;
	}

	/**
	 * @param personDao the personDao to set
	 */
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
    
}
