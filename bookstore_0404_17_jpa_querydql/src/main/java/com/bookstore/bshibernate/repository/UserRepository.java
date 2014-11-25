package com.bookstore.bshibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.bookstore.bshibernate.entities.History;
import com.bookstore.bshibernate.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>, QueryDslPredicateExecutor<User>  {
	List<User>	findByName(String name);
}
