$(document).ready(function(){
        
	
        $(".postuler").click(function(){
        
            $.ajax({ 
               url:"postuler", type:"POST",data:{id:$(this).attr("data-id")}
            });
            $(".message").text("Vous avez bien postule a cette offre");
            $(this).parent().parent().remove();
                        
                
        });
        
        $(".faireteavalider").click(function(){
            
            
                    $.ajax({ 
                    	url:"faireteavalider", type:"POST",data:{id:$(this).attr("data-id")}
                    });
                    $(".message").text("Vous avez bien valide cette heure");
                    $(this).parent().parent().remove();
                    
            
        });
        
        $(".validerannonce").click(function(){
            
           
                    $.ajax({ 
                    	url:"validerannonce", type:"POST",data:{id:$(this).attr("data-id")}
                    });
                    $(".message").text("Vous avez bien valide cette annonce");
                    $(this).parent().parent().remove();
            
        });	
        $(".validertea").click(function(){
                
            
                     $.ajax({ 
                        	url:"validertea", type:"POST",data:{id:$(this).attr("data-id")}
                     });
                     $(".message").text("Vous avez bien valide cette heure de TEA");
                     $(this).parent().parent().remove();
                
            });		
        
   
        
        $(".gestionadministration").click(function(){
            var okay = window.confirm("Voulez-vous vraiment retirer les droits?");
    		if(okay == true){
    
    			$.ajax({ 
    				url:"gestionadministration", type:"POST",data:{id:$(this).attr("data-id")}
    			});
    			$(".message").text("Vous avez bien retire les droits");
    			$(this).parent().parent().remove();
    		}        
                
        });
        

        
});