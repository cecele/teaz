package hei.devweb.forms;  

import java.util.HashMap; 
import java.util.Map;  

import javax.servlet.http.HttpServletRequest;  

import hei.devweb.model.Eleve;  
import hei.devweb.metier.Manager;

/**
 * formulaire de connexion qui gère les erreurs de saisie
 * 
 * @author Projet
 *
 */
public final class ConnexionForm {     
	     
	private String              resultat;     
	private Map<String, String> erreurs      = new HashMap<String, String>();  
	
	public String getResultat() {         
		return resultat;     
	}    
	
	public Map<String, String> getErreurs() {         
		return erreurs;     
	}      
	public Eleve connecterEleve( HttpServletRequest request ) {      
		
		/* Récupération des champs du formulaire */         
		String id_eleve = getValeurChamp( request, "id_eleve" );         
		String motDePasse = getValeurChamp( request, "motdepasse" );          
		
		Eleve eleve = Manager.getInstance().getEleveById(id_eleve);
		
		
		/* Validation du champ matricule. */         
		try {             
			validationId( id_eleve );         
			} catch ( Exception e ) {             
				setErreur( "id_eleve", e.getMessage() );         
				}         
		          
		/* Validation du champ mot de passe. */         
		try {             
			validationMotDePasse( motDePasse );         
			} catch ( Exception e ) {             
				setErreur( "motdepasse", e.getMessage() );         
				}         
		/* Validation de la correlation entre matricule et mot de passe		 */
		
		try {
			validationConnexion (id_eleve, motDePasse);
		} catch(Exception e){
			setErreur( "correlation" , e.getMessage());
		}
		/* Initialisation du résultat global de la validation. */         
		if ( erreurs.isEmpty() ) {     
			
			resultat = "Succès de la connexion.";         
			} else {             
				resultat = "Échec de la connexion.";         
				}          
		return eleve;     
		}      

	/**      * Valide l'adresse email saisie.      */     
	private void validationId( String id_eleve ) throws Exception {         
		if ( id_eleve == null ) {             
			throw new Exception( "Merci de saisir votre matricule." );         
			}     
		}      
	/**      
	 * * Valide le mot de passe saisi.  
    */    
	private void validationMotDePasse( String motDePasse ) throws Exception {         
		if ( motDePasse != null ) {             
			if ( motDePasse.length() < 3 ) {                 
				throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );             
				}         
			} else {             
				throw new Exception( "Merci de saisir votre mot de passe." );         
				}     
		}    
	
	private void validationConnexion(String id_eleve, String motDePasse ) throws Exception {  
		Eleve eleve = Manager.getInstance().getEleveById(id_eleve);
		String mdp = eleve.getMotdepasse();
		
		if ( !mdp.equals(motDePasse)) {  
			
			System.out.println(mdp);
			System.out.println(motDePasse);
			throw new Exception( "Mot de passe incorrect." );   
			
			}     
		System.out.println("dans validationconnexion2");
		} 
	/*      
	 * * Ajoute un message correspondant au champ spécifié à la map des erreurs.      
	 * */     
	private void setErreur( String champ, String message ) {         
		erreurs.put( champ, message );     
		}      
	/*      
	 * * Méthode utilitaire qui retourne null si un champ est vide, et son contenu      
	 * * sinon.      
	 * */     
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {         
		String valeur = request.getParameter( nomChamp );         
		if ( valeur == null || valeur.trim().length() == 0 ) {             
			return null;         
			} else {             
				return valeur;         
				}     
		} 
	}
	 