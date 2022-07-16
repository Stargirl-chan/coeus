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
import com.star.ttc.coeus.interfaces.IWebhookksService;
import com.star.ttc.coeus.models.Webhooks;
import com.star.ttc.coeus.repositories.WebhooksRepository;

@Service
public class WebhooksService implements IWebhookksService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
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
	public Page<Webhooks> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<Webhooks> hooks = repository.findAll();
        
        logger.info("Number of webhooks: " + hooks.size());
        
        List<Webhooks> list;

        if (hooks.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, hooks.size());
            list = hooks.subList(startItem, toIndex);
        }

        Page<Webhooks> webhookPage
          = new PageImpl<Webhooks>(list, PageRequest.of(currentPage, pageSize), hooks.size());

        return webhookPage;
    }
}
