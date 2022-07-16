package com.star.ttc.coeus.models;

import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_emoji_cache")
public class EmojiCache {

	
	@Id
	@Getter
	private BigInteger userId;
	
	@Getter
	@Column(name = "emoji_name", nullable = false)
	private String emojiName;
	
	@Getter
	@Column(name = "emoji_count", nullable = false)
	private BigInteger emojiCount;
	
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmojiCache{");
        sb.append("user_id=").append(userId);
        sb.append(", emoji_name=").append(emojiName);
        sb.append(", emoji_count=").append(emojiCount);
        sb.append('}');
        return sb.toString();
    }
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.userId);
        hash = 79 * hash + Objects.hashCode(this.emojiName);
        hash = 79 * hash + Objects.hashCode(this.emojiCount);
        return hash;
    }
}
