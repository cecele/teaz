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
 
			<h1>Mes Annonces</h1>			
			<c:if test="${couples.isEmpty()}">
			<article>
				<p>Vous n'avez posté aucune annonce</p>
			</article> 
			</c:if>
			<c:forEach var="couples" items="${applicationScope.couples}">
			<article>
				
					<table class="annonce"><tr><td class="left"><h2 class="${entry.key.statut == 1 ? 'valide' : 'attente'}"><fmt:formatDate value="${entry.key.date_tea}" pattern="d MMMM yyyy"/></h2></td><td class="center"><h2 class="${entry.key.statut == 1 ? 'valide' : 'attente'}">${entry.key.offre_titre} ${entry.key.statut==0 ? '(En attente de validation)' : '(En ligne)'  }</h2></td> <td  class="right"><h2 class="${entry.key.statut == 1 ? 'valide' : 'attente'}">${entry.key.heure_debut}H - ${entry.key.heure_fin}H</h2></td></tr></table>		
					<hr/>
					<p>
					Description : ${entry.key.offre_description}<br/>
					Plus de renseignements : ${entry.eleve_mail}@hei.fr<br/>
					<br/>
					Association : ${entry.key.structure_nom}<br/>
					Président : ${entry.key.structure_president_prenom} ${entry.key.structure_president_nom}<br/>
					${entry.key.offre_place} place${entry.key.offre_place >= 2 ? 's' : '' } disponible${entry.key.offre_place >= 2 ? 's' : '' }<br/>
					</p>
					<c:if test="${entry.key.statut==0}">
						<a href="deposerannonce?id=${entry.key.cle_offre}">
							<input type="submit" value="Modifier"/>
						</a>
					</c:if>
			</article>
			</c:forEach>
			
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>
    </body>
</html>