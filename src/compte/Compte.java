package compte;

import systeme.Systeme;

import java.util.Set;

/**
 * La classe abstraite Compte représente un compte du sytème de messagerie, identifiée par une adresse unique.
 *
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public abstract class Compte implements Comparable<Compte> {
    protected String adresse;

    /**
     * Constructeur de la classe Compte.
     *
     * @param adresse l'adresse mail du compte
     * @throws Exception, une exception est levée si l'adresse ne correspond pas au pattern défini par le Systeme
     */
    public Compte(String adresse) throws Exception {
        if (adresse.matches(Systeme.ADRESSE_CORRECTE))
            this.adresse = adresse;
        else
            throw new Exception("pattern de l'adresse incorrect : " + adresse);
    }

    /**
     * Accesseur de l'attribut adresse.
     *
     * @return l'adresse du compte
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Méthode de comparaison entre deux comptes,
     * La comparaison s'effectue sur l'adresse en utilisant la méthode compareTo() de la classe String.
     *
     * @param compte le compte à comparer
     * @return le résultat de la comparaison des adresses (String.compareTo(String))
     */
    @Override
    public int compareTo(Compte compte) {
        return adresse.compareTo(compte.getAdresse());
    }

    /**
     * Redéfinition du equals.
     *
     * @param o un objet à comparer
     * @return un booléen true si le compte est égal à l'Object o, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compte)) return false;

        Compte compte = (Compte) o;

        if (adresse != null ? !adresse.equals(compte.adresse) : compte.adresse != null) return false;

        return true;
    }

    /**
     * Renvoie une description de l'objet.
     *
     * @return une chaine de caractère représentant le compte
     */
    @Override
    public abstract String toString();

    /**
     * Retourne l'ensemble des utilisateurs du compte.
     *
     * @return l'ensemble des utilisateurs du compte
     */
    public abstract Set<Utilisateur> getUtilisateurs();

    /**
     * Vérifie si un compte appartient ou est égal au compte.
     *
     * @param compte le compte recherché
     * @return un booléen, true si le compte a été trouvé, sinon false
     */
    public abstract boolean contient(Compte compte);

    /**
     * Redéfinition du hashcode de la classe Compte.
     *
     * @return le hashcode
     */
    @Override
    public int hashCode() {
        return adresse.hashCode();
    }
}
