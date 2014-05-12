<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<aside>
	<c:if test="${!empty sessionScope.sessionEleve}">
	<h2>Mon profil</h2>
	<ul>
		<li>${sessionScope.sessionEleve.eleve_prenom} ${sessionScope.sessionEleve.eleve_nom}</li>
		<li>${sessionScope.structure.structure_nom }</li>
		<li>Profil : <c:choose>
		<c:when test="${sessionScope.sessionEleve.eleve_profil==0}" >Etudiant</c:when>
		<c:when test="${sessionScope.sessionEleve.eleve_profil==1}" >Responsable</c:when>
		<c:when test="${sessionScope.sessionEleve.eleve_profil==2}" >Admin BDE</c:when>
		<c:when test="${sessionScope.sessionEleve.eleve_profil==3}" >Administration des études</c:when>
		<c:when test="${sessionScope.sessionEleve.eleve_profil==4}" >SuperAdmin</c:when>
		<c:when test="${sessionScope.sessionEleve.eleve_profil==999}" >Secours</c:when>
		</c:choose></li>
		<li>Matricule : ${sessionScope.sessionEleve.id_eleve}</li>
		<li>Classe : ${sessionScope.sessionEleve.classe}</li>
		<li>TEA restantes : ${sessionScope.sessionEleve.teaAfaire}h</li>
	</ul>
	<c:if test="${sessionScope.sessionEleve.eleve_profil==4|| sessionScope.sessionEleve.eleve_profil==999}">
	<ul>
		<li class="menu"><a href="administration">Panneau d'administration</a></li>
	</ul>
	</c:if>
	
	<ul>
	<c:if test="${sessionScope.sessionEleve.eleve_profil==2|| sessionScope.sessionEleve.eleve_profil==4|| sessionScope.sessionEleve.eleve_profil==999}">
		<li class="menu"><a href="validationannonces">Annonces en attente<c:if test="${sessionScope.nbOffreEnAttente >0 }"><span class="attente"> (<span id="nbOffre">${sessionScope.nbOffreEnAttente}</span>)</span></c:if></a></li>
		<li class="menu"><a href="validationtea">TEA en attente<c:if test="${sessionScope.nbTeaEnAttente >0 }"><span class="attente">(<span id="nbTea">${sessionScope.nbTeaEnAttente}</span>)</span></c:if></a></li>
	</c:if>	
	<c:if test="${sessionScope.sessionEleve.eleve_profil==2|| sessionScope.sessionEleve.eleve_profil==3|| sessionScope.sessionEleve.eleve_profil==4|| sessionScope.sessionEleve.eleve_profil==999}">
		<li class="menu"><a href="gestioneleves">Gestion des élèves</a></li>
	</c:if>
	</ul>
	<ul>
		<li class="menu"><a href="deconnexion">Se déconnecter</a></li>
	</ul>
	</c:if>
	<c:if test="${empty sessionScope.sessionEleve}">  
		<form method="post" action="connexion">             			      
			<ul>
				<li><h2>Connexion</h2></li>                                                   
				<li><input id="id_eleve" name="id_eleve" value="Matricule" size="20" maxlength="5" onclick="if(this.value=='Matricule')this.value='';" /></li>
				<li><input type="password" id="motdepasse" name="motdepasse" value="Mot de passe" size="20" onclick="if(this.value=='Mot de passe')this.value='';" maxlength="20" /></li>
				<li><input type="submit" value="Connexion" class="sansLabel" /></li>
			</ul>
		</form>				
	</c:if>
</aside>

<p id="footer">CopyRight Legry et Deligny. 2014 pour l'intégrale HEI</p>