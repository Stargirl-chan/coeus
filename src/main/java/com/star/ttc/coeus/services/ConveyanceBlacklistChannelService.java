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
import com.star.ttc.coeus.interfaces.IConveyanceBlacklistChannelService;
import com.star.ttc.coeus.models.ConveyanceBlacklistChannel;
import com.star.ttc.coeus.repositories.ConveyanceBlacklistChannelRepository;

@Service
public class ConveyanceBlacklistChannelService implements IConveyanceBlacklistChannelService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
	@Autowired
	private ConveyanceBlacklistChannelRepository repository;
	
	@Override
	public List<ConveyanceBlacklistChannel> findAll() {
		
		List<ConveyanceBlacklistChannel> channels = new ArrayList<>();
		
		try {
			channels = (List<ConveyanceBlacklistChannel>) repository.findAll();
			
			logger.info("Number of channels: " + channels.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return channels;
	}
	
	@Override
	public Page<ConveyanceBlacklistChannel> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<ConveyanceBlacklistChannel> channels = repository.findAll();
        
        logger.info("Number of channels: " + channels.size());
        
        List<ConveyanceBlacklistChannel> list;

        if (channels.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, channels.size());
            list = channels.subList(startItem, toIndex);
        }

        Page<ConveyanceBlacklistChannel> conveyanceBlacklistChannelPage
          = new PageImpl<ConveyanceBlacklistChannel>(list, PageRequest.of(currentPage, pageSize), channels.size());

        return conveyanceBlacklistChannelPage;
    }
}
