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
import com.star.ttc.coeus.interfaces.IHaroldEmojiService;
import com.star.ttc.coeus.models.HaroldEmoji;
import com.star.ttc.coeus.repositories.HaroldEmojiRepository;

@Service
public class HaroldEmojiService implements IHaroldEmojiService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
	@Autowired
	private HaroldEmojiRepository repository;
	
	@Override
	public List<HaroldEmoji> findAll() {
		
		List<HaroldEmoji> emojis = new ArrayList<>();
		
		try {
			emojis = (List<HaroldEmoji>) repository.findAll();
			
			logger.info("Number of harold emojis: " + emojis.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return emojis;
	}
	
	@Override
	public Page<HaroldEmoji> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<HaroldEmoji> emojis = repository.findAll();
        
        logger.info("Number of harold emojis: " + emojis.size());
        
        List<HaroldEmoji> list;

        if (emojis.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, emojis.size());
            list = emojis.subList(startItem, toIndex);
        }

        Page<HaroldEmoji> haroldEmojiPage
          = new PageImpl<HaroldEmoji>(list, PageRequest.of(currentPage, pageSize), emojis.size());

        return haroldEmojiPage;
    }
}
