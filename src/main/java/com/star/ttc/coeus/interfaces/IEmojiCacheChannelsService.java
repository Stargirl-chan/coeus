package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.EmojiCacheChannels;

public interface IEmojiCacheChannelsService {
	List<EmojiCacheChannels> findAll();
	
	Page<EmojiCacheChannels> findPaginated(Pageable pageable);
}
