package com.sample.service.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.common.dto.PersonDto;
import com.sample.service.entity.Person;

@Repository
@Transactional
public class PersonDaoImpl extends GenericDao implements PersonDao {

	
	@SuppressWarnings("unchecked")
	public List<PersonDto> getAll(){
		
		Criteria criteria = getSessionFactory().getCurrentSession().
				createCriteria(Person.class);
	    return mapList(criteria.list(), PersonDto.class);
	}

	@Override
	public PersonDto get(String id) {
		return getMapper().map(
				(Person) getSessionFactory().getCurrentSession().get(Person.class, id),
				PersonDto.class);
	}
	
	public void save(PersonDto person){
		getSessionFactory().getCurrentSession().save(
				getMapper().map(person, Person.class));
	}
}

