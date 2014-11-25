package com.bookstore.bshibernate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.bshibernate.entities.BookAudit;

public interface BookAuditableRepository extends CrudRepository<BookAudit, Integer> {	
	List<BookAudit>	findByName(String name);
}
