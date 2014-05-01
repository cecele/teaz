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
 
			<h1>Panneau d'administration</h1>			
	
			<article>
				<h2>Groupe administration des études</h2>
				<form method="POST" action="administration">
				<table class="tableau">
					<thead>
						<tr>
							<th>Maaaatricule</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Action</th>
						</tr>
					</thead>	
					<tr>
						<td>11105</td>
						<td>Deligny</td>
						<td>Martin</td>
						<td>Retirer</td>
					</tr>
					<tr>
						<td><input type="text" name="matricule" id="matricule" required/></td>
						<td><input type="text" name="nom" id="nom" required/></td>
						<td><input type="text" name="prenom" id="prenom" required/></td>
						<td><input type="submit" value="Ajouter"/></td>
					</tr>
				</table>
				</form>
				
				<h2>Groupe administration des études</h2>
				<form method="POST" action="administration">
				<table class="tableau">
					<thead>
						<tr>
							<th>Matricule</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Action</th>
						</tr>
					</thead>	
					<tr>
						<td>11105</td>
						<td>Deligny</td>
						<td>Martin</td>
						<td>Retirer</td>
					</tr>
					<tr>
						<td><input type="text" name="matricule" id="matricule" required/></td>
						<td><input type="text" name="nom" id="nom" required/></td>
						<td><input type="text" name="prenom" id="prenom" required/></td>
						<td><input type="submit" value="Ajouter"/></td>
					</tr>
				</table>
				</form>
			</article>
		</section>	
    </body>
</html>
