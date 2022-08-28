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

import com.star.ttc.coeus.interfaces.IEmojiCacheService;
import com.star.ttc.coeus.models.EmojiCache;
import com.star.ttc.coeus.repositories.EmojiCacheRepository;

@Service
public class EmojiCacheService extends MasterService implements IEmojiCacheService {

	private static final Logger logger = LoggerFactory.getLogger(EmojiCacheService.class);

	@Autowired
	private EmojiCacheRepository repository;

	@Override
	public List<EmojiCache> findAll() {

		List<EmojiCache> items = new ArrayList<>();

		try {
			items = repository.findAll();

			logger.info("Size of emoji cache: " + items.size());

		} catch(Exception ex) {
			// TODO: handle exception
		}

		return items;
	}

	@Override
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Map<String, Object>> itemsMap = new ArrayList<>();
		try {
			itemsMap = getObjectList(repository);
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}

        logger.info("Size of emoji cache: " + itemsMap.size());

        List<Map<String, Object>> list;

        if (itemsMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, itemsMap.size());
            list = itemsMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> emojiCachePage
          = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), itemsMap.size());

        return emojiCachePage;
    }


}
