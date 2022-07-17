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

import com.star.ttc.coeus.interfaces.IBotConfigPropertiesService;
import com.star.ttc.coeus.models.BotConfigProperties;
import com.star.ttc.coeus.repositories.BotConfigPropertiesRepository;

@Service
public class BotConfigPropertiesService extends MasterService implements IBotConfigPropertiesService {

	private static final Logger logger = LoggerFactory.getLogger(BotConfigPropertiesService.class);
	
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
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<Map<String, Object>> propertiesMap = new ArrayList<>();
		try {
			propertiesMap = getObjectList(repository);
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}
        
        logger.info("Number of config properties: " + propertiesMap.size());
        
        List<Map<String, Object>> list;

        if (propertiesMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, propertiesMap.size());
            list = propertiesMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> configPropertyPage
          = new PageImpl<Map<String, Object>>(list, PageRequest.of(currentPage, pageSize), propertiesMap.size());

        return configPropertyPage;
    }
	

}
