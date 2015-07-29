package org.vinod.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vinod.jsf.bean.vo.Manager;
import org.vinod.jsf.service.ManagerService;

@Component
@ManagedBean
@ViewScoped
public class ManagerBean {

	private Manager manager = new Manager();
	
	@Autowired
	private ManagerService managerService;
	
	public void saveManager() {
		try {
			managerService.save(manager);
			manager = new Manager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
}
