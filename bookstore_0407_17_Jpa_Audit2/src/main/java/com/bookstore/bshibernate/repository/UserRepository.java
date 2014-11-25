package com.bookstore.bshibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bshibernate.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User>	findByName(String name);
}
