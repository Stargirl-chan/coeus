package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.ConveyanceBlacklistChannel;

public interface IConveyanceBlacklistChannelService {
	List<ConveyanceBlacklistChannel> findAll();
	
	Page<ConveyanceBlacklistChannel> findPaginated(Pageable pageable);
}
