import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Classe ListeDiffusion
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class ListeDiffusion extends Compte{

    private Set<Compte> comptes;

    public ListeDiffusion(String adresse) {
        super(adresse);
        this.comptes = new LinkedHashSet<Compte>();
    }

    public Set<Utilisateur> getUtilisateurs()
    {
        Set<Utilisateur> utilisateurs = new LinkedHashSet<Utilisateur>();
        for(Compte compte : comptes)
        {
            if(compte instanceof Utilisateur)
            {
                utilisateurs.add((Utilisateur) compte);
            }
            else if(compte instanceof ListeDiffusion)
            {
                utilisateurs.addAll(((ListeDiffusion) compte).getUtilisateurs());
            }
        }
        return utilisateurs;
    }

    //ajouter/supprimer compte
}
