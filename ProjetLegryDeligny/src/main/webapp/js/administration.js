$(document).ready(function(){
	
	$("tbody#list").on("click",".suppr", function(){
		var okay = window.confirm("Voulez-vous vraiment supprimer les droits ?");
		if(okay == false){
			return;
		}
	});
});