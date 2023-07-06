<%@ page import="java.util.Map" %>
<%@ page import="webapp.ApiFetcher" %>

<!DOCTYPE html>
<html lang="pt_BR">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>App do clima</title>
    <link rel="stylesheet" href="styles.css">
    <%@ page import="java.util.Map" %>
    <%@ page import="webapp.ApiFetcher" %>
    <%
        String weatherData = ApiFetcher.fetchApiData();
    %>
  </head>
  <body>
    <p id="weather"><%= weatherData %></p>
  </body>
</html>