package com.star.ttc.coeus.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.BadWord;

public interface IBadWordService {
	List<BadWord> findAll();

	Page<Map<String, Object>> findPaginated(Pageable pageable);

	Map<String, Object> findById(Long id);

	BadWord update(BadWord badWord);

	void delete(Long id);
}
