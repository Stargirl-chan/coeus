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

import com.star.ttc.coeus.interfaces.IEmojiCacheMessagesService;
import com.star.ttc.coeus.models.EmojiCacheMessages;
import com.star.ttc.coeus.repositories.EmojiCacheMessagesRepository;

@Service
public class EmojiCacheMessagesService extends MasterService implements IEmojiCacheMessagesService {

	private static final Logger logger = LoggerFactory.getLogger(EmojiCacheMessagesService.class);

	@Autowired
	private EmojiCacheMessagesRepository repository;

	@Override
	public List<EmojiCacheMessages> findAll() {

		List<EmojiCacheMessages> messages = new ArrayList<>();

		try {
			messages = repository.findAll();

			logger.info("Number of emoji message cache: " + messages.size());

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

        logger.info("Size of emoji message cache: " + messagesMap.size());

        List<Map<String, Object>> list;

        if (messagesMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, messagesMap.size());
            list = messagesMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> emojiMessageCachePage
          = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), messagesMap.size());

        return emojiMessageCachePage;
    }


}
