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
import com.star.ttc.coeus.interfaces.IMessageCacheService;
import com.star.ttc.coeus.models.MessageCache;
import com.star.ttc.coeus.repositories.MessageCacheRepository;

@Service
public class MessageCacheService implements IMessageCacheService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
	@Autowired
	private MessageCacheRepository repository;
	
	@Override
	public List<MessageCache> findAll() {
		
		List<MessageCache> items = new ArrayList<>();
		
		try {
			items = (List<MessageCache>) repository.findAll();
			
			logger.info("Size of message cache: " + items.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return items;
	}
	
	@Override
	public Page<MessageCache> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<MessageCache> items = repository.findAll();
        
        logger.info("Size of message cache: " + items.size());
        
        List<MessageCache> list;

        if (items.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, items.size());
            list = items.subList(startItem, toIndex);
        }

        Page<MessageCache> messageCachePage
          = new PageImpl<MessageCache>(list, PageRequest.of(currentPage, pageSize), items.size());

        return messageCachePage;
    }
}
