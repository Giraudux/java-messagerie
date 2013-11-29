package systeme;

import compte.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Classe systeme.Systeme
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Systeme {
    //todo: utilisateur unique (identifié par login) ex: set
    private Set<Utilisateur> utilisateurs;
    private Set<ListeDiffusion> listesDiffusion;

    public Systeme(String adresseRoot, String passwordRoot)
    {
        utilisateurs = new LinkedHashSet<Utilisateur>();
        listesDiffusion = new LinkedHashSet<ListeDiffusion>();
        addUtilisateur(new SuperUtilisateur(adresseRoot,passwordRoot));
    }

    public boolean addUtilisateur(Utilisateur utilisateur)
    {
        return utilisateurs.add(utilisateur);
    }

    public boolean addListeDiffusion(ListeDiffusion listeDiffusion)
    {
        return listesDiffusion.add(listeDiffusion);
    }
}
