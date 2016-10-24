<?xml version="1.0" encoding="UTF-8"?>
<doc>
<jsp:useBean id="xmlResponse" class="java.lang.Object" scope="request" />
<% com.mitchellbosecke.benchmark.model.XmlResponse resp = (com.mitchellbosecke.benchmark.model.XmlResponse)xmlResponse; %>
	<header>
		<uuid><%=resp.getUuid()%></uuid>
		<lastModified><%=resp.getLastModified()%></lastModified>
	</header>
	<org-info>
		<org-uuid><%=resp.getOrgUuid()%></org-uuid>
	</org-info>
	<info-status>
		<status-code><%=resp.getStatus() %></status-code>
	</info-status>
</doc>