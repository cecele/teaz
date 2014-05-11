<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
    <div id="layout">
	<jsp:include page="menu.jsp" />
	
	<!-- Corps -->
   		<section>
 
			<h1>Nouvelle annonce</h1>			
	
			<article>
				${message}
				<form method="post" action="deposerannonce?id=${offre.cle_offre}">
				<table>
					<tr>
						<td><label for="date" min="2014-05-06"  >Date</label></td>
						<td><input type="date" name="date" id="date" required value="${offre.date_tea }" /></td>
						<td><label for="hdebut" >Heure de début</label></td>
						<td><input type="number" min="1" max="23" name="hdebut" id="hdebut" value="${offre.heure_debut}" required></td>
						<td><label for="hfin" >Heure de fin</label></td>
						<td><input type="number" min="1" max="23" name="hfin" id="hfin" required value="${offre.heure_fin }"></td>	
					</tr>
					<tr>
						<td><label for="titre" >Titre</label></td>
						<td colspan="5"><input type="text" name="titre" id="titre" required value="${offre.offre_titre}"></td>
					</tr>
					<tr>
						<td><label for="description">Description</label></td>
						<td colspan="5"><textarea name="description" id="description" rows="8" cols="100">${offre.offre_description}</textarea></td>
					</tr>
					<tr>
						<td><label for="nbplaces" >Places</label></td>
						<td colspan="5"><input type="number" min="1" name="nbplaces" id="nbplaces" required value="${offre.offre_place}"></td>
					</tr>
					<tr>
						<td><label for="resp" >Responsable</label></td>
						<td colspan="5"><input type="text" name="resp" id="resp" required value="${offre.eleve_mail}">@hei.fr</td>
					</tr>
					<tr>
						<td colspan="6"><input type="submit" value="Envoyer"/></td>
					</tr>
				</table>
				</form>
				
			</article>
			
			
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>
    </body>
</html>