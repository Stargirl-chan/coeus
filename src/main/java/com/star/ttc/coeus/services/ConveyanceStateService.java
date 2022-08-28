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

import com.star.ttc.coeus.interfaces.IConveyanceStateService;
import com.star.ttc.coeus.models.ConveyanceState;
import com.star.ttc.coeus.repositories.ConveyanceStateRepository;

@Service
public class ConveyanceStateService extends MasterService implements IConveyanceStateService {

	private static final Logger logger = LoggerFactory.getLogger(ConveyanceStateService.class);

	@Autowired
	private ConveyanceStateRepository repository;

	@Override
	public List<ConveyanceState> findAll() {

		List<ConveyanceState> states = new ArrayList<>();

		try {
			states = repository.findAll();

			logger.info("Size of conveyance state: " + states.size());

		} catch(Exception ex) {
			// TODO: handle exception
		}

		return states;
	}

	@Override
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Map<String, Object>> statesMap = new ArrayList<>();
		try {
			statesMap = getObjectList(repository);
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}

        logger.info("Size of conveyance state: " + statesMap.size());

        List<Map<String, Object>> list;

        if (statesMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, statesMap.size());
            list = statesMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> conveyanceStatePage
          = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), statesMap.size());

        return conveyanceStatePage;
    }


}
