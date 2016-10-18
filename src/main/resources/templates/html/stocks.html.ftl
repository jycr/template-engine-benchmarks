<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>
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
			<#list items as item>
			<tr>
				<td><a href="/stocks/${item.symbol}">${item.symbol}</a></td>
				<td><a href="${item.url}">${item.name}</a></td>
				<td>${item.price?string["0.##"]}</td>
				<td>${item.change?string["0.##"]}</td>
				<td>${item.ratio?string["0.0#"]}</td>
			</tr>
			</#list>
		</tbody>
	</table>

</body>
</html>
