package com.mitchellbosecke.benchmark.model;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

public class XmlResponse {
	public static final XmlResponse dummyXmlResponse = new XmlResponse();
	static {
		dummyXmlResponse.setUuid(UUID.fromString("8bb080f3-d384-49a8-b372-7ddffb8c5b33"));
		dummyXmlResponse.setOrgUuid(UUID.fromString("89697e5a-15b4-4d32-b37f-ad17f6ae0fdf"));
		dummyXmlResponse.setLastModified(ZonedDateTime.of(2016, 10, 18, 22, 28, 33, 826000000, ZoneId.from(ZoneOffset.ofHoursMinutes(1, 0))));
		dummyXmlResponse.setStatus(Status.OK);

	}
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