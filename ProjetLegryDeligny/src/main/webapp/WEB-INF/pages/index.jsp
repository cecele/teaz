<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
 
			<h1>Accueil</h1>			
	
			<article id="accueil">
					
					<header>
						<h2>Coucou</h2>
					</header>
					<form method="post" action="connexion">             
						<fieldset>     
							<c:if test="${empty sessionScope.sessionEleve}">            
							<legend>Connexion</legend>                 
							<p>Vous pouvez vous connecter via ce formulaire.</p>                  
							<label for="nom">Matricule H</label>                 
							<input id="id_eleve" name="id_eleve"  size="20" maxlength="60" />                 
							<span class="erreur">${form.erreurs['id_eleve']}</span><br />                  
							<label for="motdepasse">Mot de passe</label>                 
							<input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />                 
							<span class="erreur">${form.erreurs['motdepasse']}</span><br />                  
							<input type="submit" value="Connexion" class="sansLabel" /><br />                                  
							<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>  
							</c:if>
							<c:if test="${!empty sessionScope.sessionEleve}">                                         
								<p class="succes">Vous êtes connecté(e) avec l'id : ${sessionScope.sessionEleve.id_eleve}</p>                 
							</c:if>           
						</fieldset>         
					</form>
			</article>
			
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>	
    </body>
</html>
