package compte;

import java.util.Set;

/**
 * Classe compte.Compte
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public abstract class Compte implements Comparable<Compte> {
    protected String adresse;

    public Compte(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse()
    {
        return adresse;
    }



    @Override
    public int compareTo(Compte compte) {
        return adresse.compareTo(compte.getAdresse());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compte)) return false;

        Compte compte = (Compte) o;

        if (adresse != null ? !adresse.equals(compte.adresse) : compte.adresse != null) return false;

        return true;
    }

    @Override
    public abstract String toString();
    public abstract Set<Utilisateur> getUtilisateurs();
    public abstract boolean contient(Compte compte);
}
