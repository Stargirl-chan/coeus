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
import com.star.ttc.coeus.interfaces.IWelcomeMessageService;
import com.star.ttc.coeus.models.WelcomeMessage;
import com.star.ttc.coeus.repositories.WelcomeMessageRepository;

@Service
public class WelcomeMessageService implements IWelcomeMessageService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
	@Autowired
	private WelcomeMessageRepository repository;
	
	@Override
	public List<WelcomeMessage> findAll() {
		
		List<WelcomeMessage> messages = new ArrayList<>();
		
		try {
			messages = (List<WelcomeMessage>) repository.findAll();
			
			logger.info("Number of welcome messages: " + messages.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return messages;
	}
	
	@Override
	public Page<WelcomeMessage> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<WelcomeMessage> messages = repository.findAll();
        
        logger.info("Number of welcome messages: " + messages.size());
        
        List<WelcomeMessage> list;

        if (messages.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, messages.size());
            list = messages.subList(startItem, toIndex);
        }

        Page<WelcomeMessage> welcomeMessagesPage
          = new PageImpl<WelcomeMessage>(list, PageRequest.of(currentPage, pageSize), messages.size());

        return welcomeMessagesPage;
    }
}
