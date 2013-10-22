package com.sample.presentation.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.sample.presentation.model.Person;

/**
 * A sample user service to power the EXTJS application
 */
@Controller
@RequestMapping(PersonService.RESOURCE_NAME) // a version # for this services
public class PersonService
{

	public static final String RESOURCE_NAME = "person";
	public final String JSON_ROOT = "root";

	@Autowired
	private String restRootUrl;
	
    // todo: demo only, don't use this Collection
    // For the demo we'll hard code a few users. However, for a real application this will
    // come from a db, most likely.
    private static Map<String, Person> users = new HashMap<String, Person>();
    static
    {
        Person u1 = new Person();
        u1.setId(UUID.randomUUID().toString());
        u1.setName("Ed");
        u1.setEmail("ed@sencha.com");
        users.put(u1.getId(), u1);

        Person u2 = new Person();
        u2.setId(UUID.randomUUID().toString());
        u2.setName("Tommy");
        u2.setEmail("tommy@sencha.com");
        users.put(u2.getId(), u2);
    }

    /**
     * 
     */
    public PersonService(){
    	RestTemplate rt = new RestTemplate();
        rt.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        rt.getMessageConverters().add(new StringHttpMessageConverter());
    }
    
    @RequestMapping(
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON)
	@ResponseBody
    public String getAll() {
    	
        return "hello";
    }
    
    
    /**
     * Authenticate a user
     *
     * @return the authenticated user
     */
    @RequestMapping("authenticate")
    public Person authenticateUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(value = "salt", required = false) String salt )
    {
        //TODO replace this with your real authentication code here.

        // return a new user object for the "authenticated" user
        Person user = new Person();
        user.setId("1");
        user.setName(email.split("@")[0]);
        user.setEmail(email);
        return user;
    }

    /**
     * return a list of users
     */
    @RequestMapping(
    		value="list",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON)
	@ResponseBody
    public Map<String, Person> listUsers( )
    {
    	return users;
    }

    /**
     * Update a user in the system
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map updateUser( 
            @RequestBody String json ) throws Exception
    {
        //TODO replace this with your real code here.
        Collection<Person> parsedUsers = parseUserJson(json);

        // Update all of the users (client is sending us array of users in json)
        if ( parsedUsers != null )
        {
            for (Person parsedUser : parsedUsers)
            {
                Person localUser = users.get(parsedUser.getId());
                if ( localUser == null )
                {
                    throw new RuntimeException("Invalid User");
                }

                // save changes to local user
                localUser.setName(parsedUser.getName());
                localUser.setEmail(parsedUser.getEmail());
            }
        }


        Map results = new HashMap();
        results.put("succes", true);
        return results;
    }


    /**
     * Parse an json packet of user(s)
     */
    private Collection<Person> parseUserJson( String json ) throws Exception
    {
        try
        {
            if ( json.startsWith("[") && json.endsWith("]") )
            {
                // array of users
                ObjectMapper mapper = new ObjectMapper();
                TypeReference ref = new TypeReference<Collection<Person>>(){};
                Collection<Person> user = (Collection<Person>) mapper.readValue(json, ref);
                return user;
            }
            else
            {
                // Single object
                ObjectMapper mapper = new ObjectMapper();
                Collection<Person> users = new ArrayList<Person>();
                users.add( (Person) mapper.readValue(json, Person.class) );
                return users;
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException("Invalid USER Json");
        }
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
