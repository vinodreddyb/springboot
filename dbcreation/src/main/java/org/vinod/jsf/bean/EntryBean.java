package org.vinod.jsf.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vinod.jsf.bean.vo.Entry;
import org.vinod.jsf.bean.vo.Manager;
import org.vinod.jsf.bean.vo.Search;
import org.vinod.jsf.service.EntryService;
import org.vinod.jsf.service.ManagerService;

@Component
@ManagedBean
@ViewScoped
public class EntryBean {
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private EntryService entryService;

	private List<Manager> listManagers = null;
	
	private List<Entry> listEntries = new ArrayList<>();
	
	private Entry entry = new Entry();
	
	private Search search = new Search();
	
	
	public void save() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			
			entryService.save(entry);
	        context.addMessage(null, new FacesMessage("Successful",  "Entry saved Succcessfully") );
	   } catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Exception while saving/updating Entry", 
					e.toString()));
			 
			e.printStackTrace();
		} finally {
			setEntry(new Entry());
		}
	}
	
	public void reset() {
		setEntry(new Entry());
	}
	
	public void search() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			
			setListEntries(entryService.searchEntry(search));
	        
	   } catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Exception occured while search Entries", 
					e.toString()));
			 
			e.printStackTrace();
		} finally {
			setSearch(new Search());
		}
	}
	
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

	public List<Entry> getListEntries() {
		return listEntries;
	}

	public void setListEntries(List<Entry> listEntries) {
		this.listEntries = listEntries;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}
}