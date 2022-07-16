package com.star.ttc.coeus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.star.ttc.coeus.models.EmojiCache;

@Repository
public interface EmojiCacheRepository extends JpaRepository<EmojiCache, Long> {

}