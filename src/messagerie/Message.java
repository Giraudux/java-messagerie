package messagerie;

import compte.Compte;
import compte.Utilisateur;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Classe messagerie.Message
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Message {
    private Compte destinataires;
    private Utilisateur emetteur;
    private String sujet;
    private String contenu;

    public Message(Utilisateur emetteur,Compte destinataires,String sujet,String contenu)
    {
        this.destinataires = destinataires;
        this.emetteur=emetteur;
        this.sujet=sujet;
        this.contenu=contenu;       
    }
    
    public Compte getDestinataires(){
		return this.destinataires;
	}
    
    public Utilisateur getEmetteur(){
		return emetteur;	
	}
	
	public String getSujet(){
		return sujet;
	}

    public String toString() {
        return "Emetteur : "+emetteur.getAdresse()+"\nDestinataires : "+destinataires.getAdresse()+"\n+Sujet : "+sujet+"\n\n"+contenu;
    }

}
