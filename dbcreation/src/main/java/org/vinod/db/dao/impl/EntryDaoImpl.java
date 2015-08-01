package org.vinod.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.vinod.db.dao.EntryDao;
import org.vinod.db.dao.dto.EntryDTO;
import org.vinod.db.row.EntryRowMapper;

@Repository
public class EntryDaoImpl implements EntryDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void saveEntry(final EntryDTO entry) throws Exception {
		final String sql ="INSERT INTO tbl_entry	"
				+ "(orgname, contactname, email, contactnum, location, startdate, enddate, shutdowndate,"
				+ "privateip, publicip, service, servicetype, ponum, podate, accmanager, vmname, vmram, vcpu,"
				+ "vhdd, osversion,remarks)	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,	?)";
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, entry.getOrgname());
					ps.setString(2, entry.getContactName());
					ps.setString(3, entry.getEmail());
					ps.setString(4, entry.getContactNum());
					ps.setString(5, entry.getLocation());
					ps.setDate(6, entry.getStartDate());
					ps.setDate(7, entry.getEndDate());
					ps.setDate(8, entry.getShutdownDate());
					ps.setString(9, entry.getPrivateIp());
					ps.setString(10, entry.getPublicIp());
					ps.setString(11, entry.getService());
					ps.setString(12, entry.getServiceType());
					ps.setString(13, entry.getPoNum());
					ps.setDate(14, entry.getPoDate());
					ps.setInt(15, entry.getAccManager());
					ps.setString(16, entry.getVmName());
					ps.setString(17, entry.getvRam());
					ps.setString(18, entry.getvCpu());
					ps.setString(19, entry.getvHdd());
					ps.setString(20, entry.getOsVersion());
					ps.setString(21, entry.getRemarks());
					
					return ps;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}

	public List<EntryDTO> searchEntry(String searchBy, String searchValue) throws Exception {
		List<EntryDTO> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT entry.id,entry.orgname,entry.contactname,entry.email,entry.contactnum,entry.location,entry.startdate,entry.enddate,"
				+ "entry.shutdowndate,entry.privateip, entry.publicip,entry.service,entry.servicetype,entry.ponum,"
				+ "entry.podate,manager.id as managerId,manager.name as manager,entry.vmname,entry.vmram,entry.vcpu,entry.vhdd,entry.osversion,"
				+ "entry.remarks,entry.entrydate FROM tbl_entry as entry "
				+ "join nmsdb.tbl_managers as manager on manager.id = entry.accmanager ");
		try {
			if("*".equals(searchValue)) {
				list = jdbcTemplate.query(sql.toString(), new EntryRowMapper());
			} else {
				if("prip".equals(searchBy)) {
					sql.append("WHERE entry.privateip = ?");
				} else if("puip".equals(searchBy)) {
					sql.append("WHERE entry.publicip = ?");
				} else if("vm".equals(searchBy)) {
					sql.append("WHERE entry.vmname = ?");
				} else if("customer".equals(searchBy)) {
					sql.append("WHERE entry.contactname = ?");
				} else if("manager".equals(searchBy)) {
					sql.append("WHERE manager.name = ?");
				}
				list = jdbcTemplate.query(sql.toString(),new Object[]{searchValue},new EntryRowMapper());
			}
			
		} catch (DataAccessException e) {
			throw e;
		}
		return list;
	}
	
}
