package com.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.User;
import com.bookstore.entity.UserLevel;

@Repository("userDao")
public class UserDaoImpl implements UserDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public RowMapper<User> userMapper =
			new RowMapper<User>(){
		@Override
		public User mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setPoint(rs.getInt("point"));
			user.setLevel(UserLevel.valueOf(rs.getInt("level")));

			return user;
		}
	};

	private User convertToUser(Map<String,Object> rs){
		User user = new User();
		user.setId((Integer)rs.get("id"));
		user.setName((String)rs.get("name"));
		user.setPassword((String)rs.get("password"));
		user.setPoint((Integer)rs.get("point"));
		user.setLevel(UserLevel.valueOf((int)rs.get("level")));

		return user;
	}

	public void add(final User user){
		this.jdbcTemplate.update("insert users(id,name,password,point,level) " +
				"values(?,?,?,?,?)",
				user.getId(),user.getName(),
				user.getPassword(),user.getPoint().intValue(),
				user.getLevel().intValue());
	}

	public void update(final User user){
		this.jdbcTemplate.update("update users set name=?, password=?, point=?,level=? where id=?",
				user.getName(),user.getPassword(),user.getPoint().intValue(),
				user.getLevel().intValue(),user.getId());
	}

	public User get(final int id){
		User user = convertToUser(this.jdbcTemplate.queryForMap("select * from users where id=?",id));
		return user;
	}

	public List<User> getAll(){
		String query = "select * from users";
		return this.jdbcTemplate.query(query, this.userMapper);
	}

	public int countAll()	{
		return this.jdbcTemplate.queryForInt("select count(*) from users");
	}

	public void deletAll(){
		this.jdbcTemplate.update("delete from users");
	}
}
