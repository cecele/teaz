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
 
			<h1>TEA en attente</h1>	
			<c:if test="${empty teas}">		
			<article>
				<p>Rien à valider, bon boulot !</p>
			</article> 
			</c:if>
			<c:forEach var="teas" items="${teas}">
			<article>
				
					<table><tr><td><h2><fmt:formatDate value="${teas.date_tea}" pattern="d MMMM yyyy"/></h2></td><td class="center"><h2>${teas.offre_titre}</h2></td> <td class="right"><h2>${teas.nbheure_realisee}H réalisées</h2></td></tr></table>		
					<hr/>
					<p>
					Description : ${teas.offre_description }<br/>
					Matricule de l'élève : ${teas.id_eleve }<br/>
					<br/>
					Association : ${teas.structure_nom}<br/>
					Président : ${teas.structure_president_prenom} ${teas.structure_president_nom}<br/>
					<a href="validertea?id=${teas.cle_tea }">Valider</a>
					</p>
			</article>
			</c:forEach>
			
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>
    </body>
</html>