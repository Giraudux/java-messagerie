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
     * @param adresse
     * @param restreint
     */
    public ListeDiffusion(String adresse, boolean restreint) {
        super(adresse);
        this.comptes = new LinkedHashSet<Compte>();
        this.restreint = restreint;
    }

    /**
     * @return
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
     * @return
     */
    @Override
    public String toString() {
        return comptes.toString();
    }

    /**
     * @param compte
     * @return
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
     * @param compte
     * @return
     */
    public boolean supprimerCompte(Compte compte) {
        return comptes.remove(compte);
    }

    /**
     * @param compte
     * @return
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
     * @param utilisateur
     * @return
     */
    public boolean isCreateur(Utilisateur utilisateur) {
        return utilisateur.equals(createur);
    }

    /**
     * @return
     */
    public boolean isRestreint() {
        return restreint;
    }
}
