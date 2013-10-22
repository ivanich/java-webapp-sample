package com.sample.service.dao;

import java.util.List;

import org.dozer.Mapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private Mapper mapper;

	protected <T> List<T> mapList(List<?> list){
//		
//		List<?> mappedList = new ArrayList<T>();
//		
//		if(!CollectionUtils.isEmpty(list)){
//			for (Object record : list) {
//				T mappedRecord = (T) getMapper().map(record,);
//				mappedList.add(mappedRecord);
//			}
//		}
//		
//		return mappedList;
		return null;
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
