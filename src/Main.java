import compte.*;
import messagerie.*;
import systeme.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Classe Main
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Main {
	public static void main(String[] args) {
		try {
            System.out.println("Jeu d'essais Messagerie");

            Systeme systeme = new Systeme("univ-nantes.fr","toor");
            SuperUtilisateur utilisateur0 = (SuperUtilisateur) systeme.obtenirCompte("root@univ-nantes.fr");
            Utilisateur utilisateur1 = new Utilisateur("utilisateur1", "utilisateur1@univ-nantes.fr", "password1");
            systeme.ajouterUtilisateur(utilisateur1, utilisateur0);
            Utilisateur utilisateur2 = new Utilisateur("utilisateur2", "utilisateur2@univ-nantes.fr", "password2");
            systeme.ajouterUtilisateur(utilisateur2, utilisateur0);
            Utilisateur utilisateur3 = new Utilisateur("utilisateur3", "utilisateur3@univ-nantes.fr", "password3");
            systeme.ajouterUtilisateur(utilisateur3, utilisateur0);

            //chaque utilisateur envoie un message à lui même et aux deux autres utilisateurs
            Compte[] destinataires = new Compte[]{utilisateur1, utilisateur2, utilisateur3};
            Message message1 = new Message(utilisateur1, Arrays.asList(destinataires), "sujet message1", "contenu message1");
            Utilisateur.envoyerMessage(message1);
            Message message2 = new Message(utilisateur2, Arrays.asList(destinataires), "sujet message2", "contenu message2");
            Utilisateur.envoyerMessage(message2);
            Message message3 = new Message(utilisateur3, Arrays.asList(destinataires), "sujet message3", "contenu message3");
            Utilisateur.envoyerMessage(message3);

            //affichage de la liste des messages des trois utilisateurs
            System.out.println("########## liste des messages "+utilisateur1.toString()+" ##########");
            System.out.println(utilisateur1.listerMessages());
            System.out.println("########## liste des messages "+utilisateur2.toString()+" ##########");
            System.out.println(utilisateur2.listerMessages());
            System.out.println("########## liste des messages "+utilisateur3.toString()+" ##########");
            System.out.println(utilisateur3.listerMessages());

            //modification de l'ordre de tri des messages d'un utilisateur
            System.out.println("########## tri des messages par sujets décroissant de "+utilisateur3.toString()+" ##########");
            utilisateur3.trierMessages(BoiteMessage.ORDRE_SUJETS_DECROISSANTS);
            System.out.println("########## liste des messages "+utilisateur3.toString()+" ##########");
            System.out.println(utilisateur3.listerMessages());

            //création et ajout de listes de diffusion au système
            ListeDiffusion listeDiffusion1 = new ListeDiffusion("liste1@univ-nantes.fr",false,utilisateur1);
            systeme.ajouterListeDiffusion(listeDiffusion1);
            ListeDiffusion listeDiffusion2 = new ListeDiffusion("liste2@univ-nantes.fr",false,utilisateur2);
            systeme.ajouterListeDiffusion(listeDiffusion2);
            ListeDiffusion listeDiffusion3 = new ListeDiffusion("liste3@univ-nantes.fr",true,utilisateur3);
            systeme.ajouterListeDiffusion(listeDiffusion3);

            //ajout de nouveaux abonnés à une liste et envoi d'un message à la liste
            systeme.abonnerCompte(listeDiffusion1, utilisateur2, utilisateur2);
            systeme.abonnerCompte(listeDiffusion1, utilisateur3, utilisateur3);
            destinataires = new Compte[]{listeDiffusion1};
            Message messageListe1 = new Message(utilisateur1,Arrays.asList(destinataires),"sujet messageListe1","contenu messageListe1");
            System.out.println("########## envoi d'un message à la liste de diffusion ##########");
            Utilisateur.envoyerMessage(messageListe1);
            //affichage de la liste des messages des trois utilisateurs
            System.out.println("########## liste des messages "+utilisateur1.toString()+" ##########");
            System.out.println(utilisateur1.listerMessages());
            System.out.println("########## liste des messages "+utilisateur2.toString()+" ##########");
            System.out.println(utilisateur2.listerMessages());
            System.out.println("########## liste des messages "+utilisateur3.toString()+" ##########");
            System.out.println(utilisateur3.listerMessages());
            
            //suppression de la liste
            System.out.println("########## liste à accès non restreint ########## ");
            if(systeme.supprimerListeDiffusion(listeDiffusion1,utilisateur2))
            {
                System.out.println("########## "+utilisateur2.getAdresse()+" a supprimé la liste "+listeDiffusion1.getAdresse()+" ##########");
            }
            else
            {
                System.out.println("########## "+utilisateur2.getAdresse()+" ne peut pas supprimer la liste "+listeDiffusion1.getAdresse()+" ##########");
            }
            if(systeme.supprimerListeDiffusion(listeDiffusion1,utilisateur1))
            {
                System.out.println("########## "+utilisateur1.getAdresse()+" a supprimé la liste "+listeDiffusion1.getAdresse()+" ##########");
            }
            else
            {
                System.out.println("########## "+utilisateur1.getAdresse()+" ne peut pas supprimer la liste "+listeDiffusion1.getAdresse()+" ##########");
            }
            if(systeme.supprimerListeDiffusion(listeDiffusion2,utilisateur0))
            {
                System.out.println("########## "+utilisateur0.getAdresse()+" a supprimé la liste "+listeDiffusion2.getAdresse()+" ##########");
            }
            else
            {
                System.out.println("########## "+utilisateur0.getAdresse()+" ne peut pas supprimer la liste "+listeDiffusion2.getAdresse()+" ##########");
            }

            //liste à accès restreint
            System.out.println("########## liste à accès restreint ########## ");
            if(systeme.abonnerCompte(listeDiffusion3,utilisateur1,utilisateur0))
            {
                System.out.println("########## "+utilisateur0.getAdresse()+" à abonné "+utilisateur1.getAdresse()+" à la liste "+listeDiffusion3.getAdresse()+" ##########");
            }
            else
            {
                System.out.println("########## "+utilisateur0.getAdresse()+" ne peut pas abonner "+utilisateur1.getAdresse()+" à la liste "+listeDiffusion3.getAdresse()+" ##########");
            }
            if(systeme.abonnerCompte(listeDiffusion3,utilisateur2,utilisateur2))
            {
                System.out.println("########## "+utilisateur2.getAdresse()+" à abonné "+utilisateur2.getAdresse()+" à la liste "+listeDiffusion3.getAdresse()+" ##########");
            }
            else
            {
                System.out.println("########## "+utilisateur2.getAdresse()+" ne peut pas abonner "+utilisateur2.getAdresse()+" à la liste "+listeDiffusion3.getAdresse()+" ##########");
            }
            if(systeme.abonnerCompte(listeDiffusion3,utilisateur2,utilisateur3))
            {
                System.out.println("########## "+utilisateur3.getAdresse()+" à abonné "+utilisateur2.getAdresse()+" à la liste "+listeDiffusion3.getAdresse()+" ##########");
            }
            else
            {
                System.out.println("########## "+utilisateur3.getAdresse()+" ne peut pas abonner "+utilisateur2.getAdresse()+" à la liste "+listeDiffusion3.getAdresse()+" ##########");
            }

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
        
    }
}
