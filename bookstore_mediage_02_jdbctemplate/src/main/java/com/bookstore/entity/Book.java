package com.bookstore.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class Book {
	private @Getter @Setter int id;
	private @Getter @Setter String name;
	private @Getter @Setter String author;
	private @Getter @Setter Date publishDate;
	private @Getter @Setter String comment;
	private @Getter @Setter BookStatus bookStatus;
	private @Getter @Setter Integer rentUserId;
}
