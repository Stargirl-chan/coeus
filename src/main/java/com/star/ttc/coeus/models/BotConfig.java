package com.star.ttc.coeus.models;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_config")
public class BotConfig {

	@Id
	@Getter
	@GeneratedValue
	private int id;

	@Getter
	@Column(name = "support_channel", nullable = false)
	private BigInteger supportChannel;

	@Getter
	@ElementCollection(targetClass = BigInteger.class)
	@Column(name = "conveyance_channels", nullable = false)
	private List<BigInteger> conveyanceChannel;

	@Getter
	@ElementCollection(targetClass = BigInteger.class)
	@Column(name = "conveyance_blacklisted_channels", nullable = false)
	private List<BigInteger> conveyanceBlacklistChannel;

	@Getter
	@Column(name = "welcome_channel", nullable = false)
	private BigInteger welcomeChannel;

	@Getter
	@Column(name = "verified_role", nullable = false)
	private BigInteger verifiedRole;

	@Getter
	@Column(name = "moderator_role", nullable = false)
	private BigInteger moderatorRole;

	@Getter
	@ElementCollection(targetClass = String.class)
	@Column(name = "welcome_messages", nullable = false)
	private List<String> welcomeMessage;

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BotConfig{");
        sb.append("id=").append(id);
        sb.append(", support_channel=").append(supportChannel).append('\'');
        sb.append(", conveyance_channels='").append(conveyanceChannel).append('\'');
        sb.append(", conveyance_blacklisted_channels='").append(conveyanceBlacklistChannel).append('\'');
        sb.append(", welcome_channel='").append(welcomeChannel).append('\'');
        sb.append(", verified_role='").append(verifiedRole).append('\'');
        sb.append(", moderator_role='").append(moderatorRole).append('\'');
        sb.append(", welcome_messages='").append(welcomeMessage).append('\'');
        sb.append('}');
        return sb.toString();
    }

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.supportChannel);
        hash = 79 * hash + Objects.hashCode(this.conveyanceChannel);
        hash = 79 * hash + Objects.hashCode(this.conveyanceBlacklistChannel);
        hash = 79 * hash + Objects.hashCode(this.welcomeChannel);
        hash = 79 * hash + Objects.hashCode(this.verifiedRole);
        hash = 79 * hash + Objects.hashCode(this.moderatorRole);
        hash = 79 * hash + Objects.hashCode(this.welcomeMessage);
        return hash;
    }
}
