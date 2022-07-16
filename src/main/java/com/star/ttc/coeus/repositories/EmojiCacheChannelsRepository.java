package com.star.ttc.coeus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.star.ttc.coeus.models.EmojiCacheChannels;

@Repository
public interface EmojiCacheChannelsRepository extends JpaRepository<EmojiCacheChannels, Long> {

}