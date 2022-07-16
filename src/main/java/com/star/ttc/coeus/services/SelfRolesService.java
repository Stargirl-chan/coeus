package com.star.ttc.coeus.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.star.ttc.coeus.CoeusApplication;
import com.star.ttc.coeus.interfaces.ISelfRolesService;
import com.star.ttc.coeus.models.SelfRoles;
import com.star.ttc.coeus.repositories.SelfRolesRepository;

@Service
public class SelfRolesService implements ISelfRolesService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
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
	public Page<SelfRoles> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<SelfRoles> roles = repository.findAll();
        
        logger.info("Number of self roles: " + roles.size());
        
        List<SelfRoles> list;

        if (roles.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, roles.size());
            list = roles.subList(startItem, toIndex);
        }

        Page<SelfRoles> selfRolesPage
          = new PageImpl<SelfRoles>(list, PageRequest.of(currentPage, pageSize), roles.size());

        return selfRolesPage;
    }
}
