package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.MessageCache;

public interface IMessageCacheService {
	List<MessageCache> findAll();
	
	Page<MessageCache> findPaginated(Pageable pageable);
}
