package com.star.ttc.coeus.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.star.ttc.coeus.models.WelcomeMessage;

public interface IWelcomeMessageService {
	List<WelcomeMessage> findAll();
	
	Page<WelcomeMessage> findPaginated(Pageable pageable);
}
