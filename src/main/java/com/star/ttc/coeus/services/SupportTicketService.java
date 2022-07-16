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
import com.star.ttc.coeus.interfaces.ISupportTicketService;
import com.star.ttc.coeus.models.SupportTicket;
import com.star.ttc.coeus.repositories.SupportTicketRepository;

@Service
public class SupportTicketService implements ISupportTicketService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
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
	public Page<SupportTicket> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<SupportTicket> tickets = repository.findAll();
        
        logger.info("Number of support tickets: " + tickets.size());
        
        List<SupportTicket> list;

        if (tickets.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, tickets.size());
            list = tickets.subList(startItem, toIndex);
        }

        Page<SupportTicket> supportTicketPage
          = new PageImpl<SupportTicket>(list, PageRequest.of(currentPage, pageSize), tickets.size());

        return supportTicketPage;
    }
}
