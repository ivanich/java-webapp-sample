/**
 * 
 */
package com.sample.service.converter;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import com.sample.common.dto.PersonDto;
import com.sample.service.entity.Person;

public class PersonDtoConverter implements CustomConverter{

	public Object convert(Object destination, Object source, 
			Class<?> destClass, Class<?> sourceClass) {
		
		if (source == null) {
			return null;
		}
		
		if (source instanceof Person) {
			
			PersonDto dest = new PersonDto();
			Person src = (Person)source;

			dest.setId(src.getId());
			dest.setFirstName(src.getFirstName());
			dest.setMiddleName(src.getMiddleName());
			dest.setLastName(src.getLastName());
			
			return dest;

		} else if (source instanceof PersonDto) {
			
			Person dest = new Person();
			PersonDto src = (PersonDto)source;
			
			dest.setId(src.getId());
			dest.setFirstName(src.getFirstName());
			dest.setMiddleName(src.getMiddleName());
			dest.setLastName(src.getLastName());
			
			return dest;
			
		} else {
			throw new MappingException("Converter PersonDtoConverter "
					+ "used incorrectly. Arguments passed in were:"
					+ destination + " and " + source);
		}
	} 
}
