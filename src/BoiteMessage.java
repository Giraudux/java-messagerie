import java.util.LinkedList;
import java.util.List;

/**
 * Classe BoiteMessage
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class BoiteMessage {
    List<Message> messages;

    public BoiteMessage(){
        messages = new LinkedList<Message>();
    }
}
