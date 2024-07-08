<%@ page language="java" 
	import = "mv.bean.*, mv.servlet.*, mv.util.*, mv.dao.*, java.util.*, java.text.SimpleDateFormat"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Order List</title>
		<link type="text/css" rel="stylesheet" href="css/detail.css">
	</head>

	<body>
		<%@include file="..\fragments\menu.jsp" %>
	
	<%
		currUser = (Utente) session.getAttribute("currentSessionUser");
		if(currUser == null)
		{
			response.sendRedirect("LoginPage.jsp");
			return;
		}
	%>
		<h1 align = "center">Lista degli ordini</h1>
		
		<div class = "travel-table">
		<div class = "scroll-table">
		<table id = "customers">
			<tr>
				<th>ID</th>
				<th>Film</th>
				<th>Data dell' ordine</th>
				<th>Restituito</th>
				<th>Prezzo</th>
			</tr>
		
		<%
			DatabaseManager dbManager = new DatabaseManager();
			FilmDAO fDAO = new FilmDAO(dbManager.getConnection());

			Collection<?> orders = (Collection<?>) request.getAttribute("noleggi");
			
			if(orders != null)
			{
				Iterator<?> it = orders.iterator();
				Noleggio n;
				String nDate;
			
			while(it.hasNext())
			{
				n = (Noleggio) it.next();
				Film f = fDAO.readFilmById(n.getId_f());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				nDate = sdf.format(n.getData());
		%>
			<tr>
				<td><%= "" + n.getId_u() + ", " + n.getId_f() %></td>
				<td><%= f.getTitolo() %></td>
				<td><%= nDate %></td>
				<td><%= n.isRestituito() %></td>
				<td><%= f.getPrezzo() %></td>
			</tr>
		<% 
			}
			}
		%>
		</table>
		</div>
		</div>
		
		<%@ include file="../fragments/footer.jsp" %>
	</body>
</html>