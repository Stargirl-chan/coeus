package com.star.ttc.coeus.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.star.ttc.coeus.interfaces.ISelfRolesService;
import com.star.ttc.coeus.models.SelfRoles;
import com.star.ttc.coeus.repositories.SelfRolesRepository;

@Service
public class SelfRolesService extends MasterService implements ISelfRolesService {

	private static final Logger logger = LoggerFactory.getLogger(SelfRolesService.class);
	
	@Autowired
	private SelfRolesRepository repository;
	
	@Override
	public List<SelfRoles> findAll() {
		
		List<SelfRoles> roles = new ArrayList<>();
		
		try {
			roles = (List<SelfRoles>) repository.findAll();
			
			logger.info("Number of self roles: " + roles.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return roles;
	}
	
	@Override
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<Map<String, Object>> rolesMap = new ArrayList<>();
		try {
			rolesMap = getObjectList(repository);
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}
        
        logger.info("Number of self roles: " + rolesMap.size());
        
        List<Map<String, Object>> list;

        if (rolesMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, rolesMap.size());
            list = rolesMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> selfRolesPage
          = new PageImpl<Map<String, Object>>(list, PageRequest.of(currentPage, pageSize), rolesMap.size());

        return selfRolesPage;
    }
	

}
