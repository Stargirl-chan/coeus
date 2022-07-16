package com.star.ttc.coeus.models;

import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_conveyance_channel")
public class ConveyanceChannel {

	@Id
	@Getter
	@GeneratedValue
	private int id;
	
	@Getter
	@Column(name = "channel_id", nullable = false)
	private BigInteger channelId;
	
	
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConveyanceChannel{");
        sb.append("id=").append(id);
        sb.append(", channel_id=").append(channelId);
        sb.append('}');
        return sb.toString();
    }
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.channelId);
        return hash;
    }
}
