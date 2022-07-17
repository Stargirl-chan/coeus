package com.star.ttc.coeus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/***
 * Generic methods that all services share should be defined in here
 * To avoid duplicate code
 * @author star
 *
 */
public class MasterService {
	
	private static final Logger logger = LoggerFactory.getLogger(MasterService.class);

	/***
	 * Returns an list of objects from the given repository
	 * @param repository
	 * @throws MasterServiceException 
	 */
	protected <T> List<Map<String, Object>> getObjectList(JpaRepository<T, Long> repository) throws MasterServiceException {
		
		ObjectMapper oMapper = JsonMapper.builder()
				   .addModule(new JavaTimeModule())
				   .build();
		
		List<?> items = new ArrayList<>();
		
		try {
			items = (List<?>) repository.findAll();
			
		} catch(Exception ex) {
			String message = "Failed to load data from Repository";
			logger.error(message);
			throw new MasterServiceException(message, ex);
		}
		
		List<Map<String, Object>> objectMap = new ArrayList<>();
        
        for (var item : items) {
        	objectMap.add(oMapper.convertValue(item, Map.class));
        }
        
        return objectMap;
	}
}
