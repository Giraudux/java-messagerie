package systeme;

import compte.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Classe systeme.Systeme
 *
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Systeme {
    public static final String ADRESSE_CORRECTE = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private Set<Compte> comptes;

    /**
     * @param adresseRoot
     * @param passwordRoot
     */
    public Systeme(String adresseRoot, String passwordRoot) {
        comptes = new LinkedHashSet<Compte>();
        comptes.add(new SuperUtilisateur("root", "root", adresseRoot, passwordRoot));
    }

    /**
     * @param utilisateur
     * @param superUtilisateur
     * @return
     */
    public boolean ajouterUtilisateur(Utilisateur utilisateur, Utilisateur superUtilisateur) {
        if (superUtilisateur instanceof SuperUtilisateur && !comptes.contains(utilisateur)) {
            if (utilisateur instanceof SuperUtilisateur) {
                if (superUtilisateur.getLogin().equals("root")) {
                    return comptes.add(utilisateur);
                }
            } else {
                return comptes.add(utilisateur);
            }
        }
        return false;
    }

    /**
     * @param utilisateur
     * @param superUtilisateur
     * @return
     */
    public boolean supprimerUtilisateur(Utilisateur utilisateur, Utilisateur superUtilisateur) {
        if (superUtilisateur instanceof SuperUtilisateur) {
            if (utilisateur instanceof SuperUtilisateur) {
                if (superUtilisateur.getLogin().equals("root")) {
                    return comptes.remove(utilisateur);
                }
            } else {
                return comptes.remove(utilisateur);
            }
        }
        return false;
    }

    /**
     * @param listeDiffusion
     * @return
     */
    public boolean ajouterListeDiffusion(ListeDiffusion listeDiffusion) {
        return comptes.add(listeDiffusion);
    }

    /**
     * @param listeDiffusion
     * @param utilisateur
     * @return
     */
    public boolean supprimerListeDiffusion(ListeDiffusion listeDiffusion, Utilisateur utilisateur) {
        if (utilisateur instanceof SuperUtilisateur || listeDiffusion.isCreateur(utilisateur)) {
            return comptes.remove(listeDiffusion);
        }
        return false;
    }

    /**
     * @param listeDiffusion
     * @param compte
     * @param utilisateur
     * @return
     */
    public boolean abonnerCompte(ListeDiffusion listeDiffusion, Compte compte, Utilisateur utilisateur) {
        if (utilisateur.equals(compte) || utilisateur instanceof SuperUtilisateur || listeDiffusion.isCreateur(utilisateur)) {
            return listeDiffusion.ajouterCompte(compte);
        }
        return false;
    }

    /**
     * @param listeDiffusion
     * @param compte
     * @param utilisateur
     * @return
     */
    public boolean desabonnerCompte(ListeDiffusion listeDiffusion, Compte compte, Utilisateur utilisateur) {
        if (utilisateur.equals(compte) || utilisateur instanceof SuperUtilisateur || listeDiffusion.isCreateur(utilisateur)) {
            return listeDiffusion.supprimerCompte(compte);
        }
        return false;
    }
}
