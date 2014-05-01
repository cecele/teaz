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
							<th>Matricule</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Classe</th>
							<th>Fait n-1</th>
							<th>A faire</th>
							<th>Fait</th>
							<th>Restant</th>
							<th>Justif</th>
						</tr>
					</thead>	
					<c:forEach var="eleves" items="${eleves}">
					<tr>
						<td>${eleves.id_eleve}</td>
						<td>${eleves.eleve_nom}</td>
						<td>${eleves.eleve_prenom}</td>
						<td>H43</td>
						<td>6</td>
						<td>0</td>
						<td>15</td>
						<td>0</td>
						<td><a href="detail">Détail</a></td>
					</tr>
					</c:forEach>
				</table>
			</article>
		</section>	
    </body>
</html>
