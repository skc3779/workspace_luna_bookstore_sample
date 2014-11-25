package com.bookstore.repository.support;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.bookstore.entities.QHistory;
import com.bookstore.entities.History;

@Repository("historyRepositorySupport")
public class HistoryRepositorySupport extends QueryDslRepositorySupport  {

	@SuppressWarnings("unused")
	private EntityManager entityManager;
	
	public HistoryRepositorySupport(Class<?> domainClass) {
		super(domainClass);
	}

	@Override
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<History> findAll() {
		QHistory history = QHistory.history;
		return from(history).list(history);
	}

}
