package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.BotConfigView;

public interface IBotConfigViewService {
	List<BotConfigView> findAll();
	
	Page<BotConfigView> findPaginated(Pageable pageable);
}