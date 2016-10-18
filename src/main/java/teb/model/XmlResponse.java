package teb.model;

import java.time.ZonedDateTime;
import java.util.UUID;

public class XmlResponse {
	private UUID uuid;
	private ZonedDateTime lastModified;
	private UUID orgUuid;
	private Status status;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(final UUID uuid) {
		this.uuid = uuid;
	}

	public ZonedDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(final ZonedDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public UUID getOrgUuid() {
		return orgUuid;
	}

	public void setOrgUuid(final UUID orgUuid) {
		this.orgUuid = orgUuid;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}
}