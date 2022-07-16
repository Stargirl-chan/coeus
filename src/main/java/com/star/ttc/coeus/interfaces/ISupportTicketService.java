package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.SupportTicket;

public interface ISupportTicketService {
	List<SupportTicket> findAll();
	
	Page<SupportTicket> findPaginated(Pageable pageable);
}
