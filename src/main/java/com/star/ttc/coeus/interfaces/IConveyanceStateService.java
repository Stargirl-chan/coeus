package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.ConveyanceState;

public interface IConveyanceStateService {
	List<ConveyanceState> findAll();
	
	Page<ConveyanceState> findPaginated(Pageable pageable);
}
