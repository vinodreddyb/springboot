package org.vinod.jsf.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vinod.jsf.bean.vo.Entry;
import org.vinod.jsf.bean.vo.Manager;
import org.vinod.jsf.service.ManagerService;

@Component
@ManagedBean
@ViewScoped
public class EntryBean {
	
	@Autowired
	private ManagerService managerService;

	private List<Manager> listManagers = null;
	
	private Entry entry = new Entry();
	
	public void onTabChange(TabChangeEvent event) {
		
		String tabClicked = event.getTab().getTitle();
		if("Data".equalsIgnoreCase(tabClicked)) {
			if(listManagers == null || listManagers.size() == 0) {
				loadAllManagers();
			}
		}
        
    }
	
	public void loadAllManagers() {
		try {
			listManagers = managerService.getAllManagers();
		} catch (Exception e) {
		
		}
	}

	public List<Manager> getListManagers() {
		return listManagers;
	}

	public void setListManagers(List<Manager> listManagers) {
		this.listManagers = listManagers;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}
}