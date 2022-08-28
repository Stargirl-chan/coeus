package com.star.ttc.coeus.models;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ttc_support_tickets")
public class SupportTicket {

	@Id
	@Getter
	@GeneratedValue
	private int incidentId;

	@Getter
	@Column(name = "thread_id", nullable = false)
	private BigInteger threadId;

	@Getter
	@Column(name = "user_id", nullable = false)
	private BigInteger userId;

	@Getter
	@Column(name = "incident_time", nullable = false)
	private OffsetDateTime incidentTime;

	@Getter
	@Column(name = "incident_title", nullable = false)
	private String incidentTitle;

	@Getter
	@Column(name = "incident_solved", nullable = false)
	private boolean incidentSolved;

	@Getter
	@Column(name = "unarchivals", nullable = false)
	private short unarchivals;

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SupportTicket{");
        sb.append("id=").append(incidentId);
        sb.append(", thread_id='").append(threadId).append('\'');
        sb.append(", user_id=").append(userId);
        sb.append(", incident_time=").append(incidentTime);
        sb.append(", incident_title=").append(incidentTitle);
        sb.append(", incident_solved=").append(incidentSolved);
        sb.append(", unarchivals=").append(unarchivals);
        sb.append('}');
        return sb.toString();
    }

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.incidentId);
        hash = 79 * hash + Objects.hashCode(this.threadId);
        hash = 79 * hash + Objects.hashCode(this.userId);
        hash = 79 * hash + Objects.hashCode(this.incidentTime);
        hash = 79 * hash + Objects.hashCode(this.incidentTitle);
        hash = 79 * hash + Objects.hashCode(this.incidentSolved);
        hash = 79 * hash + Objects.hashCode(this.unarchivals);
        return hash;
    }
}
