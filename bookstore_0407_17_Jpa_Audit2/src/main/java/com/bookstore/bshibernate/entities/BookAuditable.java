package com.bookstore.bshibernate.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractAuditable;

public class BookAuditable extends AbstractAuditable<BookAuditable, Integer> {

	private static final long serialVersionUID = 2174995587872874362L;

	private String name;

	private String author;

	private Date publishDate;

	private String comment;

	private BookStatus bookStatus;

	private Integer rentUser;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "author")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "publishDate")
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(name = "comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	public BookStatus getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(BookStatus bookStatus) {
		this.bookStatus = bookStatus;
	}

	@Column(name = "rentuserid", nullable = false)
	public Integer getRentUser() {
		return rentUser;
	}

	public void setRentUser(Integer rentUser) {
		this.rentUser = rentUser;
	}
}
