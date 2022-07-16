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
import com.star.ttc.coeus.interfaces.IBotConfigViewService;
import com.star.ttc.coeus.models.BotConfigView;
import com.star.ttc.coeus.repositories.BotConfigViewRepository;

@Service
public class BotConfigViewService implements IBotConfigViewService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
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
	public Page<BotConfigView> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<BotConfigView> rows = repository.findAll();
        
        logger.info("Number of rows: " + rows.size());
        
        List<BotConfigView> list;

        if (rows.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, rows.size());
            list = rows.subList(startItem, toIndex);
        }

        Page<BotConfigView> configViewPage
          = new PageImpl<BotConfigView>(list, PageRequest.of(currentPage, pageSize), rows.size());

        return configViewPage;
    }
}
