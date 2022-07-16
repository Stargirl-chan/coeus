package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.BotConfigProperties;

public interface IBotConfigPropertiesService {
	List<BotConfigProperties> findAll();
	
	Page<BotConfigProperties> findPaginated(Pageable pageable);
}
