<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="mv.bean.*" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
		<title>Catalogo</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link type="text/css" rel="stylesheet" href="css/catalog.css">
</head>
<body>
	<%@ include file="..\fragments\menu.jsp"%>
   <div class = "catalog-container">
		
		<h1 align = "center">Catalogo</h1>
		
	</div>
    <ul>
     	<%
     		Collection<?> films = (Collection<?>) request.getAttribute("films");
     		Utente currentUser = (Utente) session.getAttribute("currentSessionUser");
     		
			if(films != null)
			{
				Iterator<?> it = films.iterator();
				Film f;

				while(it.hasNext())
				{	
            		f = (Film) it.next();
            		if (!f.isDisponibile() && (currentUser == null || !currentUser.isRuolo()))
            		{
            			continue;
            		}
     	%>
            <li>
            	<%
                   
                %>
                <img src="<%=f.getImg() %>" alt="<%=f.getTitolo() %>" width="150px" height="200px">
                <h4><%=f.getTitolo() %></h4>
                <span><%=f.getGenere() %></span>
                <p><%=f.getDescrizione() %></p>
                <span><%=f.getPrezzo() %></span> 
                <%
                    if (currentUser != null )
                    {
                    	Collection<?> film_nol = (Collection<?>) request.getAttribute("filmNoleggiati");
                    	if(film_nol == null || (film_nol != null && !film_nol.contains(f.getId_f())))
                    	{
                %>
                <form action="<%=request.getContextPath() + "/noleggia" %>" method="POST">
                    <input type="hidden" id="id_f" name="id_f" value="<%=f.getId_f()%>">
                    <input type="hidden" id="id_u" name="id_u" value="<%=currentUser.getId_u()%>">
                    <button type="submit">Noleggia</button>
                </form>  
                <%
                    	}
                		else
                		{
                %>
                <form action="<%=request.getContextPath() + "/restituisci" %>" method="POST">
                	<input type="hidden" id="id_f" name="id_f" value="<%=f.getId_f()%>">
                    <input type="hidden" id="id_u" name="id_u" value="<%=currentUser.getId_u()%>">
                    <button type="submit" >Restituisci</button>
                </form>  
                <%			
                		}
                    }
                    else
                    {
                    	
                %>
                <form action="<%=request.getContextPath() + "/login.jsp" %>" method="POST">
                    <button type="submit">Noleggia</button>
                </form> 
                <% 
                    }
                    if (currentUser != null && currentUser.isRuolo()) {
                %>
				<span>
					<%=f.isDisponibile() ? "Disponibile" : "Non Disponibile"%>
				</span>
                <form action="<%=request.getContextPath() + "/elimina" %>" method="POST">
                    <input type="hidden" id="id" name="id" value="<%=f.getId_f() %>">
                    <button type="submit">Elimina</button>
                </form>
				 <form action="<%=request.getContextPath() + "/update.jsp" %>" method="POST">
                    <input type="hidden" id="id" name="id" value="<%=f.getId_f() %>">
                    <button type="submit">Aggiorna</button>
                </form>		
                <%
                	}
                }
     		}
                %>
             
            </li>
    </ul>
    <%
		if (currentUser != null && currentUser.isRuolo()) {
	%>
	
    	<form action="<%=request.getContextPath() + "/aggiungiFilm.jsp"%>" method="post">
			<button type="submit">Aggiungi Film</button>
		</form>
		
	<%
		}
	%>
	<%@ include file="..\fragments\footer.jsp"%>
</body>
</html>