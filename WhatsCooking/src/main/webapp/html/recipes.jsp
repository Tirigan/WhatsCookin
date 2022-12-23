<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Logs</title>
  <link rel="stylesheet" href="css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<%@ page import ="java.util.List" %>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="java.util.Iterator" %>
<%@ page import ="beans.Recipe" %>


<div class="container">
  <h2 class="headline">Recipes</h2>
</div>
<ul>
  <%
	  List<Recipe> recipes = (ArrayList) request.getAttribute("recipes");
	  Iterator<Recipe> iterator = recipes.iterator();
	
	  while(iterator.hasNext()) {
		  Recipe recipe = iterator.next();
  %>

  <li class="recipeItem"><%= recipe.getTitle() %></li>

  <%
  		}
  %>
  </ul>
</body>
</html>