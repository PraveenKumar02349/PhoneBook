package com.angler.phonebook.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.angler.phonebook.Utils.MasterRequest;
import com.angler.phonebook.Utils.Utils;
import com.angler.phonebook.model.UserInfo;

@Transactional
@Repository
public class phonebookDAO implements phonebookDAOInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public final static RowMapper<UserInfo> userMapper = new BeanPropertyRowMapper<UserInfo>(UserInfo.class);

	@Override
	public List<UserInfo> getUserDetails(Pageable pageable, MasterRequest commonRequest) {
		// TODO Auto-generated method stub
		String userSql = "SELECT SQL_CALC_FOUND_ROWS UI.USER_ID as id,UI.FIRSTNAME as FirstName,UI.LASTNAME,UI.EMAILID,UC.PHONENUMBER,UC.ALTERNATEPHONENUMBER "
				+ "FROM USER_INFO UI JOIN USER_CONTACT UC ON UC.USER_ID = UI.USER_ID AND UC.DELETE_FLAG = 1 AND UI.DELETE_FLAG = 1";

		if (pageable != null)
			userSql = userSql + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset() + "";
		List<UserInfo> userList = jdbcTemplate.query(userSql, userMapper);
		if (userList.size() > 0)
			userList.get(0)
					.setTotalCount(jdbcTemplate.queryForObject("SELECT FOUND_ROWS() as total_count", Long.class));
		return userList;
	}

	@Override
	public long addUser(UserInfo userInfo) throws Exception {
		Calendar cal = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

		long userId = 0;
		boolean status = false;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

		if (!Utils.isValidEmail(userInfo.getEmailId()))
			throw new Exception("Invalid EmailID");

		List<String> dataList = new ArrayList<String>();
		if (!userInfo.getFirstName().isEmpty()) {
			dataList.add("FIRSTNAME");
			mapSqlParameterSource.addValue("FIRSTNAME", userInfo.getFirstName().trim());
		}
		if (!userInfo.getLastName().isEmpty()) {
			dataList.add("LASTNAME");
			mapSqlParameterSource.addValue("LASTNAME", userInfo.getLastName().trim());
		}

		if (!userInfo.getEmailId().isEmpty()) {
			dataList.add("EMAILID");
			mapSqlParameterSource.addValue("EMAILID", userInfo.getEmailId().trim());
		}

		StringBuilder insertQuery = new StringBuilder();
		StringBuilder valueQuery = new StringBuilder();

		for (String data : dataList) {
			insertQuery.append(data);
			insertQuery.append(",");

			valueQuery.append(":" + data);
			valueQuery.append(",");
		}
		String sql = "SELECT * FROM USER_INFO WHERE UPPER(EMAILID) = '" + userInfo.getEmailId().toUpperCase() + "'";
		List<UserInfo> user = jdbcTemplate.query(sql, userMapper);
		if (!user.isEmpty()) {
			throw new Exception("User Already Exist");
		} else {

			String insertuser = "INSERT INTO USER_INFO ( " + insertQuery.toString()
					+ " DELETE_FLAG, CREATED_DATE) values " + "( " + valueQuery.toString()
					+ " :DELETE_FLAG, :CREATED_DATE)";
			SqlParameterSource parameterSource = mapSqlParameterSource.addValue("DELETE_FLAG", 1)
					.addValue("CREATED_DATE", timestamp);
			Utils.log("" + insertuser);
			namedJdbcTemplate.update(insertuser, parameterSource, keyHolder, new String[] { "USER_ID" });
			userId = keyHolder.getKey().longValue();

			if (userId > 0) {
				String insertContact = "INSERT INTO USER_CONTACT ( USER_ID,PHONENUMBER,ALTERNATEPHONENUMBER,DELETE_FLAG, CREATED_DATE) values "
						+ "( :USER_ID,:PHONENUMBER,:ALTERNATEPHONENUMBER,:DELETE_FLAG, :CREATED_DATE)";
				parameterSource = mapSqlParameterSource.addValue("USER_ID", userId)
						.addValue("PHONENUMBER", userInfo.getPhoneNumber())
						.addValue("ALTERNATEPHONENUMBER", userInfo.getAlternatePhoneNumber()).addValue("DELETE_FLAG", 1)
						.addValue("CREATED_DATE", timestamp);
				Utils.log("" + insertContact);
				namedJdbcTemplate.update(insertContact, parameterSource, keyHolder, new String[] { "USER_CONTACT_ID" });
				userId = keyHolder.getKey().longValue();
			}

		}

		return userId;
	}

	private List<UserInfo> getUserDetails(String[] columnName, String[] whereArg, Object... whereValue) {
		// TODO Auto-generated method stub
		String sql = "SELECT " + ((columnName.length == 0) ? "*" : Utils.getConcatString(columnName, ", "))
				+ " FROM USER_INFO"
				+ ((whereArg.length == 0) ? "" : " WHERE " + Utils.getWhereString(whereArg, " = ? ")) + "";

		List<UserInfo> region = new ArrayList<>();
		if (whereValue.length == 0)
			region = jdbcTemplate.query(sql, userMapper);
		else
			region = jdbcTemplate.query(sql, userMapper, whereValue);
		return region;
	}

	@Override
	public boolean updateUserInfo(String[] columnName, String[] whereArg, Object... whereValue) {
		// TODO Auto-generated method stub
		String sql = "UPDATE USER_INFO SET " + Utils.getUpdateString(columnName, " = ?") + " WHERE "
				+ Utils.getWhereString(whereArg, " = ? ") + "";
		Utils.log("sql " + sql);
		int count = -1;
		try {
			if (whereValue.length == 0)
				count = jdbcTemplate.update(sql);
			else
				count = jdbcTemplate.update(sql, whereValue);
		} catch (Exception e) {
			Utils.log("" + e.getMessage());
			// TODO: handle exception
		}
		if (count > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean updateUserContact(String[] columnName, String[] whereArg, Object... whereValue) {
		// TODO Auto-generated method stub
		String sql = "UPDATE USER_CONTACT SET " + Utils.getUpdateString(columnName, " = ?") + " WHERE "
				+ Utils.getWhereString(whereArg, " = ? ") + "";
		Utils.log("sql " + sql);
		int count = -1;
		if (whereValue.length == 0)
			count = jdbcTemplate.update(sql);
		else
			count = jdbcTemplate.update(sql, whereValue);
		if (count > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<UserInfo> getUserDetails(String id) {

		String userSql = "SELECT SQL_CALC_FOUND_ROWS UI.USER_ID as id,UI.FIRSTNAME as FirstName,UI.LASTNAME,UI.EMAILID,UC.PHONENUMBER,UC.ALTERNATEPHONENUMBER "
				+ "FROM USER_INFO UI JOIN USER_CONTACT UC ON UC.USER_ID = UI.USER_ID AND UC.DELETE_FLAG = 1 AND UI.DELETE_FLAG = 1 AND UI.USER_ID = "
				+ id + "";

		List<UserInfo> userList = jdbcTemplate.query(userSql, userMapper);
		if (userList.size() > 0)
			userList.get(0)
					.setTotalCount(jdbcTemplate.queryForObject("SELECT FOUND_ROWS() as total_count", Long.class));
		return userList;
	}

}
