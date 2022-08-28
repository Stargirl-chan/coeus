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

import com.star.ttc.coeus.interfaces.IHaroldEmojiService;
import com.star.ttc.coeus.models.HaroldEmoji;
import com.star.ttc.coeus.repositories.HaroldEmojiRepository;

@Service
public class HaroldEmojiService extends MasterService implements IHaroldEmojiService {

	private static final Logger logger = LoggerFactory.getLogger(HaroldEmojiService.class);

	@Autowired
	private HaroldEmojiRepository repository;

	@Override
	public List<HaroldEmoji> findAll() {

		List<HaroldEmoji> emojis = new ArrayList<>();

		try {
			emojis = repository.findAll();

			logger.info("Number of harold emojis: " + emojis.size());

		} catch(Exception ex) {
			// TODO: handle exception
		}

		return emojis;
	}

	@Override
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Map<String, Object>> emojisMap = new ArrayList<>();
		try {
			emojisMap = getObjectList(repository);
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}

        logger.info("Number of harold emojis: " + emojisMap.size());

        List<Map<String, Object>> list;

        if (emojisMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, emojisMap.size());
            list = emojisMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> haroldEmojiPage
          = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), emojisMap.size());

        return haroldEmojiPage;
    }


}
