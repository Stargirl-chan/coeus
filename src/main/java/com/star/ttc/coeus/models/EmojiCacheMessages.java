package com.star.ttc.coeus.models;

import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_emoji_cache_messages")
public class EmojiCacheMessages {


	@Id
	@Getter
	private BigInteger userId;

	@Getter
	@Column(name = "num_messages", nullable = false)
	private BigInteger numMessages;

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmojiCacheMessages{");
        sb.append("user_id=").append(userId);
        sb.append(", num_messages=").append(numMessages);
        sb.append('}');
        return sb.toString();
    }

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.userId);
        hash = 79 * hash + Objects.hashCode(this.numMessages);
        return hash;
    }
}
