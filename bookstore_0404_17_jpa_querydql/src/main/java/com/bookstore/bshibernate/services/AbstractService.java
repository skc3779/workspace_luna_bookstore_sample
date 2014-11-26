package com.bookstore.bshibernate.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;

public abstract class AbstractService {

		private EntityManager entityManager;
		
		protected JPAQuery from(EntityPath<?>... paths) {
			return new JPAQuery(entityManager).from(paths);
		}
		
	    /**
	     * @Autowired가 아니라,
	     * @PersistenceContext로 주입받은 EntityManager는 실제 EntityManager가 아니라
	     * 현재 진행 중인 트랜잭션에 연결되는 퍼시스턴스 컨텍스를 갖는 일종의 프록시이다.
	     * 참고) 토비2권 p288
	     *
	     * 추가정보
	     *
	     * JPA 스펙에 따르면 트랜잭션 스코프의 퍼시스턴스 컨텍스트는 JTA를 통해 트랜잭션을
	     * 관리하게 되어 있다. 하지만 스프링은 이를 스프링이 직접 관리하는 JpaTransactionManager를
	     * 통해 트랜잭션을 관리해주기 때문에 JavaEE 서버의 트랜잭션 매니저와 JTA가 없어도 JPA의 장점인
	     * @PersistenceContext를 통한 EntityManager 주입 방식을 그대로 적용할 수 있다.
	     */		
		@PersistenceContext(type = PersistenceContextType.TRANSACTION)
		public void setEntityManager(EntityManager entityManager) {
			this.entityManager = entityManager;
		}
}
