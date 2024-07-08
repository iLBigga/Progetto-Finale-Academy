<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="mv.util.*" import ="mv.dao.*" import="mv.bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Aggiorna Film</title>
<link type = "text/css" rel="stylesheet" href="css/signup.css">
</head>
<body>
	<%@ include file="..\fragments\menu.jsp"%>
	<%
		DatabaseManager dm = new DatabaseManager();
		FilmDAO fDAO = new FilmDAO(dm.getConnection());
		int id = Integer.parseInt(request.getParameter("id"));
		Film f = fDAO.readFilmById(id);
		String img = f.getImg().substring(4);
		img = img.substring(0, img.length() - 4);
	%>
		<form action = "<%=request.getContextPath() + "/update"%>" method = "post" id="signupForm" name = "registration">
			<div class="signupContainer">
    			<p>Compila i campi del form per aggiungere una destinazione.</p>
    
    			<hr>
				<input type="hidden" id="id" name="id" value="<%= f.getId_f() %>">
				<label for="city"><b>Locandina:</b></label>&nbsp; &nbsp;<span id = "cityspan" style = "color:red;"></span>
    			<input class = "valid" type="text" name="img" value="<%= img %>">    		

    			<label for="region"><b>Titolo:</b></label>&nbsp; &nbsp;<span id = "regspan" style = "color:red;"></span>
    			<input class = "valid" type="text" value="<%= f.getTitolo() %>" name="titolo">
    			
    			<label for="nation"><b>Genere:</b></label>&nbsp; &nbsp;<span id = "nationspan" style = "color:red;"></span>
    			<input class = "valid" type="text" value="<%= f.getGenere() %>" name="genere">
    			
    			<label for="numPhoto"><b>Prezzo:</b></label>&nbsp; &nbsp;<span id = "numspan" style = "color:red;"></span>
    			<input class = "valid" type="text" name="prezzo" value="<%= f.getPrezzo() %>">
    			
    			<label for = "description">Descrizione:</label>&nbsp; &nbsp;<span id = "descspan" style = "color:red;"></span>
				<textarea class = "valid" name = "descrizione" id = "description" ><%= f.getDescrizione() %></textarea><br><br>
    			
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