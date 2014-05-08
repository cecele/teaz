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
				<form method="POST" action="ajouterdroit?act=1">
				<table class="tableau">
					<thead>
						<tr>
							<th>Matricule</th>
							<th>Nom</th>
							<th>Pr�nom</th>
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
				<form method="POST" action="ajouterdroit?act=2">
				<table class="tableau">
					<thead>
						<tr>
							<th>Matricule</th>
							<th>Nom</th>
							<th>Pr�nom</th>
							<th>Action</th>
						</tr>
					</thead>	
					<c:forEach var="adminbde" items="${adminbde}">
					<tr>
						<td>${adminbde.id_eleve}</td>
						<td>${adminbde.eleve_nom}</td>
						<td>${adminbde.eleve_prenom}</td>
						<td><a href="gestionadministration?id=${adminbde.id_eleve}">Retirer</a></td>
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
				
				<h2>Groupe administration des �tudes</h2>
				<form method="POST" action="ajouterdroit?act=3">
				<table class="tableau">
					<thead>
						<tr>
							<th>Matricule</th>
							<th>Nom</th>
							<th>Pr�nom</th>
							<th>Action</th>
						</tr>
					</thead>	
					<c:forEach var="admin" items="${admin}">
					<tr>
						<td>${admin.id_eleve}</td>
						<td>${admin.eleve_nom}</td>
						<td>${admin.eleve_prenom}</td>
						<td><a href="gestionadministration?id=${admin.id_eleve}">Retirer</a></td>
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
				<form method="POST" action="ajouterdroit?act=4">
				<table class="tableau">
					<thead>
						<tr>
							<th>Matricule</th>
							<th>Nom</th>
							<th>Pr�nom</th>
							<th>Action</th>
						</tr>
					</thead>	
					<c:forEach var="superadmin" items="${superadmin}">
					<tr>
						<td>${superadmin.id_eleve}</td>
						<td>${superadmin.eleve_nom}</td>
						<td>${superadmin.eleve_prenom}</td>
						<td><a href="gestionadministration?id=${superadmin.id_eleve}">Retirer</a></td>
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
			<article>
				<h2>Gestion des commissions</h2>
				<form method="POST" action="ajoutercommission">
				<table class="tableau">
					<thead>
						<tr>
							<th>Nom</th>
							<th>Matricule Pr�sident</th>
							<th>Action</th>
						</tr>
					</thead>	
					<c:forEach var="commissions" items="${commission}">
					<tr>
						<td>${commission.structure_nom}</td>
						<td>${commission.structure_nom}</td>
						<td><a href="supprimercommission?id=${commission.cle_structure}">Retirer</a></td>
					</tr>
					</c:forEach>
					<tr>
						<td><input type="text" name="nom" id="nom" required/></td>
						<td><input type="text" name="id" id="id" required/></td>
						<td><input type="submit" value="Ajouter"/></td>
					</tr>
				</table>
				</form>
			</article>
		</section>	
    </body>
</html>
