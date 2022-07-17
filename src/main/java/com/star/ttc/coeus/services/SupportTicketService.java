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

import com.star.ttc.coeus.interfaces.ISupportTicketService;
import com.star.ttc.coeus.models.SupportTicket;
import com.star.ttc.coeus.repositories.SupportTicketRepository;

@Service
public class SupportTicketService extends MasterService implements ISupportTicketService {

	private static final Logger logger = LoggerFactory.getLogger(SupportTicketService.class);
	
	@Autowired
	private SupportTicketRepository repository;
	
	@Override
	public List<SupportTicket> findAll() {
		
		List<SupportTicket> tickets = new ArrayList<>();
		
		try {
			tickets = (List<SupportTicket>) repository.findAll();
			
			logger.info("Number of self roles: " + tickets.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return tickets;
	}
	
	@Override
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<Map<String, Object>> ticketsMap = new ArrayList<>();
		try {
			ticketsMap = getObjectList(repository);
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}
        
        logger.info("Number of support tickets: " + ticketsMap.size());
        
        List<Map<String, Object>> list;

        if (ticketsMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, ticketsMap.size());
            list = ticketsMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> supportTicketPage
          = new PageImpl<Map<String, Object>>(list, PageRequest.of(currentPage, pageSize), ticketsMap.size());

        return supportTicketPage;
    }
	

}
