package com.sample.presentation.services;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.sample.common.dto.PersonDto;

/**
 * A sample user service to power the EXTJS application
 */
@Controller
@RequestMapping(PersonDto.RESOURCE_NAME) // a version # for this services
public class PersonService
{
	@Autowired
	private String restRootUrl;
	
	RestTemplate rt;
	
    public PersonService(){
    	rt = new RestTemplate();
        rt.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        rt.getMessageConverters().add(new StringHttpMessageConverter());
    }
    
	@RequestMapping(
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON)
	@ResponseBody
	@SuppressWarnings("unchecked")
    public List<PersonDto> getAll() {
    	
		return rt.getForObject(getRestRootUrl() + PersonDto.RESOURCE_NAME, List.class);
    }
	
	@RequestMapping(
			value="{id}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON)
	@ResponseBody
    public PersonDto get(@PathVariable String id) {
    	
		return rt.getForObject(getRestRootUrl() + PersonDto.RESOURCE_NAME + "/" + id,
				PersonDto.class, id);
    }
    
    @RequestMapping(
    		method=RequestMethod.POST,
    		consumes=MediaType.APPLICATION_JSON)
    @ResponseBody
    public void save(@RequestBody PersonDto person){
    	
    	rt.postForObject(getRestRootUrl() + PersonDto.RESOURCE_NAME,
    			person, PersonDto.class);
    }
    
    @RequestMapping(
    		value="{id}",
    		method=RequestMethod.PUT,
    		consumes=MediaType.APPLICATION_JSON)
    @ResponseBody
    public void update(@RequestBody PersonDto person, @PathVariable String id){
    	
    	rt.put(getRestRootUrl() + PersonDto.RESOURCE_NAME + "/" + id,
    			person, PersonDto.class);
    }
    
    @RequestMapping(
    		value="{id}",
    		method=RequestMethod.DELETE,
    		consumes=MediaType.APPLICATION_JSON)
    @ResponseBody
    public void delete(@PathVariable String id){
    	
    	rt.delete(getRestRootUrl() + PersonDto.RESOURCE_NAME + "/" + id);
    }
    
	/**
	 * @return the restRootUrl
	 */
	public String getRestRootUrl() {
		return restRootUrl;
	}

	/**
	 * @param restRootUrl the restRootUrl to set
	 */
	public void setRestRootUrl(String restRootUrl) {
		this.restRootUrl = restRootUrl;
	}
}
