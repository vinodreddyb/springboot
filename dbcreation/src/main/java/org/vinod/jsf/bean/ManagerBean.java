package org.vinod.jsf.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vinod.jsf.bean.vo.Manager;
import org.vinod.jsf.service.ManagerService;

@Component
@ManagedBean
@ViewScoped
public class ManagerBean {

	private Manager manager = new Manager();
	
	private List<Manager> listManagers = new ArrayList<>();
	
	private boolean displayManagers;
	
	@Autowired
	private ManagerService managerService;
	
	public void saveManager() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			managerService.save(manager);
			manager = new Manager();
			
	        context.addMessage(null, new FacesMessage("Successful",  "Manager Added Successfully") );
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Exception while saving manager", 
					e.toString()));
			e.printStackTrace();
		}
	}
	
	public void loadAllManagers() {
		System.out.println("hiiiiiiiiiiii");
		try {
			
			setListManagers(managerService.getAllManagers());
			setDisplayManagers(true);
		} catch (Exception e) {
		
		}
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Manager> getListManagers() {
		return listManagers;
	}

	public void setListManagers(List<Manager> listManagers) {
		this.listManagers = listManagers;
	}

	public boolean isDisplayManagers() {
		return displayManagers;
	}

	public void setDisplayManagers(boolean displayManagers) {
		this.displayManagers = displayManagers;
	}
	
}
