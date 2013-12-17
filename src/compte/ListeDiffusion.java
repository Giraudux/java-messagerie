package compte;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Classe compte.ListeDiffusion
 *
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class ListeDiffusion extends Compte {
    private boolean restreint;
    private Set<Compte> comptes;
    private Utilisateur createur;

    /**
     * Constructeur de la classe ListeDiffusion
     * @param adresse, l'adresse de la liste de diffusion
     * @param restreint, statut de la liste : true si privé, false sinon
     * @throws Exception 
     */
    public ListeDiffusion(String adresse, boolean restreint) throws Exception {
        super(adresse);
        this.comptes = new LinkedHashSet<Compte>();
        this.restreint = restreint;
    }

    /**
     * Méthode qui retourne l'ensemble des utilisateurs contenu dans une liste de diffusion
     * @return l'ensemble des utilisateurs
     */
    @Override
    public Set<Utilisateur> getUtilisateurs() {
        Set<Utilisateur> utilisateurs = new LinkedHashSet<Utilisateur>();
        for (Compte compte : comptes) {
            if (!(compte instanceof ListeDiffusion)) {
                utilisateurs.addAll(compte.getUtilisateurs());
            }
        }
        return utilisateurs;
    }

    /**
     * Affiche tous les utilisateurs abonné à la liste
     * @return une chaine de caractère
     */
    @Override
    public String toString() {
        return comptes.toString();
    }

    /**
     * ajoute un compte à la liste de diffusion
     * @param compte, le compte à ajouter
     * @return un booléen, true si l'opération c'est bien déroulé, false sinon 
     */
    public boolean ajouterCompte(Compte compte) {
        if (compte instanceof ListeDiffusion) {
            if (compte.contient(this)) {
                return false;
            } else {
                return comptes.add(compte);
            }
        } else {
            return comptes.add(compte);
        }
    }

    /**
     * supprime un compte à la liste de diffusion
     * @param compte, le compte à supprimer
     * @return un booléen, true si l'opération c'est bien déroulé, false sinon 
     */
    public boolean supprimerCompte(Compte compte) {
        return comptes.remove(compte);
    }

    /**
     * teste si la liste contient un compte
     * @param compte, le compte à tester
     * @return un booléen, true si il est inclus, false sinon
     */
    @Override
    public boolean contient(Compte compte) {
        for (Compte c : comptes) {
            if (compte instanceof ListeDiffusion) {
                if (c.getAdresse().equals(compte.getAdresse()))
                    return true;
            }
            if (c.contient(compte)) {
                return true;
            }
        }
        return false;
    }

    /**
     * teste si l'utilisateur à créer la liste
     * @param utilisateur, l'utilisateur à tester
     * @return un booléen, true si il est le créateur, false sinon
     */
    public boolean isCreateur(Utilisateur utilisateur) {
        return utilisateur.equals(createur);
    }

    /**
     * teste si la liste de diffusion est privé
     * @return un booléen, true si l'accès est privé, false sinon
     */
    public boolean isRestreint() {
        return restreint;
    }
}
