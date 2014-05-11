$(document).ready(function(){
        
        $(".postuler").click(function(){
                
                var okay = window.confirm("Postuler ?"); 
                if(okay == true){
                        $.ajax({ 
                        	url:"postuler", type:"POST",data:{id:$(this).attr("data-id")}
                        });
                        $(this).parent().parent().remove();
                }
        });
        
});