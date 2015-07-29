package org.vinod.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.vinod.db.dao.ManagerDao;
import org.vinod.db.dao.dto.ManagerDTO;

@Repository
public class ManagerDaoImpl implements ManagerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(final ManagerDTO managerDTO) throws Exception{

		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					String sql = "INSERT INTO tbl_managers(name, mobile, email) VALUES (?, ?, ?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, managerDTO.getName());
					ps.setString(2, managerDTO.getMobile());
					ps.setString(3, managerDTO.getEmail());
					
					return ps;
				}
			});
		} catch (Exception e) {
			throw e;
		}
	}
}
