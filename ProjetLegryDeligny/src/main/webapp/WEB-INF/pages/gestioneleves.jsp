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
        <script type="text/javascript">
 			$(document).ready(function(){
 				
 			$("#encours_id").click(function(){
                    $("#matricule").attr("disabled",true);
                    $("#matricule").attr("style","background-color:gray;");
                    $("#matricule").attr("value","");
                    $("form").attr("action","recherche?rech=2");
            });
            $("#ajour_id").click(function(){
                    $("#matricule").attr("disabled",true);
                    $("#matricule").attr("style","background-color:gray;");
                    $("#matricule").attr("value","");
                    $("form").attr("action","recherche?rech=2");
            });
            $("#diplome_id").click(function(){
                    $("#matricule").attr("disabled",true);
                    $("#matricule").attr("style","background-color:gray;");
                    $("#matricule").attr("value","");
                    $("form").attr("action","recherche?rech=2");
            });
            $("#pasajour_id").click(function(){
                    $("#matricule").attr("disabled",true);
                    $("#matricule").attr("style","background-color:gray;");
                    $("#matricule").attr("value","");
                    $("form").attr("action","recherche?rech=2");
            });
 			function init(){
					$("#matricule").attr("disabled",false);
					$("#matricule").attr("style","background-color:rgb(52,73,94);");
					$("#matricule").value="";
					$("#nom").attr("disabled",false);
					$("#nom").attr("style","background-color:rgb(52,73,94);");
					$("#nom").value="";
					$("#prenom").attr("disabled",false);
					$("#prenom").attr("style","background-color:rgb(52,73,94);");
					$("#prenom").value="";
					$("#classe").attr("disabled",false);
					$("#classe").attr("style","background-color:rgb(52,73,94);");
					$("#classe").value="Tous";
					$("#encours").attr("disabled",false);
					$("#encours").attr("style","background-color:rgb(52,73,94);");
					$("#encours").prop('checked', true);
					$("#ajour").attr("disabled",false);
					$("#ajour").attr("style","background-color:rgb(52,73,94);");
					$("#ajour").prop('checked',true);
					$("#pasajour").attr("disabled",false);
					$("#pasajour").attr("style","background-color:rgb(52,73,94);");
					$("#pasajour").prop('checked',true);
					$("#diplome").attr("disabled",false);
					$("#diplome").attr("style","background-color:rgb(52,73,94);");
					$("#diplome").prop('checked',true);
					$("form").attr("action","recherche?rech=0");
			}});
 </script>
    </head>
    <body>
    <!-- Menu -->
    <div id="layout">
	<jsp:include page="menu.jsp" />
	
	<!-- Corps -->
   		<section>
 
			<h1>Gestion des élèves</h1>			
	
			<article>
				<h2>Filtre</h2>
				<form method="POST" class="form" name="form" action="recherche?rech=0">
					<table>
						<tr>
							<td><label for="matricule" >Matricule</label></td>
							<td><input type="text" name="matricule" id="matricule" value=""></input></td>
						</tr>
						<tr>
							<td><label for="nom" >Nom</label></td>
							<td><input type="text" name="nom" id="nom" value=""></input></td>
						</tr>
						<tr>
							<td><label for="prenom" >Prénom</label></td>
							<td><input type="text" name="prenom" id="prenom" value=""></input></td>
						</tr>
						
 <tr>
     <td><label for="encours" >En Cours</label></td>
     <td><input type="checkbox" value="encours_value" name="encours_id" id="encours_id"  checked="checked"/></td>
</tr>
 <tr>
     <td><label for="diplome" >Diplomé</label></td>
     <td><input type="checkbox" value="diplome_value" name="diplome_id" id="diplome_id"  checked="checked"/></td>
</tr>
<tr>
     <td><label for="pasajour" >Tea pas à jour</label></td>
     <td><input type="checkbox" value="pasajour_value" name="pasajour_id" id="pasajour_id"  checked="checked"/></td>
</tr>
<tr>
     <td><label for="ajour" >TEA à jour</label></td>
     <td><input type="checkbox" name="ajour_id" value="ajour_value" id="ajour_id" checked="checked"/></td>
</tr>
							<td><label for="classe" >Classe</label></td>
							<td>
								<select name="classe" id="classe" required >
									<option value="tous">Tous</option>
									<option value="h1a">1A</option>
									<option value="h1b">1B</option>
									<option value="h1c">1C</option>
									<option value="h1d">1D</option>
									<option value="h1e">1E</option>
									<option value="h1f">1F</option>
									<option value="h1g">1G</option>
									<option value="h2a">2A</option>
									<option value="h2b">2B</option>
									<option value="h2c">2C</option>
									<option value="h2d">2D</option>
									<option value="h2e">2E</option>
									<option value="h2f">2F</option>
									<option value="h31">h31</option>
									<option value="h32">h32</option>
									<option value="h33">h33</option>
									<option value="h34">h34</option>
									<option value="h35">h35</option>
									<option value="h36">h36</option>
									<option value="h38">h38</option>
									<option value="h41">h41</option>
									<option value="h42">h42</option>
									<option value="h43">h43</option>
									<option value="h44">h44</option>
									<option value="h48">h48</option>
									<option value="h51">h51</option>
									<option value="h52">h52</option>
									<option value="CES">Césure</option>
									<option value="HCA">Hors Catégorie</option>
								</select>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Rechercher"/><input type="button" onClick="init()" value="Réinitialiser"/></td>
						</tr>
					</table>
				</form>	
			</article>
			<article>
				<table class="tableau">
					<thead>
						<tr>
							<th colspan="2">Matricule</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Classe</th>
							<th>A faire</th>
							<th>Fait</th>
							<th>En attente</th>
							<th>Justif</th>
						</tr>
					</thead>	
					<c:forEach var="eleves" items="${eleves}">
					<tr>
						<td><img src="img/${eleves.teaAfaire == 0 ? 'vert' : 'rouge' }.png" alt ="${eleves.teaAfaire == 0 ? 'vert' : 'rouge' }"/></td>
						<td>${eleves.id_eleve}</td>
						<td>${eleves.eleve_nom}</td>
						<td>${eleves.eleve_prenom}</td>
						<td>${eleves.classe}</td>
						<td>${eleves.teaAfaire}</td>
						<td>${eleves.teaFaite}</td>
						<td>${eleves.teaEnAttente}</td>
						<td><a href="detail?id=${eleves.id_eleve}">Détail</a></td>
					</tr>
					</c:forEach>
				</table>
			</article>
		</section>	
		<jsp:include page="bottom.jsp" />
	</div>
    </body>
</html>
