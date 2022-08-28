package com.star.ttc.coeus.models;

import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_emoji_cache_channels")
public class EmojiCacheChannels {


	@Id
	@Getter
	private BigInteger channelId;

	@Getter
	@Column(name = "message_id", nullable = false)
	private BigInteger messageId;

	@Getter
	@Column(name = "timestamp_unix", nullable = false)
	private BigInteger unixTimestamp;

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmojiCacheChannels{");
        sb.append("channel_id=").append(channelId);
        sb.append(", message_id=").append(messageId);
        sb.append(", timestamp_unix=").append(unixTimestamp);
        sb.append('}');
        return sb.toString();
    }

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.channelId);
        hash = 79 * hash + Objects.hashCode(this.messageId);
        hash = 79 * hash + Objects.hashCode(this.unixTimestamp);
        return hash;
    }
}
