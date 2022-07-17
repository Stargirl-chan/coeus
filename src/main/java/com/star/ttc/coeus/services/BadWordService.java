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

import com.star.ttc.coeus.interfaces.IBadWordService;
import com.star.ttc.coeus.models.BadWord;
import com.star.ttc.coeus.repositories.BadWordRepository;

@Service
public class BadWordService extends MasterService implements IBadWordService {

	private static final Logger logger = LoggerFactory.getLogger(BadWordService.class);
	
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
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;  
        
        List<Map<String, Object>> wordsMap = new ArrayList<>();
		try {
			wordsMap = getObjectList(repository);
	        logger.info("Number of bad words: " + wordsMap.size());
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}
        
        List<Map<String, Object>> list;

        if (wordsMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, wordsMap.size());
            list = wordsMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> badWordPage
          = new PageImpl<Map<String, Object>>(list, PageRequest.of(currentPage, pageSize), wordsMap.size());

        return badWordPage;
    }
}
