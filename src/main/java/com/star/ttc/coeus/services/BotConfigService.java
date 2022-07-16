package com.star.ttc.coeus.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.ttc.coeus.CoeusApplication;
import com.star.ttc.coeus.interfaces.IBotConfigService;
import com.star.ttc.coeus.models.BotConfig;
import com.star.ttc.coeus.repositories.BotConfigRepository;

@Service
public class BotConfigService implements IBotConfigService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
	@Autowired
	private BotConfigRepository repository;
	
	@Override
	public List<BotConfig> findAll() {
		
		List<BotConfig> configs = new ArrayList<>();
		
		try {
			configs = (List<BotConfig>) repository.findAll();
			
			logger.info("Number of bad words: " + configs.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return configs;
	}
}
