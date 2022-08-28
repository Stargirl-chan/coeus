package com.star.ttc.coeus.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.ConveyanceChannel;

public interface IConveyanceChannelService {
	List<ConveyanceChannel> findAll();

	Page<Map<String, Object>> findPaginated(Pageable pageable);
}
