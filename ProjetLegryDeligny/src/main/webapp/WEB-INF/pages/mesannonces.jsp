<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

	<jsp:include page="menu.jsp" />
	
	<!-- Corps -->
   		<section>
 
			<h1>Mes Annonces</h1>			
			<c:if test="${empty offres}">
			<article>
				<p>Vous n'avez posté aucune annonce</p>
			</article> 
			</c:if>
			<c:forEach var="offres" items="${offres}">
			<article>
				
					<table class="annonce"><tr><td class="left"><h2 class="${offres.statut == 1 ? 'valide' : 'attente'}"><fmt:formatDate value="${offres.date_tea}" pattern="d MMMM yyyy"/></h2></td><td class="center"><h2 class="${offres.statut == 1 ? 'valide' : 'attente'}">${offres.offre_titre} ${offres.statut==0 ? '(En attente de validation)' : '(En ligne)'  }</h2></td> <td  class="right"><h2 class="${offres.statut == 1 ? 'valide' : 'attente'}">${offres.heure_debut}H - ${offres.heure_fin}H</h2></td></tr></table>		
					<hr/>
					<p>
					Description : ${offres.offre_description}<br/>
					Plus de renseignements : ${offres.eleve_mail}@hei.fr<br/>
					<br/>
					Association : ${offres.structure_nom}<br/>
					Président : ${offres.structure_president_prenom} ${offres.structure_president_nom}<br/><br/>
					${offres.offre_place} place${offres.offre_place >= 2 ? 's' : '' } disponible${offres.offre_place >= 2 ? 's' : '' }<br/>
					</p>
					<c:if test="${offres.statut==0}">
						<a href="deposerannonce?id=${offres.cle_offre}">
							<input type="submit" value="Modifier"/>
						</a>
					</c:if>
					<c:forEach var="teas" items="${teas}">
						<c:if test="${offres.cle_offre == teas.cle_offre}">
							<hr/>						
							${teas.eleve_prenom } ${teas.eleve_nom }
						</c:if>
					</c:forEach>
			</article>
			</c:forEach>
			
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>
    </body>
</html>