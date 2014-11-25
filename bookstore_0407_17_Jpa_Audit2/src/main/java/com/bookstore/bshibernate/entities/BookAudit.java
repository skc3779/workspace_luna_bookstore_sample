package com.bookstore.bshibernate.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;

@Entity
@Audited
@Table(name="books_audit")
public class BookAudit implements Auditable<String, Integer>, Serializable {

	private static final long serialVersionUID = -6335105660067007007L;

	private  Integer id;

	private  String name;

	private  String author;	

	private  Date publishDate;

	private  String comment;

	private  BookStatus bookStatus;		

	private  Integer rentUser;
	
	// Audit fields
	private String createdBy;
	private DateTime  createdDate;	
	private String lastModifiedBy;
	private DateTime  lastModifiedDate;	
	
/*	private  Set<History> histories = new HashSet<History>();	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
		@Temporal(TemporalType.DATE)
		@Column(name="publishDate")	
		public Date getPublishDate() {
			return publishDate;
		}
		public void setPublishDate(Date publishDate) {
			this.publishDate = publishDate;
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
		@Column(name="rentuserid", nullable=false)	
		public Integer getRentUser() {
			return rentUser;
		}
		public void setRentUser(Integer rentUser) {
			this.rentUser = rentUser;
		}
	
	@Column(name="created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name="created_date")
	//Joda 사용시 밑의 어노테이션을 활용하고
	//@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDateTime")	
	//Jadira 사용시 밑의 어노테이션을 활용하면 됨 그리고 객체는 DateTime 사용 (?)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime  getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(DateTime  createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name="last_modified_by")
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	@Column(name="last_modified_date")
	//Joda 사용시 밑의 어노테이션을 활용하고
	//@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDateTime")	
	//Jadira 사용시 밑의 어노테이션을 활용하면 됨 그리고 객체는 DateTime 사용
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime  getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(DateTime  lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
/*	
	@OneToMany(mappedBy="book", cascade=CascadeType.ALL, orphanRemoval=true)	
	public Set<History> getHistories() {
		return histories;
	}
	public void setHistories(Set<History> histories) {
		this.histories = histories;
	}*/
	
	@Transient
	public boolean isNew() {
		if (id == null) {
			return true;
		} else {
			return false;
		}
	}
	
}
