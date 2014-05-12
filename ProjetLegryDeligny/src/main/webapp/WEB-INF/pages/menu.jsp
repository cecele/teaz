<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
   	<head>	
  	<meta http-equiv="Content-Type" content="text/html;
			 charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/stylepage.css"/>
		<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="js/menu.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
        <title>Tea</title>
        
        <!-- SLIDER -->
        
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="banniere, bootstrap slider, javascript slider" />
		
		<link rel="stylesheet" type="text/css" href="engine/style.css" />
		<script type="text/javascript" src="engine/jquery.js"></script>
		
		
    </head>
    <body>
    <!-- Menu -->
    <div id="layout">
    	<div id="ban">
		<div id="wowslider-container1">
			<div class="ws_images">
				<ul>
					<li><img src="img/ban1.jpg" alt="ban1" title="ban1" id="wows1_0"/></li>
					<li><img src="img/ban2.jpg" alt="ban2" title="ban2" id="wows1_1"/></li>
					<li><img src="img/ban3.jpg" alt="ban3" title="ban3" id="wows1_2"/></li>
					<li><img src="img/ban4.jpg" alt="ban4" title="ban4" id="wows1_3"/></li>
				</ul>
			</div>
			<div class="ws_bullets">
				<div>
					<a href="#" title="ban1">1</a>
					<a href="#" title="ban2">2</a>
					<a href="#" title="ban3">3</a>
					<a href="#" title="ban4">4</a>
				</div>
			</div>
		</div>
	<script type="text/javascript" src="engine/wowslider.js"></script>
	<script type="text/javascript" src="engine/script.js"></script>
	</div>
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
			<a href="${empty sessionScope.sessionEleve ? 'index' : 'tea'}">TEA <c:if test="${sessionScope.nbTeaAValiderByStructure >0 && !empty sessionScope.sessionEleve}"><span class="attente">(<span id="nbH">${sessionScope.nbTeaAValiderByStructure}</span>)</span></c:if></a>
			<c:if test="${!empty sessionScope.sessionEleve}">
			<ul>
				<li><a href="mestea">Mes TEA</a></li>
				<li><a href="annonces">Annonces</a></li>
				<c:if test="${sessionScope.sessionEleve.eleve_profil==1 || sessionScope.sessionEleve.eleve_profil==2|| sessionScope.sessionEleve.eleve_profil==4|| sessionScope.sessionEleve.eleve_profil==999}">
					<li><a href="deposerannonce?id=0">Déposer une annonce</a></li>
					<li><a href="mesannonces">Mes annonces</a></li>
					<li><a href="fairetea">Valider les heures <c:if test="${sessionScope.nbTeaAValiderByStructure >0 && !empty sessionScope.sessionEleve }"><span class="attente">(<span id="nbH2">${sessionScope.nbTeaAValiderByStructure}</span>)</span></c:if></a></li>
				</c:if>
			</ul>
			</c:if>
		</li>
	</ul>
</nav>

