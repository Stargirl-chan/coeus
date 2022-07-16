package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.SelfRoles;

public interface ISelfRolesService {
	List<SelfRoles> findAll();
	
	Page<SelfRoles> findPaginated(Pageable pageable);
}
