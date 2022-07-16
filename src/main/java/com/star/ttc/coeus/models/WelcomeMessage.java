package com.star.ttc.coeus.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_welcome_message")
public class WelcomeMessage {

	
	@Id
	@Getter
	private int id;
	
	@Getter
	@Column(name = "welcome_message", nullable = false)
	private String welcomeMessage;
	
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WelcomeMessage{");
        sb.append("id=").append(id);
        sb.append(", welcome_message=").append(welcomeMessage);
        sb.append('}');
        return sb.toString();
    }
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.welcomeMessage);
        return hash;
    }
}
