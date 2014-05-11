<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header id="ban">
	<marquee align="center" height="300px" scrolldelay="10" scrollamount="20" onmouseout="this.start()" onmouseover="this.stop()">
		<p>
			<img src="img/bandeau4.png" alt="bandeau4" hspace="0"/>
		</p>
	</marquee>
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
					<li><a href="mesannonces">Mes annonces</a></li>
					<li><a href="fairetea">Valider les heures</a></li>
				</c:if>
			</ul>
			</c:if>
		</li>
	</ul>
</nav>

