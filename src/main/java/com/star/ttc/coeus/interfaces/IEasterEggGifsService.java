package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.EasterEggGifs;

public interface IEasterEggGifsService {
	List<EasterEggGifs> findAll();
	
	Page<EasterEggGifs> findPaginated(Pageable pageable);
}
