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
    private Set<Compte> destinataires;
    private Utilisateur emetteur;

    public Message()
    {
        destinataires = new LinkedHashSet<Compte>();
        //todo: ajouter destinataire
    }
}
