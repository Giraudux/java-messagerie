package systeme;

import compte.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * La classe Systeme permet de gérer un systeme de messagerie comportant des comptes (utilisateurs et liste de diffusion).
 * Cette classe permet de gérer les interactions entre les utilisateurs (envoie de messages, inscription à une liste de diffusion...).
 *
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Systeme {

    public static final String ADRESSE_CORRECTE = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,6}$";
    private Set<Compte> comptes;


    /**
     * Constructeur de la classe Systeme.
     *
     * @param adresseRoot  l'adresse du root
     * @param passwordRoot le mot de passe du root
     * @throws Exception, exception levée si l'adresse ne correspond pas au pattern spécifié
     */

    public Systeme(String adresseRoot, String passwordRoot) throws Exception {
        comptes = new LinkedHashSet<Compte>();
        comptes.add(new SuperUtilisateur("root", adresseRoot, passwordRoot));

    }

    /**
     * Méthode qui connecte un utilisateur au système.
     *
     * @param adresse  l'adresse de connexion
     * @param password le mot de passe
     * @return l'utilisateur si l'authentification s'est bien déroulée, sinon  retourne null
     */
    public Utilisateur connexion(String adresse, String password) {
        Compte c = obtenirCompte(adresse);
        if (c != null && c instanceof Utilisateur) {
            Utilisateur u = (Utilisateur) c;
            if (password.equals(u.getPassword())) {
                if (c instanceof SuperUtilisateur)
                    return (SuperUtilisateur) u;
                else
                    return u;
            }
        }
        return null;
    }

    /**
     * Méthode qui renvoie le compte associé à l'adresse.
     *
     * @param adresse l'adresse du compte recherché
     * @return le compte correspondant à l'adresse, null si aucun compte trouvé
     */
    public Compte obtenirCompte(String adresse) {
        for (Compte c : comptes) {
            if (c.getAdresse().equals(adresse))
                return c;
        }
        return null;
    }

    /**
     * Ajoute un utilisateur dans le systeme
     *
     * @param utilisateur      l'utilisateur à enregistrer
     * @param superUtilisateur l'administrateur qui effectue l'action
     * @return un booléen, true si l'opération c'est bien déroulée, false sinon
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
     * Supprime un utilisateur du systeme.
     *
     * @param utilisateur      l'utilisateur à supprimer
     * @param superUtilisateur l'administrateur qui effectu l'action
     * @return un booléen, true si l'opération c'est bien déroulée, false sinon
     */
    public boolean supprimerUtilisateur(Utilisateur utilisateur, Utilisateur superUtilisateur) {
        if (superUtilisateur instanceof SuperUtilisateur) {
            if (utilisateur instanceof SuperUtilisateur) {
                if (superUtilisateur.getLogin().equals("root")) {
                    supprimerAbonnement(utilisateur);
                    return comptes.remove(utilisateur);
                }
            } else {
                supprimerAbonnement(utilisateur);
                return comptes.remove(utilisateur);
            }
        }
        return false;
    }

    /**
     * Ajoute une liste de diffusion au systeme.
     *
     * @param listeDiffusion la liste de diffusion à enregistrer
     * @return un booléen, true si l'opération c'est bien déroulée, false sinon
     */
    public boolean ajouterListeDiffusion(ListeDiffusion listeDiffusion) {
        return comptes.add(listeDiffusion);
    }

    /**
     * Supprime une liste de diffusion du systeme.
     *
     * @param listeDiffusion la liste de diffusion à supprimer
     * @param utilisateur    l'utilisateur qui effectue l'action
     * @return un booléen, true si l'opération c'est bien déroulée, false sinon
     */
    public boolean supprimerListeDiffusion(ListeDiffusion listeDiffusion, Utilisateur utilisateur) {
        if (utilisateur instanceof SuperUtilisateur || listeDiffusion.estCreateur(utilisateur)) {
            supprimerAbonnement(listeDiffusion);
            return comptes.remove(listeDiffusion);
        }
        return false;
    }

    /**
     * Abonne un compte à une liste de diffusion.
     *
     * @param listeDiffusion la liste de diffusion à laquelle le compte s'abonne
     * @param compte         le compte qui s'y abonnne
     * @param utilisateur    l'utilisateur qui effectu l'opération
     * @return un booléen, true si l'opération c'est bien déroulée, false sinon
     */
    public boolean abonnerCompte(ListeDiffusion listeDiffusion, Compte compte, Utilisateur utilisateur) {
        if (utilisateur.equals(compte) || utilisateur instanceof SuperUtilisateur || listeDiffusion.estCreateur(utilisateur)) {
            return listeDiffusion.ajouterCompte(compte);
        }
        return false;
    }

    /**
     * Désabonne un compte à une liste de diffusion.
     *
     * @param listeDiffusion la liste de diffusion à laquelle le compte se désabonne
     * @param compte         le compte qui se désabonnne
     * @param utilisateur    l'utilisateur qui effectue l'opération
     * @return un booléen, true si l'opération c'est bien déroulée, false sinon
     */
    public boolean desabonnerCompte(ListeDiffusion listeDiffusion, Compte compte, Utilisateur utilisateur) {
        if (utilisateur.equals(compte) || utilisateur instanceof SuperUtilisateur || listeDiffusion.estCreateur(utilisateur)) {
            return listeDiffusion.supprimerCompte(compte);
        }
        return false;
    }


    /**
     * Méthode qui récupère les listes de diffusion auxquelles est abonné un compte.
     *
     * @param compte le compte dont on veut connaître les abonnements
     * @return l'ensemble des listes de diffusion
     */
    public Set<ListeDiffusion> voirAbonnements(Compte compte) {
        LinkedHashSet<ListeDiffusion> listes = new LinkedHashSet<ListeDiffusion>();
        for (Compte c : comptes) {
            if (c instanceof ListeDiffusion) {
                ListeDiffusion liste = (ListeDiffusion) c;
                if (liste.contient(compte))
                    listes.add(liste);
            }
        }
        return listes;
    }

    /**
     * Supprime tous les abonnements d'un compte.
     *
     * @param compte le compte auquel on souhaite supprimer les abonnements
     */
    public void supprimerAbonnement(Compte compte) {
        Set<ListeDiffusion> listes = voirAbonnements(compte);
        for (ListeDiffusion liste : listes) {
            liste.supprimerCompte(compte);
        }
    }

}
