package com.bookstore.mybatis.dao.xml;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bookstore.mybatis.entities.Book;
import com.bookstore.mybatis.utils.XmlSessionFactoryUtil;

public class BookDaoImpl implements BookDao {
	
	@Override
	public Book findById(Integer id) {
		SqlSession session = XmlSessionFactoryUtil.getSqlSessionFactory().openSession();
		Book book = null;
		try {
			book = (Book)session.selectOne("findById", id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return book;
	}

}
