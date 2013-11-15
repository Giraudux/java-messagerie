import java.util.LinkedList;
import java.util.List;

/**
 * Classe Message
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Message {
    private List<Compte> destinataires;

    public Message()
    {
        destinataires = new LinkedList<Compte>();
        //todo: ajouter destinataire
    }
}
