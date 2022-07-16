package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.Webhooks;

public interface IWebhookksService {
	List<Webhooks> findAll();
	
	Page<Webhooks> findPaginated(Pageable pageable);
}
