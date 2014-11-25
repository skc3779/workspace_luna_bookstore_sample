package com.bookstore.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="books")
public class Book {

	private  int id;

	private  String name;

	private  String author;	
	
	private  DateTime publishDate;

	private  String comment;

	private  BookStatus bookStatus;		

	private  User rentUser;
	
	private  Set<History> histories = new HashSet<History>();	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="name")	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="author")		
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column(name="publishDate")
	//Joda 사용시 밑의 어노테이션을 활용하고
	//@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDateTime")	
	//Jadira 사용시 밑의 어노테이션을 활용하면 됨 그리고 객체는 DateTime 사용 (?)
	//@Temporal(TemporalType.DATE)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")	
	@DateTimeFormat(iso=ISO.DATE) //, pattern="yyyy-MM-dd"
	public DateTime getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(DateTime publishDate) {
		this.publishDate = publishDate;
	}
	
	@Transient
	public String getPublishDateString() {
		return org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(publishDate);
	}	
	
	@Column(name="comment")	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="status")
	public BookStatus getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(BookStatus bookStatus) {
		this.bookStatus = bookStatus;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rentUserid", nullable=false)
	@JsonIgnore
	public User getRentUser() {
		return rentUser;
	}
	public void setRentUser(User rentUser) {
		this.rentUser = rentUser;
	}
	
	@OneToMany(mappedBy="book", cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonIgnore
	public Set<History> getHistories() {
		return histories;
	}
	public void setHistories(Set<History> histories) {
		this.histories = histories;
	}	
}
