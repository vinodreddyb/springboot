package org.vinod.db.dao;

import java.util.List;

import org.vinod.db.dao.dto.EntryDTO;

public interface EntryDao {
	public void saveEntry(final EntryDTO entry) throws Exception;
	public List<EntryDTO> searchEntry(String searchBy, String searchValue) throws Exception;
}
