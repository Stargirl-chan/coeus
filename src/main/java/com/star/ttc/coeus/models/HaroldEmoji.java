package com.star.ttc.coeus.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_harold_emoji")
public class HaroldEmoji {

	
	@Id
	@Getter
	private int id;
	
	@Getter
	@Column(name = "name", nullable = false)
	private String name;
	
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HaroldEmoji{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }
}
