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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.star.ttc.coeus.interfaces.IBotConfigViewService;
import com.star.ttc.coeus.models.BotConfigView;
import com.star.ttc.coeus.repositories.BotConfigViewRepository;

@Service
public class BotConfigViewService extends MasterService implements IBotConfigViewService {

	private static final Logger logger = LoggerFactory.getLogger(BotConfigViewService.class);
	
	@Autowired
	private BotConfigViewRepository repository;
	
	@Override
	public List<BotConfigView> findAll() {
		
		List<BotConfigView> rows = new ArrayList<>();
		
		try {
			rows = (List<BotConfigView>) repository.findAll();
			
			logger.info("Number of rows: " + rows.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return rows;
	}
	
	@Override
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<Map<String, Object>> rowsMap = new ArrayList<>();
		try {
			rowsMap = getObjectList(repository);
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}
        
        logger.info("Number of rows: " + rowsMap.size());
        
        List<Map<String, Object>> list;

        if (rowsMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, rowsMap.size());
            list = rowsMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> configViewPage
          = new PageImpl<Map<String, Object>>(list, PageRequest.of(currentPage, pageSize), rowsMap.size());

        return configViewPage;
    }
	
	@Override
	public Map<String, Object> findById(Long id) {
		
		BotConfigView configView = new BotConfigView();
		
		ObjectMapper oMapper = JsonMapper.builder()
				   .addModule(new JavaTimeModule())
				   .build();
		
		Map<String, Object> objectMap = null;
		
		try {
			
			objectMap = getObject(repository, id);
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return objectMap;
	}
	
}
