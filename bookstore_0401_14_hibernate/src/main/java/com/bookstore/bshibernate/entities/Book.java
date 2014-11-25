package com.bookstore.bshibernate.entities;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private @Getter @Setter int id;
	@Column(name="name")
	private @Getter @Setter String name;
	@Column(name="author")	
	private @Getter @Setter String author;	
	@Temporal(TemporalType.DATE)
	@Column(name="publishDate")
	private @Getter @Setter Date publishDate;
	@Column(name="comment")
	private @Getter @Setter String comment;
	@Enumerated(EnumType.ORDINAL)
	@Column(name="status")
	private @Getter @Setter BookStatus bookStatus;
	@Column(name="rentUserId")
	private @Getter @Setter Integer rentUserId;
	@OneToMany(mappedBy="book", cascade=CascadeType.ALL, orphanRemoval=true)	
	private @Getter @Setter Set<History> histories = new HashSet<History>();
}
