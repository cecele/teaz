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
	<jsp:include page="menu.jsp" />
	
	<!-- Corps -->
   		<section>
 
			<h1>Gestion TEA</h1>			
	
			<article>
				<h2>Filtre</h2>
				<form method="POST" action="filtrer">
					<table>
						<tr>
							<td><label for="matricule" >Matricule</label></td>
							<td><input type="text" name="matricule" id="matricule"></td>
						</tr>
						<tr>
							<td><label for="nom" >Nom</label></td>
							<td><input type="text" name="nom" id="nom"></td>
						</tr>
						<tr>
							<td><label for="classe" >Classe</label></td>
							<td>
								<select name="classe" id="classe" required >
									<option value="h1">h1</option>
									<option value="h2">h2</option>
									<option value="h3">h3</option>
									<option value="h4">h4</option>
									<option value="h5">h5</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="Rechercher"/></td>
						</tr>
					</table>
				</form>	
			</article>
			<article>
				<table class="tableau">
					<thead>
						<tr>
							<th colspan="2">Matricule</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Classe</th>
							<th>A faire</th>
							<th>Fait</th>
							<th>En attente</th>
							<th>Restant</th>
							<th>Justif</th>
						</tr>
					</thead>	
					<c:forEach var="eleves" items="${eleves}">
					<tr>
						<td><img src="img/${eleves.teaRestante == 0 ? 'vert' : 'rouge' }.png" alt ="${eleves.teaRestante == 0 ? 'vert' : 'rouge' }"/></td>
						<td>${eleves.id_eleve}</td>
						<td>${eleves.eleve_nom}</td>
						<td>${eleves.eleve_prenom}</td>
						<td>${eleves.classe}</td>
						<td>${eleves.teaAfaire}</td>
						<td>0</td>
						<td>${eleves.teaEnAttente}</td>
						<td>${eleves.teaRestante}</td>
						<td><a href="detail?id=${eleves.id_eleve}">Détail</a></td>
					</tr>
					</c:forEach>
				</table>
			</article>
		</section>	
    </body>
</html>
