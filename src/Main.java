import compte.*;
import messagerie.*;
import systeme.*;

/**
 * Classe compte.ListeDiffusion
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Main {
	
	public static void main(String[] args) {
		Systeme webmail = new Systeme("root@systeme.fr","root");
		
		Utilisateur user1 = new Utilisateur("François","francois@systeme.fr","azerty");
		Utilisateur user2 = new Utilisateur("Alexis","alexis@systeme.fr","azerty");
		Utilisateur user3 = new Utilisateur("Eric","eric@systeme.fr","azerty");
		Utilisateur user4 = new Utilisateur("Pierre","pierre@systeme.fr","azerty");
		Utilisateur user5 = new Utilisateur("Seb","seb@systeme.fr","azerty");
		
	    if(webmail.addUtilisateur(user1))
	    	System.out.println("ajout user1 ok");
	    
	    if(webmail.addUtilisateur(user2))
	    	System.out.println("ajout user2 ok");
	    
	    if(webmail.addUtilisateur(user3))
	    	System.out.println("ajout user3 ok");
	    
	    if(webmail.addUtilisateur(user4))
	    	System.out.println("ajout user4 ok");
	    
	    if(webmail.addUtilisateur(user5))
	    	System.out.println("ajout user5 ok");
	
		Message message = new Message(user1,user3,"holla","Salut poto éric !");
		user1.envoyerMessage(message);
		
		System.out.println(user1.listerMessages());
		System.out.println(user3.listerMessages());
	
	
	}	
	
}
