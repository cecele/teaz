<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

	<jsp:include page="menu.jsp" />
	
	<!-- Corps -->
   		<section>
 
			<h1>Valider les heures</h1>	
			<article class="valide">		
				<c:if test="${empty teas}">
					<p>Aucune heure de tea � valider</p>
				</c:if>
				<span class="message"></span>
			</article>
			<c:forEach var="teas" items="${teas}">
			<article>
				
					<table class="annonce"><tr><td class="left"><h2><fmt:formatDate value="${teas.date_tea}" pattern="d MMMM yyyy"/></h2></td><td class="center"><h2>${teas.offre_titre}</h2></td> <td class="right"><h2>${teas.heure_debut}H - ${teas.heure_fin}H</h2></td></tr></table>		
					<hr/>
					<p>
					Eleve : ${teas.eleve_prenom} ${teas.eleve_nom} <br/>
					Matricule : ${teas.id_eleve}<br/>
					<br/>
					Association : ${teas.structure_nom}<br/>
					Pr�sident : ${teas.structure_president_prenom} ${teas.structure_president_nom}<br/>
					<br/>
					<span class="faireteavalider" data-id="${teas.cle_tea }"><input type="button" value="Valider"/></span>
					</p>
			</article>
			</c:forEach>
			
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>
    </body>
</html>