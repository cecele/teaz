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
				<c:if test="${empty sessionScope.sessionEleve}"> 
					<header>
						<h2>Connexion</h2>
					</header>
					<form method="post" action="connexion">                 
							<table>
								<tr>                  
									<td>          
										<label for="nom">Matricule H</label>   
									</td>  
									<td>            
										<input id="id_eleve" name="id_eleve"  size="20" maxlength="60" />                 
										<span class="attente">${form.erreurs['id_eleve']}</span>
									</td>
								</tr>
								<tr>       
									<td>          
										<label for="motdepasse">Mot de passe</label>   
									</td>
									<td>	              
										<input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />                 
										<span class="attente">${form.erreurs['motdepasse']}</span>
									</td>
								<tr>	 
									<td colspan="2">                 
										<input type="submit" value="Connexion" class="sansLabel" />
									</td>	
								</tr>   
								<tr>        
									<td colspan="2">                      
										<p class="${empty form.erreurs ? 'valide' : 'attente'}">${form.resultat}</p> 
									</td>
								</tr> 
							</table>            
					</form>
				</c:if>  
				<p>
					
				</p>	
			</article>
			
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>	
    </body>
</html>
