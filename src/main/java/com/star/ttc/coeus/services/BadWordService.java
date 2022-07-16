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
import com.star.ttc.coeus.interfaces.IBadWordService;
import com.star.ttc.coeus.models.BadWord;
import com.star.ttc.coeus.repositories.BadWordRepository;

@Service
public class BadWordService implements IBadWordService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
	@Autowired
	private BadWordRepository repository;
	
	@Override
	public List<BadWord> findAll() {
		
		List<BadWord> words = new ArrayList<>();
		
		try {
			words = (List<BadWord>) repository.findAll();
			
			logger.info("Number of bad words: " + words.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return words;
	}
	
	@Override
	public Page<BadWord> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<BadWord> words = repository.findAll();
        
        logger.info("Number of bad words: " + words.size());
        
        List<BadWord> list;

        if (words.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, words.size());
            list = words.subList(startItem, toIndex);
        }

        Page<BadWord> badWordPage
          = new PageImpl<BadWord>(list, PageRequest.of(currentPage, pageSize), words.size());

        return badWordPage;
    }
}
