<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header id="ban">
</header>
<nav>
	<ul id="menu">
		<li>
			<a href="index">Accueil</a>
		</li>
		<li>
			<a href="integrale?id=1">Integrale</a>
			<ul>
				<li><a href="integrale?id=2">Comité</a></li>
				<li><a href="integrale?id=3">PES</a></li>
				<li><a href="integrale?id=4">BDA</a></li>
				<li><a href="integrale?id=5">BCS</a></li>
				<li><a href="integrale?id=6">PET</a></li>
			</ul>
		</li>
		<li>
			<a href="${empty sessionScope.sessionEleve ? 'index' : 'tea'}">TEA</a>
			<c:if test="${!empty sessionScope.sessionEleve}">
			<ul>
				<li><a href="tea">Mes TEA</a></li>
				<li><a href="annonces">Annonces</a></li>
				<c:if test="${sessionScope.sessionEleve.eleve_profil==1 || sessionScope.sessionEleve.eleve_profil==2|| sessionScope.sessionEleve.eleve_profil==4|| sessionScope.sessionEleve.eleve_profil==999}">
					<li><a href="deposerannonce">Déposer une annonce</a></li>
					<li><a href="fairetea">Valider les heures</a></li>
				</c:if>
			</ul>
			</c:if>
		</li>
	</ul>
</nav>

<aside>
	<c:if test="${!empty sessionScope.sessionEleve}">
	<h2>Mon profil</h2>
	<ul>
		<li>${sessionScope.sessionEleve.eleve_prenom} ${sessionScope.sessionEleve.eleve_nom}</li>
		<li>Profil : <c:choose>
		<c:when test="${sessionScope.sessionEleve.eleve_profil==0}" >Etudiant</c:when>
		<c:when test="${sessionScope.sessionEleve.eleve_profil==1}" >Responsable</c:when>
		<c:when test="${sessionScope.sessionEleve.eleve_profil==2}" >Admin BDE</c:when>
		<c:when test="${sessionScope.sessionEleve.eleve_profil==3}" >Administration des études</c:when>
		<c:when test="${sessionScope.sessionEleve.eleve_profil==4}" >SuperAdmin</c:when>
		<c:when test="${sessionScope.sessionEleve.eleve_profil==999}" >Secours</c:when>
		</c:choose></li>
		<li>Matricule : ${sessionScope.sessionEleve.id_eleve}</li>
		<li>Classe </li>
		<li>TEA restantes : 0h</li>
	</ul>
	<c:if test="${sessionScope.sessionEleve.eleve_profil==4|| sessionScope.sessionEleve.eleve_profil==999}">
	<ul>
		<li class="menu"><a href="administration">Panneau d'administration</a></li>
	</ul>
	</c:if>
	
	<ul>
	<c:if test="${sessionScope.sessionEleve.eleve_profil==2|| sessionScope.sessionEleve.eleve_profil==4|| sessionScope.sessionEleve.eleve_profil==999}">
		<li class="menu"><a href="validationannonces">Annonces en attente</a></li>
		<li class="menu"><a href="validationtea">TEA en attente</a></li>
	</c:if>	
	<c:if test="${sessionScope.sessionEleve.eleve_profil==2|| sessionScope.sessionEleve.eleve_profil==3|| sessionScope.sessionEleve.eleve_profil==4|| sessionScope.sessionEleve.eleve_profil==999}">
		<li class="menu"><a href="gestiontea">Gestion des TEA</a></li>
	</c:if>
	</ul>
	<ul>
		<li class="menu"><a href="deconnexion">Se déconnecter</a></li>
	</ul>
	</c:if>
	<c:if test="${empty sessionScope.sessionEleve}">  
		<form method="post" action="connexion">             			      
			<h2>Connexion</h2>                                  
			<label for="nom">Matricule H</label>                 
			<input id="id_eleve" name="id_eleve"  size="20" maxlength="60" />                                  
			<label for="motdepasse">Mot de passe</label>                 
			<input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />                                  
			<input type="submit" value="Connexion" class="sansLabel" /><br />                                    		      
		</form>				
	</c:if>
</aside>