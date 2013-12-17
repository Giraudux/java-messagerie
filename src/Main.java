import compte.*;
import messagerie.*;
import systeme.*;

/**
 * Classe Main
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Main {
	public static void main(String[] args) {
		
		String a = "francois@systeme.fr";
		System.out.println(a.matches(Systeme.ADRESSE_CORRECTE));
		
		try {
////			Systeme webmail = new Systeme("root@systeme.fr","toor");
//            
//			Utilisateur user1 = new Utilisateur("François","francois@systeme.fr","azerty");
////            Utilisateur user2 = new Utilisateur("Alexis","alexis.test@systeme.fr","azerty");
////            Utilisateur user3 = new Utilisateur("Eric","eric.test@systeme.fr","azerty");
////            Utilisateur user4 = new Utilisateur("Pierre","pierre.test@systeme.fr","azerty");
////            Utilisateur user5 = new Utilisateur("Seb","seb.test@systeme.fr","azerty");
//            
////            if(webmail.connexion("root@systeme.fr", "toor") instanceof SuperUtilisateur)
////            	System.out.println("root est connecté");
            
            
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
        
    }
}
