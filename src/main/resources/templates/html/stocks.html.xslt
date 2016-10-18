<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:regexp="http://exslt.org/regular-expressions"
	xmlns:str="http://exslt.org/strings" xmlns:twc="http://twc.com/2009/01/ivr/framework"
	exclude-result-prefixes="twc regexp str" extension-element-prefixes="str">
	<xsl:output method="xml" encoding="UTF-8" indent="yes" />
	<xsl:template match="/">
		<xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;</xsl:text>
		<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
				<title>Stock Prices</title>
			</head>
			<body>
				<h1>Stock Prices</h1>
				<table>
					<thead>
						<tr>
							<th>symbol</th>
							<th>name</th>
							<th>price</th>
							<th>change</th>
							<th>ratio</th>
						</tr>
					</thead>
					<tbody>
						<xsl:apply-templates />
					</tbody>
				</table>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="item" xmlns="http://www.w3.org/1999/xhtml">
		<tr>
			<td>
				<a href="/stocks/{symbol}">
					<xsl:value-of select="symbol" />
				</a>
			</td>
			<td>
				<a href="{url}">
					<xsl:value-of select="name" />
				</a>
			</td>
			<td>
				<xsl:value-of select="price" />
			</td>
			<td>
				<xsl:value-of select="change" />
			</td>
			<td>
				<xsl:value-of select="ratio" />
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>