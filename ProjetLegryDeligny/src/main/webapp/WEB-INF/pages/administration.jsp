<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
	<jsp:include page="menu.jsp" />
	
	<!-- Corps -->
   		<section>
 
			<h1>Panneau d'administration</h1>			
			
			<article class="valide">
				<span class="message"></span>
			</article>
			<article>
				<h2>Groupe responsables de structure</h2>
				
				<form method="POST" action="ajouterdroit?act=1">
				<table class="tableau">
					<thead>
						<tr>
							<th>Matricule</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Action</th>
						</tr>
					</thead>	
					<tbody id="list">
					<c:forEach var="respStruct" items="${respStruct}">
					<tr>
						<td>${respStruct.id_eleve}</td>
						<td><span class="nom">${respStruct.eleve_nom}</span></td>
						<td><span class="prenom">${respStruct.eleve_prenom}</span></td>
						<td>
							<span class="gestionadministration" data-id="${respStruct.id_eleve}"><input type="button" value="Retirer"/></span>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td><input type="text" name="matricule" id="matricule" required/></td>
						<td><input type="text" name="nom" id="nom" required/></td>
						<td><input type="text" name="prenom" id="prenom" required/></td>
						<td><input type="submit" value="Ajouter"/></td>
					</tr>
					</tbody>
				</table>
				</form>
				
				<h2>Groupe administration du BDE</h2>
				<form method="POST" action="ajouterdroit?act=2">
				<table class="tableau">
					<thead>
						<tr>
							<th>Matricule</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Action</th>
						</tr>
					</thead>	
					<tbody id="list">
					<c:forEach var="adminbde" items="${adminbde}">
					<tr>
						<td>${adminbde.id_eleve}</td>
						<td><span class="nom">${adminbde.eleve_nom}</span></td>
						<td><span class="prenom">${adminbde.eleve_prenom}</span></td>
						<td>
							<span class="gestionadministration" data-id="${adminbde.id_eleve}"><input type="button" value="Retirer"/></span>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td><input type="text" name="matricule" id="matricule" required/></td>
						<td><input type="text" name="nom" id="nom" required/></td>
						<td><input type="text" name="prenom" id="prenom" required/></td>
						<td><input type="submit" value="Ajouter"/></td>
					</tr>
					</tbody>
				</table>
				</form>
				
				<h2>Groupe administration des études</h2>
				<form method="POST" action="ajouterdroit?act=3">
				<table class="tableau">
					<thead>
						<tr>
							<th>Matricule</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Action</th>
						</tr>
					</thead>	
					<tbody id="list">
					<c:forEach var="admin" items="${admin}">
					<tr>
						<td>${admin.id_eleve}</td>
						<td><span class="nom">${admin.eleve_nom}</span></td>
						<td><span class="prenom">${admin.eleve_prenom}</span></td>
						<td>
							<span class="gestionadministration" data-id="${admin.id_eleve}"><input type="button" value="Retirer"/></span>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td><input type="text" name="matricule" id="matricule" required/></td>
						<td><input type="text" name="nom" id="nom" required/></td>
						<td><input type="text" name="prenom" id="prenom" required/></td>
						<td><input type="submit" value="Ajouter"/></td>
					</tr>
					</tbody>
				</table>
				</form>
				
				<h2>Groupe SuperAdmin</h2>
				<form method="POST" action="ajouterdroit?act=4">
				<table class="tableau">
					<thead>
						<tr>
							<th>Matricule</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody id="list">	
					<c:forEach var="superadmin" items="${superadmin}">
					<tr>
						<td>${superadmin.id_eleve}</td>
						<td><span class="nom">${superadmin.eleve_nom}</span></td>
						<td><span class="prenom">${superadmin.eleve_prenom}</span></td>
						<td>
							<span class="gestionadministration" data-id="${superadmin.id_eleve}"><input type="button" value="Retirer"/></span>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td><input type="text" name="matricule" id="matricule" required/></td>
						<td><input type="text" name="nom" id="nom" required/></td>
						<td><input type="text" name="prenom" id="prenom" required/></td>
						<td><input type="submit" value="Ajouter"/></td>
					</tr>
					</tbody>
				</table>
				</form>
				
			</article>
			<article>
				<h2>Gestion des commissions</h2>
				
				<table class="tableau">
					<thead>
						<tr>
							<th>Nom de la commission</th>
							<th>Président</th>
							<th>Action</th>
						</tr>
					</thead>	
					<tbody id="list">
					<c:forEach var="commissions" items="${commissions}">
					<tr>
						<td>${commissions.structure_nom}</td>
						<td>${commissions.structure_president_prenom} ${commissions.structure_president_nom}</td>
						<td><a href="modifiercommission?id=${commissions.cle_structure}"><input type="submit" value="Modifier"/></a></td>
				
					</tr>
					</c:forEach>
					<tr>
						<th colspan="3" style="text-align:center;background-color:rgb(52,73,94);color:rgb(228,229,211)">Ajouter une commission</th>
					</tr>
					<tr>
					<form method="POST" action="ajoutercommission">
						<td>Nom de la commission</td>
						<td><input type="text" name="nom" id="nom" required/></td>
						<td><input type="submit" value="Ajouter"/></td>
					</form>
					</tr>
					</tbody>
				</table>
				
			</article>
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>
    </body>
</html>
