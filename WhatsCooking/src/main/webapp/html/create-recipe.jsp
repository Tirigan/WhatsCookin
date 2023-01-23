<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
 <!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
</head>
<body>
  <h2 class="headline">Create A Recipe</h2>
    
    <%
    	String infoMessage = (String) request.getAttribute("infoMessage");
    	if(infoMessage != null) {
  	%>
  
  	<em><%= infoMessage %></em><br><br>
  
   	<%
  		}
  	%>
  	
  <form id="myRecipeForm" action="create-recipe" method=post>
    <label for=recipe-name>Recipe Name:</label><br>
    <input type=text id=recipe-name name=recipe-name><br>
    <label for=recipe-description>Recipe Description:</label><br>
    <input type=text id=recipe-description name=recipe-description><br>
    <label for=recipe-cooking-style>Cooking Style:</label><br>
    <input type=text id=recipe-cooking-style name=recipe-cooking-style><br>
    <br>
    <!-- INGREDIENTS -->
    <span id="ingredients"></span>
    <script type="text/javascript">
    	var ingCount=1;
		  function addIng() {
			  var ingredients = document.getElementById("ingredients");

			  var heading = document.createElement("h3");
			  heading.innerHTML = "Ingredient # " + ingCount;
			  ingredients.appendChild(heading);

			  
			  // Ingredient name
  			  var ingredientName = "ingredient_name_" + ingCount;
			  var label = document.createElement("label");
			  label.setAttribute("for", ingredientName);
			  label.innerHTML = "Name: "
			  ingredients.appendChild(label);
			  
			  var element = document.createElement("input");
			  element.setAttribute("type", "text");
			  element.setAttribute("name", ingredientName);
			  element.setAttribute("id", ingredientName);
			  ingredients.appendChild(element);
			  
			  var breakpoint = document.createElement("br");
			  ingredients.appendChild(breakpoint);
			  
			  // Ingredient amount
			  var ingredientAmount = "ingredient_amount_" + ingCount;
			  var label = document.createElement("label");
			  label.setAttribute("for", ingredientAmount);
			  label.innerHTML = "Amount: "
			  ingredients.appendChild(label);
			  
			  var element = document.createElement("input");
			  element.setAttribute("type", "text");
			  element.setAttribute("name", ingredientAmount);
			  element.setAttribute("id", ingredientAmount);
			  ingredients.appendChild(element);
			  
			  var breakpoint = document.createElement("br");
			  ingredients.appendChild(breakpoint);
			  
			  // Ingredient Unit
			  var ingredientAmountUnit = "ingredient_unit_" + ingCount;
 			  var label = document.createElement("label");
			  label.setAttribute("for", ingredientAmountUnit);
			  label.innerHTML = "Amount Unit: "
			  ingredients.appendChild(label);
			  
			  var element = document.createElement("input");
			  element.setAttribute("type", "text");
			  element.setAttribute("name", ingredientAmountUnit);
			  element.setAttribute("id", ingredientAmountUnit);
			  ingredients.appendChild(element);
			  
			  var breakpoint = document.createElement("br");
			  ingredients.appendChild(breakpoint);
			  
			  ingCount++;
		  }
	</script>
	<div>
      <input type="button" id="addIngredient" name="addIngredient"
        value="Add Ingredient" onclick="addIng();">
      <br><br>
	</div>
    <!-- INSTRUCTIONS -->
    <span id="instructions"></span>
    <script type="text/javascript">
    	var instCount=1;
		  function addInst() {
			  var instructions = document.getElementById("instructions");

			  var instructionName = "instruction_" + instCount;
			  
			  var label = document.createElement("label");
			  label.setAttribute("for", instructionName);
			  label.innerHTML = "Instruction #" + instCount + ":"
			  instructions.appendChild(label);

			  var element = document.createElement("input");
			  element.setAttribute("type", "text");
			  element.setAttribute("name", instructionName);
			  element.setAttribute("id", instructionName);
			  instructions.appendChild(element);
			  
			  var breakpoint = document.createElement("br");
			  instructions.appendChild(breakpoint);

			  instCount++;
		  }
	</script>
	<div>
      <input type="button" id="addInstruction" name="addInstruction"
        value="Add Instruction" onclick="addInst();">
      <br><br>
	</div>
    
    <input type=submit value="Submit">
    </form>
</body>
</html>