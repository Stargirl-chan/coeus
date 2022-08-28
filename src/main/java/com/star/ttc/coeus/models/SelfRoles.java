package com.star.ttc.coeus.models;

import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_selfroles")
public class SelfRoles {


	@Id
	@Getter
	private int id;

	@Getter
	@Column(name = "role_id", nullable = false)
	private BigInteger roleId;

	@Getter
	@Column(name = "emoji_name", nullable = false)
	private String emojiName;

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SelfRoles{");
        sb.append("id=").append(id);
        sb.append(", role_id=").append(roleId);
        sb.append(", emoji_name=").append(emojiName);
        sb.append('}');
        return sb.toString();
    }

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.roleId);
        hash = 79 * hash + Objects.hashCode(this.emojiName);
        return hash;
    }
}
