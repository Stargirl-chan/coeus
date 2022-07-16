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
import com.star.ttc.coeus.interfaces.IBotConfigPropertiesService;
import com.star.ttc.coeus.models.BotConfigProperties;
import com.star.ttc.coeus.repositories.BotConfigPropertiesRepository;

@Service
public class BotConfigPropertiesService implements IBotConfigPropertiesService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
	@Autowired
	private BotConfigPropertiesRepository repository;
	
	@Override
	public List<BotConfigProperties> findAll() {
		
		List<BotConfigProperties> properties = new ArrayList<>();
		
		try {
			properties = (List<BotConfigProperties>) repository.findAll();
			
			logger.info("Number of properties: " + properties.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return properties;
	}
	
	@Override
	public Page<BotConfigProperties> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<BotConfigProperties> properties = repository.findAll();
        
        logger.info("Number of config properties: " + properties.size());
        
        List<BotConfigProperties> list;

        if (properties.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, properties.size());
            list = properties.subList(startItem, toIndex);
        }

        Page<BotConfigProperties> configPropertyPage
          = new PageImpl<BotConfigProperties>(list, PageRequest.of(currentPage, pageSize), properties.size());

        return configPropertyPage;
    }
}
