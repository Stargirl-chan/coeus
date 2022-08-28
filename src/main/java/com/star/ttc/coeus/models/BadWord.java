package com.star.ttc.coeus.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ttc_bad_words")
public class BadWord {

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@Column(name = "word", nullable = false)
	private String word;

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BadWord{");
        sb.append("id=").append(id);
        sb.append(", word='").append(word).append('\'');
        sb.append('}');
        return sb.toString();
    }

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.word);
        return hash;
    }
}
