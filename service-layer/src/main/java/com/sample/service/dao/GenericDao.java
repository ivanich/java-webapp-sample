package com.sample.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.Mapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private Mapper mapper;

	/**
	 * This method is used to convert a single DTO <=> Entity
	 * @param origRecord
	 * @param mappedType
	 * @return
	 */
	protected final <S,T> T map(S origRecord, Class<T> mappedType){
		return getMapper().map(origRecord, mappedType);
	}

	/**
	 * This method is used to convert a DTO list <=> Entity list
	 * @param list
	 * @param mapppedType
	 * @return
	 */
	protected final <S,T> List<?> mapList(List<S> list, Class<T> mapppedType){

		List<T> mappedList = new ArrayList<T>();

		if(!CollectionUtils.isEmpty(list)){
			for (S record : list) {
				T mappedRecord = getMapper().map(record, mapppedType);
				mappedList.add(mappedRecord);
			}
		}

		return mappedList;
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
}
