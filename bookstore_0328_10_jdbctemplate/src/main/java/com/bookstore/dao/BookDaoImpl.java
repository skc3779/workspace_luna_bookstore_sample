package com.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookStatus;

@Repository("bookDao")
@Transactional
public class BookDaoImpl implements BookDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public RowMapper<Book> bookMapper =
			new RowMapper<Book>(){
		@Override
		public Book mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setName(rs.getString("name"));
			book.setAuthor(rs.getString("author"));
			java.util.Date date = new java.util.Date(rs.getTimestamp("publishdate").getTime());
			book.setPublishDate(date);
			book.setComment(rs.getString("comment"));
			book.setBookStatus(BookStatus.valueOf(rs.getInt("status")));
			book.setRentUserId((Integer) rs.getObject("rentUserId"));

			return book;
		}
	};

	public void add(final Book book) {
		java.util.Date sqlDate = new java.util.Date(book.getPublishDate().getTime());
		System.out.println("jdbcTemplate : add() sqlDate 생성부분 "+sqlDate);
		this.jdbcTemplate.update("insert books(id,name,author,publishdate,comment,status,rentUserId) " +
				"values(?,?,?,?,?,?,?)"
				,book.getId(),book.getName(),
				book.getAuthor(),sqlDate,book.getComment(),
				book.getBookStatus().intValue(),
				book.getRentUserId());
	}

	public void update(final Book book){
		java.util.Date sqlDate = new java.util.Date(book.getPublishDate().getTime());
		this.jdbcTemplate.update("update books set name=?, author=?, publishdate=?,comment=?, status=?, rentUserId=? where id=?"
				,book.getName(),book.getAuthor(),
				sqlDate,
				//new Timestamp(book.getPublishDate().getTime()),
				book.getComment(),book.getBookStatus().intValue(),
				book.getRentUserId(),
				book.getId());

		System.out.println("jdbcTemplate : update() Timestamp 생성부분"+sqlDate);
	}

	public Book get(final int id)	{
		return this.jdbcTemplate.queryForObject("select * from books where id=?",new Object[] {id}, this.bookMapper);
	}

	public List<Book> search(final String name){
		String query = "select * from books where name like '%" + name + "%'";
		return this.jdbcTemplate.query(query, this.bookMapper);
	}

	public List<Book> getAll(){
		String query = "select * from books order by status asc";
		return this.jdbcTemplate.query(query, this.bookMapper);
	}

	public int countAll(){
		return this.jdbcTemplate.queryForInt("select count(*) from books");
	}

	public void deletAll(){
		this.jdbcTemplate.update("delete from books");
	}
}
