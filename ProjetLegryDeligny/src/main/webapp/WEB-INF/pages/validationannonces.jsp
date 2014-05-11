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
		<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="js/menu.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
        <title>Tea</title>
    </head>
    <body>
    <!-- Menu -->
    <div id="layout">
	<jsp:include page="menu.jsp" />
	
	<!-- Corps -->
   		<section>
 
			<h1>Annonces</h1>	
			
			<article class="valide">
				<span class="message"></span>
				<c:if test="${empty offres}">		
					<p>Rien à valider, bon boulot !</p>
				</c:if>
			</article>
			<c:forEach var="offres" items="${offres}">
			<article>
				
					<table class="annonce"><tr><td class="left"><h2><fmt:formatDate value="${offres.date_tea}" pattern="d MMMM yyyy"/></h2></td><td class="center"><h2>${offres.offre_titre}</h2></td> <td class="right"><h2>${offres.heure_debut} - ${offres.heure_fin}</h2></td></tr></table>		
					<hr/>
					<p>
					Description : ${offres.offre_description}<br/>
					Plus de renseignements : ${offres.eleve_mail}@hei.fr<br/>
					<br/>
					Association : ${offres.structure_nom}<br/>
					Président : ${offres.structure_president_prenom} ${offres.structure_president_nom}<br/>
					${offres.offre_place} places disponibles<br/>
					<span class="validerannonce" data-id="${offres.cle_offre }"><input type="button" value="Valider"/></span>
					</p>
			</article>
			</c:forEach>
			
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>
    </body>
</html>