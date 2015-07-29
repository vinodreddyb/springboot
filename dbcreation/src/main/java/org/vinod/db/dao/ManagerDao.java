package org.vinod.db.dao;

import org.vinod.db.dao.dto.ManagerDTO;

public interface ManagerDao  {
	
	public void save(ManagerDTO managerDTO) throws Exception;
}
