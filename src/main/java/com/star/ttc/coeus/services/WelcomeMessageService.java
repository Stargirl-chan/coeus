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

import com.star.ttc.coeus.interfaces.IWelcomeMessageService;
import com.star.ttc.coeus.models.WelcomeMessage;
import com.star.ttc.coeus.repositories.WelcomeMessageRepository;

@Service
public class WelcomeMessageService extends MasterService implements IWelcomeMessageService {

	private static final Logger logger = LoggerFactory.getLogger(WelcomeMessageService.class);

	@Autowired
	private WelcomeMessageRepository repository;

	@Override
	public List<WelcomeMessage> findAll() {

		List<WelcomeMessage> messages = new ArrayList<>();

		try {
			messages = repository.findAll();

			logger.info("Number of welcome messages: " + messages.size());

		} catch(Exception ex) {
			// TODO: handle exception
		}

		return messages;
	}

	@Override
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Map<String, Object>> messagesMap = new ArrayList<>();
		try {
			messagesMap = getObjectList(repository);
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}

        logger.info("Number of welcome messages: " + messagesMap.size());

        List<Map<String, Object>> list;

        if (messagesMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, messagesMap.size());
            list = messagesMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> welcomeMessagesPage
          = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), messagesMap.size());

        return welcomeMessagesPage;
    }
}
