<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:include page="menu.jsp" />
	 <div id="fb-root"></div> <script>(function(d, s, id) {   var js, fjs = d.getElementsByTagName(s)[0];   if (d.getElementById(id)) return;   js = d.createElement(s); js.id = id;   js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.0";   fjs.parentNode.insertBefore(js, fjs); }(document, 'script', 'facebook-jssdk'));</script>
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
				<c:if test="${!empty sessionScope.sessionEleve}">
					<h2>Bienvenue !</h2>
					<p>Bonjour et bienvenue sur le portail de gestion des Teas, ${sessionScope.sessionEleve.eleve_prenom } ${sessionScope.sessionEleve.eleve_nom }.</p>
				</c:if>
			</article>
			<article>
				<H2>Suivez-nous sur Facebook</H2>
				<div class="fb-follow" data-href="https://www.facebook.com/boulkiyheis2012" data-width="940px" data-colorscheme="light" data-layout="standard" data-show-faces="true"></div>
			</article>
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>	
    </body>
</html>
