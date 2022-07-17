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

import com.star.ttc.coeus.interfaces.IWebhookksService;
import com.star.ttc.coeus.models.Webhooks;
import com.star.ttc.coeus.repositories.WebhooksRepository;

@Service
public class WebhooksService extends MasterService implements IWebhookksService {

	private static final Logger logger = LoggerFactory.getLogger(WebhooksService.class);
	
	@Autowired
	private WebhooksRepository repository;
	
	@Override
	public List<Webhooks> findAll() {
		
		List<Webhooks> hooks = new ArrayList<>();
		
		try {
			hooks = (List<Webhooks>) repository.findAll();
			
			logger.info("Number of webhooks: " + hooks.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return hooks;
	}
	
	@Override
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<Map<String, Object>> hooksMap = new ArrayList<>();
		try {
			hooksMap = getObjectList(repository);
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}
        
        logger.info("Number of webhooks: " + hooksMap.size());
        
        List<Map<String, Object>> list;

        if (hooksMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, hooksMap.size());
            list = hooksMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> webhookPage
          = new PageImpl<Map<String, Object>>(list, PageRequest.of(currentPage, pageSize), hooksMap.size());

        return webhookPage;
    }
	

}
