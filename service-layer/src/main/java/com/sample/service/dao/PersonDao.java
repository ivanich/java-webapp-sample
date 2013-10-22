package com.sample.service.dao;

import java.util.List;

import com.sample.common.dto.PersonDto;

public interface PersonDao {

	public List<PersonDto> getAll();
	public PersonDto get(String id);
	public void save(PersonDto person);
}
