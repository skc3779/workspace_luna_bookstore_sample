package com.bookstore.bshibernate.entities;

import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="histories")
public class History {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")	
	private @Getter @Setter Integer id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId", nullable=false)
	private @Getter @Setter User user;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bookId", nullable=false)
	private @Getter @Setter Book book;
	@Enumerated(EnumType.ORDINAL)
	@Column(name="actionType")
	private @Getter @Setter HistoryActionType actionType;
	@Temporal(TemporalType.DATE)	
	@Column(name="insertDate")
	private @Getter @Setter Date insertDate;
}
