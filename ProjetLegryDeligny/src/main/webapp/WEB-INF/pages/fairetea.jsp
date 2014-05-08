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
	<jsp:include page="menu.jsp" />
	
	<!-- Corps -->
   		<section>
 
			<h1>Valider les heures</h1>			
			<c:if test="${empty teas}">
			<article>
				<p>Aucune heure de tea à valider</p>
			</article> 
			</c:if>
			<c:forEach var="teas" items="${tea}">
			<article>
				
					<table><tr><td><h2><fmt:formatDate value="${tea.date_tea}" pattern="d MMMM yyyy"/></h2></td><td class="center"><h2>${tea.offre_titre}</h2></td> <td class="right"><h2>${tea.heure_debut} - ${tea.heure_fin}</h2></td></tr></table>		
					<hr/>
					<p>
					Eleve : ${tea.id_eleve}<br/>
					<br/>
					Association : ${tea.structure_nom}<br/>
					Président : ${tea.structure_president}<br/>
					<br/>
					<a href="fairetea?id=${tea.cle_offre }">Valider</a>
					</p>
			</article>
			</c:forEach>
			
		</section>	
    </body>
</html>