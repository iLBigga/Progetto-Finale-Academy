<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Aggiungi Film</title>
<link type = "text/css" rel="stylesheet" href="css/signup.css">
</head>
<body>
	<%@ include file="..\fragments\menu.jsp"%>

		<form action = "<%=request.getContextPath() + "/aggiungi"%>" method = "post" id="signupForm" name = "registration">
			<div class="signupContainer">
    			<p>Compila i campi del form per aggiungere una destinazione.</p>
    
    			<hr>

				<label for="city"><b>Locandina:</b></label>&nbsp; &nbsp;<span id = "cityspan" style = "color:red;"></span>
    			<input class = "valid" type="text" placeholder="Inserire nome locandina" name="img">    		

    			<label for="region"><b>Titolo:</b></label>&nbsp; &nbsp;<span id = "regspan" style = "color:red;"></span>
    			<input class = "valid" type="text" placeholder="Inserire titolo film" name="titolo">
    			
    			<label for="nation"><b>Genere:</b></label>&nbsp; &nbsp;<span id = "nationspan" style = "color:red;"></span>
    			<input class = "valid" type="text" placeholder="Inserire genere" name="genere">
    			
    			<label for="numPhoto"><b>Prezzo:</b></label>&nbsp; &nbsp;<span id = "numspan" style = "color:red;"></span>
    			<input class = "valid" type="text" name="prezzo" placeholder = "Inserire il prezzo">
    			
    			<label for = "description">Descrizione:</label>&nbsp; &nbsp;<span id = "descspan" style = "color:red;"></span>
				<textarea class = "valid" name = "descrizione" id = "description" placeholder = "Inserire descrizione(max 3000 caratteri)"></textarea><br><br>
    			
    			<label for="continent"><b>Disponibilità:</b></label>&nbsp; &nbsp;<span id = "conspan" style = "color:red;"></span>
    			<select class="dropdown" name="disp" id="continent">
                <option value="true">Disponibile</option>
                <option value="false">Non Disponibile</option>
            	</select>
    			
    			<div class="clearfix">
    				<button type="submit" class="signupbtn">Inserisci</button>
      				<button type="reset" class="cancelbtn">Cancella</button>
    			</div>
  			</div>
		</form>
		<%@ include file="..\fragments\footer.jsp"%>
</body>
</html>