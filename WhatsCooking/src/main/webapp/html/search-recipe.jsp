<!doctype html>

<html lang="en">

<head>

  <meta charset="UTF-8">

  <title>What's Cooking?</title>

  <link rel="stylesheet" href="css/style.css">

  <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body>
<%@ page import ="java.util.List" %>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="java.util.Iterator" %>
<%@ page import ="beans.Recipe" %>
<%@ page import ="beans.Ingredient" %>
<%@ page import ="beans.Instruction" %>


<div class="container">
  <h2 class="headline">Search A Recipe</h2>
    <%
    	String infoMessage = (String) request.getAttribute("infoMessage");
    	if(infoMessage != null) {
  	%>
  
  <em><%= infoMessage %></em>
  
   	<%
  		}
  	%>
  
  <form action="search-recipe" method="post">
    <label for="ing1">Ingredient 1:</label><br>
	<input type="text" id="ing1" name="ing1"><br>
	
    <label for="ing2">Ingredient 2:</label><br>
	<input type="text" id="ing2" name="ing2"><br>
	
	
    <label for="ing3">Ingredient 3:</label><br>
	<input type="text" id="ing3" name="ing3"><br>
	
    <label for="cooking_style">Cooking Style:</label><br>
	<input type="text" id="cooking_style" name="cooking_style"><br>
	
	<br>
	<input type="submit" value="Search Recipe">
  </form>
  
  
    <%
    	Recipe recipe = (Recipe) request.getAttribute("recipe");
	
    	if(recipe != null) {
  	%>
  
    <!-- Save Recipe Form -->
    <form action="search-recipe" method=post>
  	<input type=hidden id=saveRecipe name=saveRecipe value="yes"><br>
    <input type=hidden id="recipe_id" name="recipe_id" value="<%= recipe.getId() %>">
    <input type=hidden id="user_id" name="user_id" value="<%= session.getAttribute("user_id") %>">
    <input type=submit value="Save Recipe">
    </form>
    
    <!-- Create Recipe Form -->    
    <form action="search-recipe" method=post>
    <input type=hidden id=goToCreateRecipe name=goToCreateRecipe value="yes"><br>
    <input type=submit value="Create Recipe">
    </form>   
  

  			<h2><%= recipe.getTitle() %></h2>
 			<h4>Description: </h4>
	  		<p><%= recipe.getDescription() %></p>
	  		<h4>Cooking Style: </h4>
	  		<p><%= recipe.getCookingStyle() %></p>
 			<h4>Ingredients: </h4>
			<ul>
			  <%
			  List<Ingredient> ingredients = recipe.getIngredients();
			  Iterator<Ingredient> ingredientsIterator = ingredients.iterator();
			
			  while(ingredientsIterator.hasNext()) {
				  Ingredient ingredient = ingredientsIterator.next();
			  %>
			  	<li class="ingredientItem"><%= ingredient.getName() %> - <%= ingredient.getAmount() %> <%= ingredient.getAmountUnit() %> </li>
			  <%
		  		}
			  %>
			</ul>
			
			<h4>Instructions: </h4>
			<ol>
			  <%
			  List<Instruction> instructions = recipe.getInstructions();
			  Iterator<Instruction> instructionsIterator = instructions.iterator();
			
			  while(instructionsIterator.hasNext()) {
				  Instruction instruction = instructionsIterator.next();
			  %>
			  	<li class="instructionItem"><%= instruction.getValue() %></li>
			  <%
		  		}
			  %>
			</ol>
  	<%
  		}
  	%>
  
</div>

</body>
</html>