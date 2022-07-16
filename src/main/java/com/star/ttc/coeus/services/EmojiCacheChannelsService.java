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
import com.star.ttc.coeus.interfaces.IEmojiCacheChannelsService;
import com.star.ttc.coeus.models.EmojiCacheChannels;
import com.star.ttc.coeus.repositories.EmojiCacheChannelsRepository;

@Service
public class EmojiCacheChannelsService implements IEmojiCacheChannelsService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
	@Autowired
	private EmojiCacheChannelsRepository repository;
	
	@Override
	public List<EmojiCacheChannels> findAll() {
		
		List<EmojiCacheChannels> items = new ArrayList<>();
		
		try {
			items = (List<EmojiCacheChannels>) repository.findAll();
			
			logger.info("Size of emoji channels cache: " + items.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return items;
	}
	
	@Override
	public Page<EmojiCacheChannels> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<EmojiCacheChannels> items = repository.findAll();
        
        logger.info("Size of emoji channels cache: " + items.size());
        
        List<EmojiCacheChannels> list;

        if (items.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, items.size());
            list = items.subList(startItem, toIndex);
        }

        Page<EmojiCacheChannels> emojiChannelCachePage
          = new PageImpl<EmojiCacheChannels>(list, PageRequest.of(currentPage, pageSize), items.size());

        return emojiChannelCachePage;
    }
}
