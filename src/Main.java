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
		Systeme webmail = new Systeme("root@systeme.fr","toor");
		
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
	
	    ListeDiffusion l1 = new ListeDiffusion("potoliste@systeme");
        user1.ajouterListeDiffusion(l1);
        user1.ajouterCompteListeDiffusion(l1,user3);
        user1.ajouterCompteListeDiffusion(l1,user2);
        user1.ajouterCompteListeDiffusion(l1,user4);

        Message message2 = new Message(user1,l1,"ChiracLaFrance","ex président chirac de retour pour les prochaines élections?");
        user1.envoyerMessage(message2);

        System.out.println(user1.listerMessages());
        System.out.println(user2.listerMessages());
        System.out.println(user3.listerMessages());
        System.out.println(user4.listerMessages());
        System.out.println(user5.listerMessages());

        ListeDiffusion l2 = new ListeDiffusion("potoliste2@systeme");
        user1.ajouterListeDiffusion(l2);
        user1.ajouterCompteListeDiffusion(l2,user3);
        user1.ajouterCompteListeDiffusion(l2,user5);

        Message message3 = new Message(user1,l2,"Chat perdu","J'ai perdu mon chat Berlioz, un sacré de birmanie trop trop beau <3");
        user1.envoyerMessage(message3);

        System.out.println(user1.listerMessages());
        System.out.println(user2.listerMessages());
        System.out.println(user3.listerMessages());
        System.out.println(user4.listerMessages());
        System.out.println(user5.listerMessages());



        ListeDiffusion l3 = new ListeDiffusion("potoliste3@systeme");
        user1.ajouterListeDiffusion(l3);
        System.out.println(user1.ajouterCompteListeDiffusion(l1,l2));
        System.out.println(user1.ajouterCompteListeDiffusion(l2,l3));
        System.out.println(user1.ajouterCompteListeDiffusion(l3,l1));


        Message message4 = new Message(user1,l3,"Je vand call of","vas y il é tro bi1" );
        user1.envoyerMessage(message4);

        System.out.println(user1.listerMessages());
        System.out.println(user2.listerMessages());
        System.out.println(user3.listerMessages());
        System.out.println(user4.listerMessages());
        System.out.println(user5.listerMessages());

	}	
	
}
