<?xml version="1.0" encoding="UTF-8"?>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en" xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions" xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Stock Prices</title>
</head>
<body>
  <h1>Stock Prices</h1>
  <table>
   <thead>
    <tr><th>symbol</th><th>name</th><th>price</th><th>change</th><th>ratio</th></tr>
   </thead>
   <tbody>
	<jsp:useBean id="items" class="java.util.ArrayList" scope="request" />
<%for(com.mitchellbosecke.benchmark.model.Stock item: (java.util.ArrayList<com.mitchellbosecke.benchmark.model.Stock>)items){%>
    <tr>
     <td><a href="/stocks/<%= item.getSymbol() %>"><%= item.getSymbol() %></a></td>
     <td><a href="<%= item.getUrl() %>"><%= item.getName() %></a></td>
     <td><%= item.getPrice() %></td>
     <td><%= item.getChange() %></td>
     <td><%= item.getRatio() %></td>
    </tr>
<%}%>
   </tbody>
  </table>
 </body>
</html>