package messagerie;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe messagerie.BoiteMessage
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class BoiteMessage {
    List<Message> messages;

    public BoiteMessage(){
        messages = new LinkedList<Message>();
    }

    //todo: taille limite boite
    public void addMessage(Message message)
    {
        messages.add(message);
    }
    
    //affiche tous les sujets des messages
    public String listerMessages(){
		String liste = "Bienvenue dans votre boite de réception \n";
		if(!messages.isEmpty()){
			for(Message m : messages){
				liste+=	"Envoyé par "+m.getEmetteur().getLogin()+" | Sujet: "+m.getSujet()+"\n";
			}
			liste+="Fin des messages.\n";
		}
		else{
			liste+="Vous n'avez aucun message...\n";
		}
		return liste;
	}
}