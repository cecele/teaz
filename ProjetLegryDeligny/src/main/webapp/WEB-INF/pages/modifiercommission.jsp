<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<jsp:include page="menu.jsp" />
	
	<!-- Corps -->
   		<section>
 
			<h1>Modifier ${structure.structure_nom }</h1>			
	
			<article id="accueil">
				<form action="modifiercommission?id=${structure.cle_structure }" method="POST" name="form">           
						    Matricule du nouveau président (sans le h)<br/>                                   
							<input type="text" id="ideleve" name="ideleve"  size="20" maxlength="60" required/><br/>     
							Début de mandat<br/>                                         
							<input type="date" id="debut" name="debut" required/><br/> 
							Fin de mandat<br/>    
							<input type="date" id="fin" name="fin" required/><br/>                        
							<span class="modifiercommission"><input type="submit" value="Modifier" class="sansLabel" /></span><br />                                   
				</form>		
			</article>
			<input action="action" type="button" value="Retour" onclick="history.go(-1);"/>
		</section>	
		
		<jsp:include page="bottom.jsp" />
	</div>	
    </body>
</html>
