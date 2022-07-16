package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.EmojiCacheMessages;

public interface IEmojiCacheMessagesService {
	List<EmojiCacheMessages> findAll();
	
	Page<EmojiCacheMessages> findPaginated(Pageable pageable);
}
