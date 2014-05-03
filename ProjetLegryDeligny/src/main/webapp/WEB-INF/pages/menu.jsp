<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header id="ban">
</header>
<nav>
	<ul id="menu">
		<li>
			<a href="index">Accueil</a>
		</li>
		<li>
			<a href="#">Integrale</a>
			<ul>
				<li><a href="#">Comité</a></li>
				<li><a href="#">PES</a></li>
				<li><a href="#">BDA</a></li>
				<li><a href="#">BCS</a></li>
				<li><a href="#">PET</a></li>
			</ul>
		</li>
		<li>
			<a href="${empty sessionScope.sessionEleve ? 'index' : 'tea'}">TEA</a>
			<c:if test="${!empty sessionScope.sessionEleve}">
			<ul>
				<li><a href="tea">Mes TEA</a></li>
				<li><a href="annonces">Annonces</a></li>
				<li><a href="deposerannonce">Déposer une annonce</a></li>
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
		<li>Profil : etudiant</li>
		<li>Matricule : ${sessionScope.sessionEleve.id_eleve}</li>
		<li>Promotion 2015</li>
		<li>TEA restantes : 0h</li>
	</ul>
	<ul>
		<li class="menu"><a href="administration">Panneau d'administration</a></li>
	</ul>
	<ul>
		<li class="menu"><a href="validationannonces">Annonces en attente</a></li>
		<li class="menu"><a href="gestiontea">Gestion des TEA</a></li>
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