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
import com.star.ttc.coeus.interfaces.IEmojiCacheService;
import com.star.ttc.coeus.models.EmojiCache;
import com.star.ttc.coeus.repositories.EmojiCacheRepository;

@Service
public class EmojiCacheService implements IEmojiCacheService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
	@Autowired
	private EmojiCacheRepository repository;
	
	@Override
	public List<EmojiCache> findAll() {
		
		List<EmojiCache> items = new ArrayList<>();
		
		try {
			items = (List<EmojiCache>) repository.findAll();
			
			logger.info("Size of emoji cache: " + items.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return items;
	}
	
	@Override
	public Page<EmojiCache> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<EmojiCache> items = repository.findAll();
        
        logger.info("Size of emoji cache: " + items.size());
        
        List<EmojiCache> list;

        if (items.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, items.size());
            list = items.subList(startItem, toIndex);
        }

        Page<EmojiCache> emojiCachePage
          = new PageImpl<EmojiCache>(list, PageRequest.of(currentPage, pageSize), items.size());

        return emojiCachePage;
    }
}
