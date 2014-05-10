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
 
			<h1>Modifier Commission</h1>			
	
			<article id="accueil">
					
					<header>
						<h2>Coucou</h2>
					</header>
					<form method="post" action="modifiercommission">             
						  
							                                    
							<label for="nom">Matricule H</label>                 
							<input id="id_eleve" name="id_eleve"  size="20" maxlength="60" />                 
							<span class="erreur">${form.erreurs['id_eleve']}</span><br />                  
							<label for="motdepasse">Mot de passe</label>                 
							<input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />                 
							<span class="erreur">${form.erreurs['motdepasse']}</span><br />                  
							<input type="submit" value="Connexion" class="sansLabel" /><br />                                   
							               
					</form>
			</article>
			
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>	
    </body>
</html>
