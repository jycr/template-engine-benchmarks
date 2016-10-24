<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:regexp="http://exslt.org/regular-expressions"
	xmlns:str="http://exslt.org/strings" xmlns:twc="http://twc.com/2009/01/ivr/framework"
	exclude-result-prefixes="twc regexp str" extension-element-prefixes="str">
	<xsl:output method="xml" encoding="UTF-8" indent="yes" />
	<xsl:template match="/">
		<doc>
			<xsl:apply-templates />
		</doc>
	</xsl:template>
	<xsl:template match="xmlResponse">
		<header>
			<uuid>
				<xsl:value-of select="uuid" />
			</uuid>
			<lastModified>
				<xsl:value-of select="lastModified" />
			</lastModified>
		</header>
		<org-info>
			<org-uuid>
				<xsl:value-of select="orgUuid" />
			</org-uuid>
		</org-info>
		<info-status>
			<status-code>
				<xsl:value-of select="status" />
			</status-code>
		</info-status>
	</xsl:template>
</xsl:stylesheet>