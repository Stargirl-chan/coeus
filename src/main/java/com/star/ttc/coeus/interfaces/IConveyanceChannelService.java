package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.ConveyanceChannel;

public interface IConveyanceChannelService {
	List<ConveyanceChannel> findAll();
	
	Page<ConveyanceChannel> findPaginated(Pageable pageable);
}
