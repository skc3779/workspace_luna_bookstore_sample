package com.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.History;
import com.bookstore.entity.HistoryActionType;

@Repository("historyDao")
public class HistoryDaoImpl implements HistoryDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public RowMapper<History> historyMapper =
			new RowMapper<History>() {
		@Override
		public History mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			History history = new History();
			history.setId(rs.getInt("id"));
			history.setUserId(rs.getInt("userId"));
			history.setBookId(rs.getInt("bookId"));
			history.setActionType(HistoryActionType.valueOf(rs.getInt("actionType")));
			java.util.Date date = new java.util.Date(rs.getTimestamp("insertDate").getTime());
			history.setInsertDate(date);

			return history;
		}
	};

	public void add(final History history){
		java.util.Date sqlDate = new java.util.Date(history.getInsertDate().getTime());
		this.jdbcTemplate.update("insert histories(userId,bookId,actionType,insertDate) " +
				"values(?,?,?,?)",
				history.getUserId(),
				history.getBookId(),history.getActionType().intValue(),
				sqlDate);
	}

	public List<History> getAll()	{
		String query = "select * from histories";
		return this.jdbcTemplate.query(query, historyMapper);
	}

	public List<History> getByUser(final int userId){
		String query = "select * from histories where userId = "+userId+" order by insertDate desc";
		return this.jdbcTemplate.query(query, historyMapper);
		// userId 별 최근 날짜 순
	}

	public List<History> getByBook(final int bookId){
		String query = "select * from histories where bookId = "+ bookId;
		return this.jdbcTemplate.query(query, historyMapper);
	}

	public void deletAll(){
		this.jdbcTemplate.update("delete from histories");
	}

	public int countAll(){
		return this.jdbcTemplate.queryForInt("select count(*) from histories");
	}
}
