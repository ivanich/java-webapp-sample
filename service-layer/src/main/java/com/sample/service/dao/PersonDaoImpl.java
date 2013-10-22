package com.sample.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.common.dto.PersonDto;
import com.sample.service.entity.Person;

@Repository
@Transactional
public class PersonDaoImpl extends GenericDao implements PersonDao {

	
	private Class<T> mapType;
	
	@SuppressWarnings("unchecked")
	public List<PersonDto> getAll(){
		
		Criteria criteria = getSessionFactory().getCurrentSession().
				createCriteria(Person.class);
	    return getMapper().map(criteria.list(), ArrayList<PersonDto>.);
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

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return the mapper
	 */
	public Mapper getMapper() {
		return mapper;
	}

	/**
	 * @param mapper the mapper to set
	 */
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}
}

