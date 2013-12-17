package compte;

import java.util.Set;

/**
 * Classe compte.Compte
 *
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public abstract class Compte implements Comparable<Compte> {
    protected String adresse;
    public static final String ADRESSE_CORRECTE = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    
    /**Constructeur de la classe Compte
     * @param adresse, l'adresse mail du compte
     * @throws Exception, une exception est levée si elle ne correspond pas au pattern suivant : ^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$
     */
    public Compte(String adresse) throws Exception{
    	if(adresse.matches(ADRESSE_CORRECTE))
    		this.adresse = adresse;
    	else
    		throw new Exception("pattern de l'adresse incorrect : "+adresse);
    }

    /**Accesseur de l'attribut adresse
     * @return l'adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * méthode de comparaison entre deux comptes
     * La comparaison s'effectue sur l'adresse en utilisant l'ordre alphabétique 
     * @param compte, le compte à comparer
     * @return 1 si this > compte,-1 si this < compte et 0 sinon
     */
    @Override
    public int compareTo(Compte compte) {
        return adresse.compareTo(compte.getAdresse());
    }

    /**
     * redéfinition du equals
     * @param o, un objet
     * @return un booléen true si égal, false sinon
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
     * renvoie une description de l'objet
     * @return une chaine de caractère
     */
    @Override
    public abstract String toString();

    /**
     * retourne un ensemble d'utilisateurs
     * @return une chaine de caractère
     */
    public abstract Set<Utilisateur> getUtilisateurs();

    /**
     * vérifie si un compte appartient ou est égal au compte
     * @param compte, le compte à tester
     * @return un booléen
     */
    public abstract boolean contient(Compte compte);

    /**
     * redéfinition du hashcode de la classe compte
     * @return le hashcode
     */
    @Override
    public int hashCode() {
        return adresse.hashCode();
    }
}
