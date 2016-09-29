package com.bookstore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.entity.Book;
import com.bookstore.utils.ConnectionFactory;

public class BookApp {
	
    interface ExecuteUpdateQuery {
        PreparedStatement getPreparedStatement(Connection conn) throws SQLException;
    }

    interface ExecuteSelectQuery {
        PreparedStatement getPreparedStatement(Connection conn) throws SQLException;
        Object parseResultSet(ResultSet rs) throws SQLException;
    }

    private ConnectionFactory connectionFactory;

    public void setConnectionFactory(ConnectionFactory connectionFactory)
    {
    	this.connectionFactory = connectionFactory;
    }
    
    private void closeObject(AutoCloseable... closeables) throws Exception {
        for(AutoCloseable c : closeables) {
            try {
                c.close();
            } catch(Exception ex) {

            }
        }
    }

    private Book convertBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        java.util.Date date = new java.util.Date(rs.getDate("publishDate").getTime());
        book.setPublishDate(date);
        book.setComment(rs.getString("comment"));

        return book;
    }

    private Object execute(ExecuteSelectQuery query) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = query.getPreparedStatement(conn);
        ResultSet rs = ps.executeQuery();
        Object result = query.parseResultSet(rs);
        closeObject(conn, ps, rs);
        return result;
    }

    private void execute(ExecuteUpdateQuery query) throws Exception {
        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = query.getPreparedStatement(conn);
        ps.executeUpdate();
        closeObject(conn, ps);
    }

    public void add(final Book book) throws Exception {
        ExecuteUpdateQuery query = new ExecuteUpdateQuery() {
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement st = conn.prepareStatement("insert books(name, author, publishDate, comment, id) values(?, ?, ?, ?, ?)");
                st.setString(1, book.getName());
                st.setString(2, book.getAuthor());
                java.sql.Date sqlDate = new java.sql.Date(book.getPublishDate().getTime());
                st.setDate(3, sqlDate);
                st.setString(4, book.getComment());
                st.setInt(5, book.getId());
                return st;
            }
        };
        execute(query);
    }

    public Book get(final int id) throws Exception {
        ExecuteSelectQuery query = new ExecuteSelectQuery() {
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement st = conn.prepareStatement("select id, name, author, publishDate, comment from books where id=?");
                st.setInt(1, id);
                return st;
            }            
            public Object parseResultSet(ResultSet rs) throws SQLException {
                rs.next();
                return convertBook(rs);
            }
        };
        return (Book) execute(query);
    }

    public List<Book> search(final String name) throws Exception {
        ExecuteSelectQuery query = new ExecuteSelectQuery() {
            
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement("select id, name, author, publishDate, comment from books where name like ?");
                ps.setString(1, "%" + name + "%");
                return ps;
            }

            
            public Object parseResultSet(ResultSet rs) throws SQLException {
                List<Book> books = new ArrayList<Book>();
                while(rs.next()) {
                    Book book = convertBook(rs);
                    books.add(book);
                }
                return books;
            }
        };
        return (List<Book>) execute(query);
    }

    public Integer countAll() throws Exception {
        ExecuteSelectQuery query = new ExecuteSelectQuery() {
            
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                return conn.prepareStatement("select count(*) from books");
            }
            
            public Object parseResultSet(ResultSet rs) throws SQLException {
                rs.next();
                return rs.getInt(1);
            }
        };
        return (Integer)execute(query);
    }

    public Book update(final Book book) throws Exception {
        ExecuteUpdateQuery query = new ExecuteUpdateQuery() {

            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement("update books set name=?, author=?, publishDate=?, comment=? where id=?");
                ps.setString(1, book.getName());
                ps.setString(2, book.getAuthor());
                ps.setDate(3, new java.sql.Date(book.getPublishDate().getTime()));
                ps.setString(4, book.getComment());
                ps.setInt(5, book.getId());
                return ps;
            }
        };
        execute(query);
        return book;
    }

    public List<Book> getAll() throws Exception {
        ExecuteSelectQuery query = new ExecuteSelectQuery() {

            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                return conn.prepareStatement("select id, name, author, publishDate, comment from books");
            }


            public Object parseResultSet(ResultSet rs) throws SQLException {
                List<Book> books = new ArrayList<Book>();
                while(rs.next()) {
                    Book book = convertBook(rs);
                    books.add(book);
                }
                return books;
            }
        };
        return (List<Book>) execute(query);
    }

    public void deleteAll() throws Exception {
        ExecuteUpdateQuery query = new ExecuteUpdateQuery() {            
            public PreparedStatement getPreparedStatement(Connection conn) throws SQLException {
                return conn.prepareStatement("delete from books");
            }
        };
        execute(query);
    }
}
