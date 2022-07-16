package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.HaroldEmoji;

public interface IHaroldEmojiService {
	List<HaroldEmoji> findAll();
	
	Page<HaroldEmoji> findPaginated(Pageable pageable);
}
