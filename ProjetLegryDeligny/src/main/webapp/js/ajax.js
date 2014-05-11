$(document).ready(function(){
        
	$(".valide").hide();
	var nbTea = $("#nbTea").text();
	if(nbTea == 0)
		$(".valide").show();
	
        $(".postuler").click(function(){
        
            $.ajax({ 
               url:"postuler", type:"POST",data:{id:$(this).attr("data-id")}
            });
            $(".message").text("Vous avez bien postule a cette offre");
            $(this).parent().parent().remove();
            $(".valide").show();            
                
        });
        
        $(".faireteavalider").click(function(){
            
            
                    $.ajax({ 
                    	url:"faireteavalider", type:"POST",data:{id:$(this).attr("data-id")}
                    });
                    $(".message").text("Vous avez bien valide cette heure");
                    $(this).parent().parent().remove();
                    $(".valide").show();
            
        });
        
        $(".validerannonce").click(function(){
            
           
                    $.ajax({ 
                    	url:"validerannonce", type:"POST",data:{id:$(this).attr("data-id")}
                    });
                    var nbOffre = 0;
                    nbOffre = $("#nbOffre").text();
                    nbOffre--;
                    if(nbOffre ==0){
                   	 	$("#nbOffre").parent().remove();
                   	 	$(".message").text("Il n'y a plus d'annonces a valider, bon boulot!");
                    }
                    else{
                   	 	$("#nbOffre").text(nbOffre);
                   	 	$(".message").text("Vous avez bien valide cette annonce");
                    }
              
                    $(this).parent().parent().remove();
                    $(".valide").show();
            
        });	
        $(".validertea").click(function(){
                
            
                     $.ajax({ 
                        	url:"validertea", type:"POST",data:{id:$(this).attr("data-id")}
                     });
                     var nbTea = $("#nbTea").text();
                     nbTea--;
                     if(nbTea ==0){
                    	 $("#nbTea").parent().remove();
                    	 $(".message").text("Il n'y a plus d'heures de TEA a valider, bon boulot!");
                     }
                     else{
                    	 $("#nbTea").text(nbTea);
                    	 $(".message").text("Vous avez bien valide cette heure de TEA");
                     }
                     
                 
                     $(this).parent().parent().remove();
                     $(".valide").show();
                
            });		
        
   
        
        $(".gestionadministration").click(function(){
            var okay = window.confirm("Voulez-vous vraiment retirer les droits?");
    		if(okay == true){
    
    			$.ajax({ 
    				url:"gestionadministration", type:"POST",data:{id:$(this).attr("data-id")}
    			});
    			$(".message").text("Vous avez bien retire les droits");
    			$(this).parent().parent().remove();
    			$(".valide").show();
    		}        
                
        });
        
        $(".modifiercommission").click(function(){
            var okay = window.confirm("Voulez-vous vraiment donner les droits?");
    		if(okay == true){
    
    			$.ajax({ 
    				url:"modifiercommission", type:"POST",data:{id:$(this).attr("data-id")}
    			});
    		}        
    		else{
    			return;
    		}
                
        });
        

        
});