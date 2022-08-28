package com.star.ttc.coeus.models;

import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_config_view")
public class BotConfigView {

	@Id
	@Getter
	private Long configId;

	@Column(name = "config_properties_id", nullable = true)
	@Getter
	private int configPropertiesId;

	@Getter
	@Column(name = "support_channel", nullable = true)
	private BigInteger supportChannel;

	@Getter
	@Column(name = "conveyance_channel", nullable = true)
	private BigInteger conveyanceChannel;

	@Getter
	@Column(name = "conveyance_blacklist_channel", nullable = true)
	private BigInteger conveyanceBlacklistChannel;

	@Getter
	@Column(name = "welcome_channel", nullable = true)
	private BigInteger welcomeChannel;

	@Getter
	@Column(name = "verified_role", nullable = true)
	private BigInteger verifiedRole;

	@Getter
	@Column(name = "moderator_role", nullable = true)
	private BigInteger moderatorRole;

	@Getter
	@Column(name = "welcome_message", nullable = true)
	private String welcomeMessage;

	@Getter
	@Column(name = "harold_emoji", nullable = true)
	private String haroldEmoji;


	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BotConfigView{");
        sb.append("config_id=").append(configId);
        sb.append(", onfig_properties_id=").append(configPropertiesId).append('\'');
        sb.append(", support_channel=").append(supportChannel).append('\'');
        sb.append(", conveyance_channels='").append(conveyanceChannel).append('\'');
        sb.append(", conveyance_blacklisted_channels='").append(conveyanceBlacklistChannel).append('\'');
        sb.append(", welcome_channel='").append(welcomeChannel).append('\'');
        sb.append(", verified_role='").append(verifiedRole).append('\'');
        sb.append(", moderator_role='").append(moderatorRole).append('\'');
        sb.append(", welcome_messages='").append(welcomeMessage).append('\'');
        sb.append(", harold_emoji='").append(haroldEmoji).append('\'');
        sb.append('}');
        return sb.toString();
    }

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.configId);
        hash = 79 * hash + Objects.hashCode(this.configPropertiesId);
        hash = 79 * hash + Objects.hashCode(this.supportChannel);
        hash = 79 * hash + Objects.hashCode(this.conveyanceChannel);
        hash = 79 * hash + Objects.hashCode(this.conveyanceBlacklistChannel);
        hash = 79 * hash + Objects.hashCode(this.welcomeChannel);
        hash = 79 * hash + Objects.hashCode(this.verifiedRole);
        hash = 79 * hash + Objects.hashCode(this.moderatorRole);
        hash = 79 * hash + Objects.hashCode(this.welcomeMessage);
        hash = 79 * hash + Objects.hashCode(this.haroldEmoji);
        return hash;
    }
}
