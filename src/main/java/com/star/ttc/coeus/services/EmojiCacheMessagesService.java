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
import com.star.ttc.coeus.interfaces.IEmojiCacheMessagesService;
import com.star.ttc.coeus.models.EmojiCacheMessages;
import com.star.ttc.coeus.repositories.EmojiCacheMessagesRepository;

@Service
public class EmojiCacheMessagesService implements IEmojiCacheMessagesService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
	@Autowired
	private EmojiCacheMessagesRepository repository;
	
	@Override
	public List<EmojiCacheMessages> findAll() {
		
		List<EmojiCacheMessages> messages = new ArrayList<>();
		
		try {
			messages = (List<EmojiCacheMessages>) repository.findAll();
			
			logger.info("Number of emoji message cache: " + messages.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return messages;
	}
	
	@Override
	public Page<EmojiCacheMessages> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<EmojiCacheMessages> messages = repository.findAll();
        
        logger.info("Size of emoji message cache: " + messages.size());
        
        List<EmojiCacheMessages> list;

        if (messages.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, messages.size());
            list = messages.subList(startItem, toIndex);
        }

        Page<EmojiCacheMessages> emojiMessageCachePage
          = new PageImpl<EmojiCacheMessages>(list, PageRequest.of(currentPage, pageSize), messages.size());

        return emojiMessageCachePage;
    }
}
