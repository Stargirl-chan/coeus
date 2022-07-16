package com.star.ttc.coeus.models;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_message_cache")
public class MessageCache {

	@Id
	@Getter
	private int id;
	
	@Getter
	@Column(name = "message_id", nullable = false)
	private BigInteger messageId;
	
	@Getter
	@Column(name = "channel_id", nullable = false)
	private BigInteger channelId;
	
	@Getter
	@Column(name = "user_id", nullable = false)
	private BigInteger userId;
	
	@Getter
	@Column(name = "message_time", nullable = false)
	private OffsetDateTime messageTime;
	
	@Getter
	@Column(name = "content", nullable = false)
	private String content;
	
	@Getter
	@Column(name = "attachments", nullable = false)
	private String attachments;
	
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageCache{");
        sb.append("id=").append(id);
        sb.append(", message_id=").append(messageId);
        sb.append(", channel_id=").append(channelId);
        sb.append(", user_id=").append(userId);
        sb.append(", message_time=").append(messageTime);
        sb.append(", content=").append(content);
        sb.append(", attachments=").append(attachments);
        sb.append('}');
        return sb.toString();
    }
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.messageId);
        hash = 79 * hash + Objects.hashCode(this.channelId);
        hash = 79 * hash + Objects.hashCode(this.channelId);
        hash = 79 * hash + Objects.hashCode(this.channelId);
        hash = 79 * hash + Objects.hashCode(this.channelId);
        hash = 79 * hash + Objects.hashCode(this.channelId);
        hash = 79 * hash + Objects.hashCode(this.channelId);
        return hash;
    }
}
