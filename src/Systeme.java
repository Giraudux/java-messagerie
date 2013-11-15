import java.util.LinkedList;
import java.util.List;

/**
 * Classe Systeme
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Systeme {
    //todo: utilisateur unique (identifié par login) ex: set
    private List<Utilisateur> utilisateurs;
    private List<ListeDiffusion> listesDiffusion;

    public Systeme()
    {
        utilisateurs = new LinkedList<Utilisateur>();
        listesDiffusion = new LinkedList<ListeDiffusion>();
        //todo: ajouter root
    }
}
