package com.star.ttc.coeus.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_conveyance_state")
public class ConveyanceState {

	@Id
	@Getter
	private int id;
	
	@Getter
	@Column(name = "current_message_id", nullable = false)
	private int currentMessageId;
	
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConveyanceState{");
        sb.append("id=").append(id);
        sb.append(", role_id=").append(currentMessageId);
        sb.append('}');
        return sb.toString();
    }
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.currentMessageId);
        return hash;
    }
}
