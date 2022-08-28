package com.star.ttc.coeus.models;

import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_webhooks")
public class Webhooks {


	@Id
	@Getter
	private BigInteger channelId;

	@Getter
	@Column(name = "webhook_url", nullable = false)
	private String webhookUrl;

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wenhooks{");
        sb.append("channel_id=").append(channelId);
        sb.append(", webhook=").append(webhookUrl);
        sb.append('}');
        return sb.toString();
    }

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.channelId);
        hash = 79 * hash + Objects.hashCode(this.webhookUrl);
        return hash;
    }
}
