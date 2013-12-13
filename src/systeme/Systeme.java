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
        utilisateurs.add(new SuperUtilisateur("root",adresseRoot,passwordRoot));
    }

    public boolean addUtilisateur(Utilisateur utilisateur, Utilisateur superUtilisateur)
    {
        if(superUtilisateur instanceof SuperUtilisateur)
        {
            if(utilisateur instanceof SuperUtilisateur)
            {
                if(superUtilisateur.getLogin().equals("root"))
                {
                    return utilisateurs.add(utilisateur);
                }
            }
            else
            {
                return utilisateurs.add(utilisateur);
            }
        }
        return false;
    }

    public boolean supprimerUtilisateur(Utilisateur utilisateur, Utilisateur superUtilisateur)
    {
        if(superUtilisateur instanceof SuperUtilisateur)
        {
            if(utilisateur instanceof SuperUtilisateur)
            {
                if(superUtilisateur.getLogin().equals("root"))
                {
                    return utilisateurs.remove(utilisateur);
                }
            }
            else
            {
                return utilisateurs.remove(utilisateur);
            }
        }
        return false;
    }

    //todo mettre en fr les noms des méthodes
    public boolean addListeDiffusion(ListeDiffusion listeDiffusion)
    {
        return listesDiffusion.add(listeDiffusion);
    }

    public boolean supprimerListeDiffusion(ListeDiffusion listeDiffusion,Utilisateur utilisateur)
    {
        if(utilisateur instanceof SuperUtilisateur || listeDiffusion.isCreateur(utilisateur)){
            return listesDiffusion.remove(listeDiffusion);
        }
        return false;
    }

    public boolean abonnerCompte(ListeDiffusion listeDiffusion,Compte compte, Utilisateur utilisateur)
    {
        if(utilisateur.equals(compte) || utilisateur instanceof SuperUtilisateur || listeDiffusion.isCreateur(utilisateur) )
        {
            return listeDiffusion.ajouterCompte(compte);
        }
        return false;
    }

    public boolean desabonnerCompte(ListeDiffusion listeDiffusion,Compte compte,Utilisateur utilisateur)
    {
        if(utilisateur.equals(compte) || utilisateur instanceof SuperUtilisateur || listeDiffusion.isCreateur(utilisateur) )
        {
            return listeDiffusion.supprimerCompte(compte);
        }
        return false;
    }


}
