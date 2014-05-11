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
		<li class="menu"><a href="validationannonces">Annonces en attente<c:if test="${sessionScope.nbOffreEnAttente >0 }"><span id="nbOffre" class="attente"> (${sessionScope.nbOffreEnAttente})</span></c:if></a></li>
		<li class="menu"><a href="validationtea">TEA en attente<c:if test="${sessionScope.nbTeaEnAttente >0 }"><span id="nbTea" class="attente"> (${sessionScope.nbTeaEnAttente})</span></c:if></a></li>
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
			<h2>Connexion</h2>                                                   
			<input id="id_eleve" name="id_eleve" value="Matricule" size="10" maxlength="5" onclick="if(this.value=='Matricule')this.value='';" /><br/>            
			<input type="password" id="motdepasse" name="motdepasse" value="Mot de passe" size="10" onclick="if(this.value=='Mot de passe')this.value='';" maxlength="20" /><br/>                                  
			<input type="submit" value="Connexion" class="sansLabel" /><br />                                    		      
		</form>				
	</c:if>
</aside>
<script type="text/javascript">
$(document).ready(function(){
	$(".validerannonce").click(function(){
	    var nbOffre = $("#nbOffre").text();
	    --nbOffre;
	    $("#nbOffre").text(nbOffre);
	});	
	$(".validertea").click(function(){
    	var nbTea = $("#nbTea").text();
     	--nbTea;
     	$("#nbTea").text(nbTea);
	});		
});
</script>

<p>Petit message stylé pour afficher notre pouvoir supérieur</p>