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

import com.star.ttc.coeus.interfaces.IMessageCacheService;
import com.star.ttc.coeus.models.MessageCache;
import com.star.ttc.coeus.repositories.MessageCacheRepository;

@Service
public class MessageCacheService extends MasterService implements IMessageCacheService {

	private static final Logger logger = LoggerFactory.getLogger(MessageCacheService.class);
	
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
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<Map<String, Object>> itemsMap = new ArrayList<>();
		try {
			itemsMap = getObjectList(repository);
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}
        
        logger.info("Size of message cache: " + itemsMap.size());
        
        List<Map<String, Object>> list;

        if (itemsMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, itemsMap.size());
            list = itemsMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> messageCachePage
          = new PageImpl<Map<String, Object>>(list, PageRequest.of(currentPage, pageSize), itemsMap.size());

        return messageCachePage;
    }
	

}
