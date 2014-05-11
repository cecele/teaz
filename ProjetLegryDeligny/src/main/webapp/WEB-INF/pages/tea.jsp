<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
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
 
			<h1>${sessionScope.sessionEleve.eleve_prenom} ${sessionScope.sessionEleve.eleve_nom}</h1>	
			<article>
				<h2>Fiche</h2>
				Matricule H${sessionScope.sessionEleve.id_eleve}<br/>
				Date de naissance <fmt:formatDate value="${sessionScope.sessionEleve.date_naissance}" pattern="d MMMM yyyy"/><br/>
				Adresse : ${sessionScope.sessionEleve.numrue} ${sessionScope.sessionEleve.nomrue},<br/>
				${sessionScope.sessionEleve.codepostal} ${sessionScope.sessionEleve.ville}<br/>
				${sessionScope.sessionEleve.diplome == 1 ? 'Diplomé' : 'Non Diplomé'}<br/>
				
			</article>
			<article>
				<table class="tableau">
					<thead>
						<tr>
							<th colspan="2">Etat</th>
							<th>A faire</th>
							<th>Fait</th>
							<th>En attente</th>
							<th>Dues</th>
						</tr>
					</thead>	
					<tr>
						<td><img src="img/${sessionScope.sessionEleve.teaAfaire == 0 ? 'vert' : 'rouge' }.png" alt ="${eleves.teaAfaire == 0 ? 'vert' : 'rouge' }"/></td>
						<td>${sessionScope.sessionEleve.teaAfaire == 0 ? 'A jour' : 'Pas à jour' }</td>
						<td>${sessionScope.sessionEleve.teaAfaire}</td>
						<td>${sessionScope.sessionEleve.teaFaite}</td>
						<td>${nbTeaDuesEnCours}</td>
					</tr>
				</table>
			</article>
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>
    </body>
</html>
