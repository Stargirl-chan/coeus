package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.BadWord;

public interface IBadWordService {
	List<BadWord> findAll();
	
	Page<BadWord> findPaginated(Pageable pageable);
}
