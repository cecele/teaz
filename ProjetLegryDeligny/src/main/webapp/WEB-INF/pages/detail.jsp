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
 
			<h1>${eleve.eleve_prenom} ${eleve.eleve_nom}</h1>	
			<article>
				<h2>Fiche</h2>
				Matricule H${eleve.id_eleve}<br/>
				Date de naissance <fmt:formatDate value="${eleve.date_naissance}" pattern="d MMMM yyyy"/><br/>
				Adresse : ${eleve.numrue} ${eleve.nomrue},<br/>
				${eleve.codepostal} ${eleve.ville}<br/>
				Date d'entrée : <fmt:formatDate value="${eleve.date_entree}" pattern="yyyy"/><br/>
				${eleve.cotisant == 1 ? 'Cotisant' : 'Non cotisant'}<br/>
				${eleve.diplome == 1 ? 'Diplomé' : 'Non Diplomé'}<br/>
				
			</article>
			<c:if test="${empty teas}">		
			<article>
				<p>Aucune heure de TEA réalisée...</p>
			</article> 
			</c:if>		
			<c:forEach var="teas" items="${teas}">
			
			<article>
					<table class="annonce"><tr><td class="left"><h2 class="${teas.statut_valide == 2 ? 'valide' : 'attente'}"><fmt:formatDate value="${teas.date_tea_realisee}" pattern="d MMMM yyyy"/></h2></td><td class="center"><h2 class="${teas.statut_valide == 2 ? 'valide' : 'attente'}">${teas.statut_valide == 2 ? 'Validée' : 'En attente'}</h2></td> <td class="right"><h2 class="${teas.statut_valide == 2 ? 'valide' : 'attente'}">${teas.nbheure_realisee}H</h2></td></tr></table>
					<hr/>
					<p>
					Demandeur : ${teas.structure_nom}<br/>
					Président : ${teas.structure_president_prenom} ${teas.structure_president_nom}<br/>
					<br/>
					Evenement: ${teas.offre_titre}
					</p>
			</article>
			
			</c:forEach>
			<input action="action" type="button" value="Retour" onclick="history.go(-1);"/>
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>
    </body>
</html>
