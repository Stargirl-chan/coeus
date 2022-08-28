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

import com.star.ttc.coeus.interfaces.IEasterEggGifsService;
import com.star.ttc.coeus.models.EasterEggGifs;
import com.star.ttc.coeus.repositories.EasterEggGifsRepository;

@Service
public class EasterEggGifsService extends MasterService implements IEasterEggGifsService {

	private static final Logger logger = LoggerFactory.getLogger(EasterEggGifsService.class);

	@Autowired
	private EasterEggGifsRepository repository;

	@Override
	public List<EasterEggGifs> findAll() {

		List<EasterEggGifs> eggs = new ArrayList<>();

		try {
			eggs = repository.findAll();

			logger.info("Number of easter eggs: " + eggs.size());

		} catch(Exception ex) {
			// TODO: handle exception
		}

		return eggs;
	}

	@Override
	public Page<Map<String, Object>> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Map<String, Object>> eggsMap = new ArrayList<>();
		try {
			eggsMap = getObjectList(repository);
		} catch (MasterServiceException ex) {
			logger.error("Failed to get object map");
			logger.error(ex.toString());
		}

        logger.info("Number of easter eggs: " + eggsMap.size());

        List<Map<String, Object>> list;

        if (eggsMap.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, eggsMap.size());
            list = eggsMap.subList(startItem, toIndex);
        }

        Page<Map<String, Object>> easterEggGifsPage
          = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), eggsMap.size());

        return easterEggGifsPage;
    }


}
