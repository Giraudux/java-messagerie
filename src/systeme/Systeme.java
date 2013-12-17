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
     * Constructeur de la classe Systeme
     * @param adresseRoot, l'adresse du root 
     * @param passwordRoot, le mot de passe du root
     * @throws Exception, exception levée si l'adresse ne correspond pas au pattern spécifié 
     */

    public Systeme(String adresseRoot, String passwordRoot) throws Exception {
        comptes = new LinkedHashSet<Compte>();
        comptes.add(new SuperUtilisateur("root", adresseRoot, passwordRoot));

    }

    /**
     * Ajoute un utilisateur dans le systeme
     * @param utilisateur, l'utilisateur à enregistrer
     * @param superUtilisateur, l'administrateur qui effectue l'action
     * @return un booléen, true si l'opération c'est bien déroulé, false sinon
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
     * Supprime un utilisateur dans le systeme
     * @param utilisateur, l'utilisateur à supprimer
     * @param superUtilisateur, l'administrateur qui effectue l'action
     * @return un booléen, true si l'opération c'est bien déroulé, false sinon
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
     * Ajoute une liste de diffusion dans le systeme
     * @param listeDiffusion, la liste de diffusion à enregistrer
     * @return un booléen, true si l'opération c'est bien déroulé, false sinon
     */
    public boolean ajouterListeDiffusion(ListeDiffusion listeDiffusion) {
        return comptes.add(listeDiffusion);
    }
 
    /**
     * Supprime une liste de diffusion dans le systeme
     * @param listeDiffusion, la liste de diffusion à supprimer
     *  @param utilisateur, l'utilisateur qui effectue l'action
     * @return un booléen, true si l'opération c'est bien déroulé, false sinon
     */
    public boolean supprimerListeDiffusion(ListeDiffusion listeDiffusion, Utilisateur utilisateur) {
        if (utilisateur instanceof SuperUtilisateur || listeDiffusion.isCreateur(utilisateur)) {
            return comptes.remove(listeDiffusion);
        }
        return false;
    }

    /**
     * Abonne un utilisateur à une liste de diffusion
     * @param listeDiffusion, la liste de diffusion auquel le compte s'abonne
     * @param compte, le compte qui s'y abonnne
     * @param utilisateur, l'utilisateur qui effectue l'opération
     * @return un booléen, true si l'opération c'est bien déroulé, false sinon
     */
    public boolean abonnerCompte(ListeDiffusion listeDiffusion, Compte compte, Utilisateur utilisateur) {
        if (utilisateur.equals(compte) || utilisateur instanceof SuperUtilisateur || listeDiffusion.isCreateur(utilisateur)) {
            return listeDiffusion.ajouterCompte(compte);
        }
        return false;
    }

    /**
     * Désabonne un utilisateur à une liste de diffusion
     * @param listeDiffusion, la liste de diffusion auquel le compte se désabonne
     * @param compte, le compte qui se désabonnne
     * @param utilisateur, l'utilisateur qui effectue l'opération
     * @return un booléen, true si l'opération c'est bien déroulé, false sinon
     */
    public boolean desabonnerCompte(ListeDiffusion listeDiffusion, Compte compte, Utilisateur utilisateur) {
        if (utilisateur.equals(compte) || utilisateur instanceof SuperUtilisateur || listeDiffusion.isCreateur(utilisateur)) {
            return listeDiffusion.supprimerCompte(compte);
        }
        return false;
    }
}
