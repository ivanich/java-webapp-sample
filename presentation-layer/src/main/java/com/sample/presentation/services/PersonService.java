package com.sample.presentation.services;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * A sample user service to power the EXTJS application
 */
@Controller
@RequestMapping(PersonService.RESOURCE_NAME) // a version # for this services
public class PersonService
{
	public static final String RESOURCE_NAME = "person";

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
	public Object getAll() {
    	return rt.getForObject(getRestRootUrl() + RESOURCE_NAME, Object.class);
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
