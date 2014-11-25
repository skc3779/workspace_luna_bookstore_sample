package com.bookstore.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

import com.bookstore.mybatis.entities.Book;
import com.bookstore.mybatis.entities.BookStatus;

public interface BookMapperAnnotation {
	
	@Select(value="select a.id, a.name, a.author, a.publishDate, " +
			"a.comment, a.status, a.rentUserId from bookstore.books a")
	@Results(value={
			@Result(javaType=Book.class),
			@Result(property="id", column="id"),
			@Result(property="name", column="name"),
			@Result(property="author", column="author"),
			@Result(property="publishDate",column="publishDate"),
			@Result(property="comment", column="comment"),
			//@Result(property="bookStatus", column="status", typeHanlder=EnumOrdinalTypeHandler<>(MyEnum.class)),
			@Result(property="rentUserId", column="rentUserId")
	})
	
	List<Book> findAll();

}
