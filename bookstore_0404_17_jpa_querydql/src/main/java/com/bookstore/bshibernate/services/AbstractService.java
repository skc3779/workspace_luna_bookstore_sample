package com.bookstore.bshibernate.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;

public abstract class AbstractService {

		private EntityManager entityManager;
		
		protected JPAQuery from(EntityPath<?>... paths) {
			return new JPAQuery(entityManager).from(paths);
		}
		
		@PersistenceContext
		public void setEntityManager(EntityManager entityManager) {
			this.entityManager = entityManager;
		}
}
