package com.star.ttc.coeus.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_easter_egg_gifs")
public class EasterEggGifs {

	
	@Id
	@Getter
	private int id;
	
	@Getter
	@Column(name = "content", nullable = false)
	private String content;
	
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EasterEggGifs{");
        sb.append("id=").append(id);
        sb.append(", content=").append(content);
        sb.append('}');
        return sb.toString();
    }
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.content);
        return hash;
    }
}
