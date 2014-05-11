<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   	<head>
  	<meta http-equiv="Content-Type" content="text/html;
			 charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="js/menu.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
        <title>Tea</title>
    </head>
    <body>
    <!-- Menu -->
    <div id="layout">
	<jsp:include page="menu.jsp" />
	
	<!-- Corps -->
   		<section>
 
			<h1>Modifier ${structure.structure_nom }</h1>			
	
			<article id="accueil">
					
					           
						    Matricule du nouveau président (sans le h)<br/>                                   
							<input type="text" id="ideleve" name="ideleve"  size="20" maxlength="60" /><br/>     
							Début de mandat<br/>                                         
							<input type="date" id="debut" name="debut" /><br/> 
							Fin de mandat<br/>    
							<input type="date" id="fin" name="fin" /><br/>                        
							<span class="modifiercommission" data-id="${structure.cle_structure}"><input type="submit" value="Modifier" class="sansLabel" /></span><br />                                   
					
			</article>
			
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>	
    </body>
</html>
