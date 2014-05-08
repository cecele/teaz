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
 
			<h1>Panneau d'administration</h1>			
			
	
			<article>
				<h2>Groupe responsables de structure</h2>
				<form method="POST" action="gestionadministration">
				<table class="tableau">
					<thead>
						<tr>
							<th>Matricule</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Action</th>
						</tr>
					</thead>	
					<c:forEach var="respStruct" items="${respStruct}">
					<tr>
						<td>${respStruct.id_eleve}</td>
						<td>${respStruct.eleve_nom}</td>
						<td>${respStruct.eleve_prenom}</td>
						<td><a href="gestionadministration?id=${respStruct.id_eleve}">Retirer</a></td>
					</tr>
					</c:forEach>
					<tr>
						<td><input type="text" name="matricule" id="matricule" required/></td>
						<td><input type="text" name="nom" id="nom" required/></td>
						<td><input type="text" name="prenom" id="prenom" required/></td>
						<td><input type="submit" value="Ajouter"/></td>
					</tr>
				</table>
				</form>
				
				<h2>Groupe administration du BDE</h2>
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
					<c:forEach var="adminbde" items="${adminbde}">
					<tr>
						<td>${adminbde.id_eleve}</td>
						<td>${adminbde.eleve_nom}</td>
						<td>${adminbde.eleve_prenom}</td>
						<td>Retirer</td>
					</tr>
					</c:forEach>
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
					<c:forEach var="admin" items="${admin}">
					<tr>
						<td>${admin.id_eleve}</td>
						<td>${admin.eleve_nom}</td>
						<td>${admin.eleve_prenom}</td>
						<td>Retirer</td>
					</tr>
					</c:forEach>
					<tr>
						<td><input type="text" name="matricule" id="matricule" required/></td>
						<td><input type="text" name="nom" id="nom" required/></td>
						<td><input type="text" name="prenom" id="prenom" required/></td>
						<td><input type="submit" value="Ajouter"/></td>
					</tr>
				</table>
				</form>
				
				<h2>Groupe SuperAdmin</h2>
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
					<c:forEach var="superadmin" items="${superadmin}">
					<tr>
						<td>${superadmin.id_eleve}</td>
						<td>${superadmin.eleve_nom}</td>
						<td>${superadmin.eleve_prenom}</td>
						<td>Retirer</td>
					</tr>
					</c:forEach>
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
