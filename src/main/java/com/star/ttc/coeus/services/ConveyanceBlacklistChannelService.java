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

import com.star.ttc.coeus.interfaces.IConveyanceBlacklistChannelService;
import com.star.ttc.coeus.models.ConveyanceBlacklistChannel;
import com.star.ttc.coeus.repositories.ConveyanceBlacklistChannelRepository;

@Service
public class ConveyanceBlacklistChannelService extends MasterService implements IConveyanceBlacklistChannelService {

	private static final Logger logger = LoggerFactory.getLogger(ConveyanceBlacklistChannelService.class);

	@Autowired
	private ConveyanceBlacklistChannelRepository repository;

	@Override
	public List<ConveyanceBlacklistChannel> findAll() {

		List<ConveyanceBlacklistChannel> channels = new ArrayList<>();

		try {
			channels = repository.findAll();

			logger.info("Number of channels: " + channels.size());

		} catch(Exception ex) {
			// TODO: handle exception
		}

		return channels;
	}

	@Override
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Map<String, Object>> channelsMap = new ArrayList<>();
		try {
			channelsMap = getObjectList(repository);
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}

        logger.info("Number of channels: " + channelsMap.size());

        List<Map<String, Object>> list;

        if (channelsMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, channelsMap.size());
            list = channelsMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> conveyanceBlacklistChannelPage
          = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), channelsMap.size());

        return conveyanceBlacklistChannelPage;
    }


}
