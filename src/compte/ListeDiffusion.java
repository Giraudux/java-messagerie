package compte;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Une liste de diffusion permet de rassembler des utilisateurs et d'autres listes.
 * Une liste est la propriété d'un utilisateur (créateur).
 * Une liste peut avoir un accès public ou restreint:
 * la liste a un accès restreint lorsque seuls les membres de la liste ou les administrateurs peuvent y poster un message,
 * la liste a un accès public lorsque tous les utilisateurs du système peuvent y poster un message.
 *
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class ListeDiffusion extends Compte {
    private boolean restreint;
    private Set<Compte> comptes;
    private Utilisateur createur;

    /**
     * Constructeur de la classe ListeDiffusion.
     *
     * @param adresse,   l'adresse de la liste de diffusion
     * @param restreint, statut de la liste : true si l'accès est restreint, sinon false
     * @throws Exception lève une exception lorsque l'adresse est incorrecte
     */
    public ListeDiffusion(String adresse, boolean restreint, Utilisateur createur) throws Exception {
        super(adresse);
        this.comptes = new LinkedHashSet<Compte>();
        this.restreint = restreint;
        this.createur = createur;
    }

    /**
     * Méthode qui retourne l'ensemble des utilisateurs contenus dans une liste de diffusion.
     *
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
     * Affiche tous les utilisateurs abonnés à la liste.
     *
     * @return une chaîne de caractères
     */
    @Override
    public String toString() {
        return comptes.toString();
    }

    /**
     * Ajoute un compte à la liste de diffusion.
     *
     * @param compte, le compte à ajouter
     * @return un booléen, true si l'opération c'est bien déroulée, false sinon
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
     * Supprime un compte de la liste de diffusion.
     *
     * @param compte, le compte à supprimer
     * @return un booléen, true si l'opération c'est bien déroulée, false sinon
     */
    public boolean supprimerCompte(Compte compte) {
        return comptes.remove(compte);
    }

    /**
     * Teste si la liste contient un compte.
     *
     * @param compte, le compte recherché
     * @return un booléen, true si le compte a été trouvé, false sinon
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
     * Teste si l'utilisateur est le créateur de la liste.
     *
     * @param utilisateur, l'utilisateur à tester
     * @return un booléen, true si il est le créateur, false sinon
     */
    public boolean estCreateur(Utilisateur utilisateur) {
        return utilisateur.equals(createur);
    }

    /**
     * Teste si l'accès à la liste est restreint.
     *
     * @return un booléen, true si l'accès est restreint, false sinon
     */
    public boolean estRestreint() {
        return restreint;
    }
}
