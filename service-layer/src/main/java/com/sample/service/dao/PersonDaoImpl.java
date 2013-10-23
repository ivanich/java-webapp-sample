package com.sample.service.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.common.dto.PersonDto;
import com.sample.service.entity.Person;

@Repository
@Transactional
public class PersonDaoImpl extends GenericDao implements PersonDao {

	@SuppressWarnings("unchecked")
	public List<PersonDto> getAll() {

		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(Person.class);
		return mapList(criteria.list(), PersonDto.class);
	}

	@Override
	public PersonDto get(String id) {
		return getMapper().map(
				(Person) getSessionFactory().getCurrentSession().get(
						Person.class, id), PersonDto.class);
	}

	@Override
	public void save(PersonDto person) {
		person.setId(UUID.randomUUID().toString());
		getSessionFactory().getCurrentSession().save(
				getMapper().map(person, Person.class));
	}

	@Override
	public void update(PersonDto person) {
		getSessionFactory().getCurrentSession().update(
				getMapper().map(person, Person.class));

	}

	@Override
	public void delete(String id) {
		getSessionFactory().getCurrentSession().delete(new Person(id));
	}
}
