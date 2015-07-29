package org.vinod.jsf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vinod.db.dao.ManagerDao;
import org.vinod.db.dao.dto.ManagerDTO;
import org.vinod.jsf.bean.vo.Manager;
import org.vinod.jsf.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {
 
	@Autowired
	private ManagerDao managerDao;
	
	public void save(Manager manager) throws Exception {
		ManagerDTO dto = new ManagerDTO();
		dto.setName(manager.getName());
		dto.setMobile(manager.getMobile());
		dto.setEmail(manager.getEmail());
		
		managerDao.save(dto);
	}
}
