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
import com.star.ttc.coeus.interfaces.IEasterEggGifsService;
import com.star.ttc.coeus.models.EasterEggGifs;
import com.star.ttc.coeus.repositories.EasterEggGifsRepository;

@Service
public class EasterEggGifsService implements IEasterEggGifsService {

	private static final Logger logger = LoggerFactory.getLogger(CoeusApplication.class);
	
	@Autowired
	private EasterEggGifsRepository repository;
	
	@Override
	public List<EasterEggGifs> findAll() {
		
		List<EasterEggGifs> eggs = new ArrayList<>();
		
		try {
			eggs = (List<EasterEggGifs>) repository.findAll();
			
			logger.info("Number of easter eggs: " + eggs.size());
			
		} catch(Exception ex) {
			// TODO: handle exception
		}
		
		return eggs;
	}
	
	@Override
	public Page<EasterEggGifs> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        
        List<EasterEggGifs> eggs = repository.findAll();
        
        logger.info("Number of easter eggs: " + eggs.size());
        
        List<EasterEggGifs> list;

        if (eggs.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, eggs.size());
            list = eggs.subList(startItem, toIndex);
        }

        Page<EasterEggGifs> easterEggGifsPage
          = new PageImpl<EasterEggGifs>(list, PageRequest.of(currentPage, pageSize), eggs.size());

        return easterEggGifsPage;
    }
}
