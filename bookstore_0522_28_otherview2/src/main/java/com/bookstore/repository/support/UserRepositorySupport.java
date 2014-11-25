package com.bookstore.repository.support;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository("userRepositorySupport")
public class UserRepositorySupport extends QueryDslRepositorySupport  {
	
	@SuppressWarnings("unused")
	private EntityManager entityManager;
	
	public UserRepositorySupport(Class<?> domainClass) {
		super(domainClass);
	}

	@Override
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
