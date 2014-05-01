<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
   	<head>
  	<meta http-equiv="Content-Type" content="text/html;
			 charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<script type="text/javascript" src="js/menu.js"></script>
        <title>Tea</title>
    </head>
    <body>
    <!-- Menu -->
	<jsp:include page="menu.jsp" />
	
	<!-- Corps -->
   		<section>
 
			<h1>Nouvelle annonce</h1>			
	
			<article>
			
				<form method="post" action="deposerannonce">
				<table>
					<tr>
						<td><label for="date" >Date</label></td>
						<td><input type="date" name="date" id="date" required></td>
						<td><label for="hdebut" >Heure de début</label></td>
						<td><input type="time" name="hdebut" id="hdebut" required></td>
						<td><label for="hfin" >Heure de fin</label></td>
						<td><input type="time" name="hfin" id="hfin" required></td>	
					</tr>
					<tr>
						<td><label for="titre" >Titre</label></td>
						<td colspan="5"><input type="text" name="titre" id="titre" required></td>
					</tr>
					<tr>
						<td><label for="description">Description</label></td>
						<td colspan="5"><textarea name="description" id="description" rows="8" cols="125"></textarea></td>
					</tr>
					<tr>
						<td><label for="nbplaces" >Places</label></td>
						<td colspan="5"><input type="number" value="1" min="1" name="nbplaces" id="nbplaces" required></td>
					</tr>
					<tr>
						<td><label for="resp" >Responsable</label></td>
						<td colspan="5"><input type="text" name="resp" id="resp" required>@hei.fr</td>
					</tr>
					<tr>
						<td colspan="6"><input type="submit" value="Envoyer"/></td>
					</tr>
				</table>
				</form>
				
			</article>
			
			
		</section>	
    </body>
</html>