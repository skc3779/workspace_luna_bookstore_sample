package com.bookstore.bshibernate.entities;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")		
	private @Getter @Setter Integer id;
	@Column(name="name")		
	private @Getter @Setter String name;
	@Column(name="password")		
	private @Getter @Setter String password;
	@Column(name="point")
	private @Getter @Setter Integer point;
	@Enumerated(EnumType.ORDINAL)
	@Column(name="level")
	private @Getter @Setter UserLevel level;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
	private @Getter @Setter Set<History> histories = new HashSet<History>(); 
}
