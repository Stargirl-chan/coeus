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
import com.star.ttc.coeus.interfaces.IConveyanceStateService;
import com.star.ttc.coeus.models.ConveyanceState;
import com.star.ttc.coeus.repositories.ConveyanceStateRepository;

@Service
public class ConveyanceStateService implements IConveyanceStateService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
	@Autowired
	private ConveyanceStateRepository repository;
	
	@Override
	public List<ConveyanceState> findAll() {
		
		List<ConveyanceState> states = new ArrayList<>();
		
		try {
			states = (List<ConveyanceState>) repository.findAll();
			
			logger.info("Size of conveyance state: " + states.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return states;
	}
	
	@Override
	public Page<ConveyanceState> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<ConveyanceState> states = repository.findAll();
        
        logger.info("Size of conveyance state: " + states.size());
        
        List<ConveyanceState> list;

        if (states.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, states.size());
            list = states.subList(startItem, toIndex);
        }

        Page<ConveyanceState> conveyanceStatePage
          = new PageImpl<ConveyanceState>(list, PageRequest.of(currentPage, pageSize), states.size());

        return conveyanceStatePage;
    }
}
