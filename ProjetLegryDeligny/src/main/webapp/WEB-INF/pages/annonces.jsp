<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

	<jsp:include page="menu.jsp" />
	
	<!-- Corps -->
   		<section>
 
			<h1>Annonces</h1>			
			<article class="${empty offres ? 'attente' : 'valide' }">
				<span class="message"></span>
				<c:if test="${empty offres}">		
					<p>Il n'y a pas d'offre disponible pour le moment.</p>
				</c:if>
			</article>
			<c:forEach var="offres" items="${offres}">
			<article>
				
					<table class="annonce"><tr><td class="left"><h2><fmt:formatDate value="${offres.date_tea}" pattern="d MMMM yyyy"/></h2></td><td class="center"><h2>${offres.offre_titre}</h2></td> <td class="right"><h2>${offres.heure_debut}H - ${offres.heure_fin}H</h2></td></tr></table>		
					<hr/>
					<p>
					Description : ${offres.offre_description}<br/>
					Plus de renseignements : ${offres.eleve_mail}@hei.fr<br/>
					<br/>
					Association : ${offres.structure_nom}<br/>
					Président : ${offres.structure_president_prenom} ${offres.structure_president_nom}<br/>
					<span id="nbplaces">${offres.offre_place}</span> place<span class="plur">${offres.offre_place >= 2 ? 's' : '' }</span> disponible<span class="plur">${offres.offre_place >= 2 ? 's' : '' }</span><br/>
					<span class="postuler" data-id="${offres.cle_offre}"><input type="button" value="Postuler"/></span>
					</p>
			</article>
			</c:forEach>
			
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>
    </body>
</html>