package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.EmojiCache;

public interface IEmojiCacheService {
	List<EmojiCache> findAll();
	
	Page<EmojiCache> findPaginated(Pageable pageable);
}
