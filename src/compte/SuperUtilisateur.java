package compte;

/**
 * Un SuperUtilisateur est un Utilisateur disposant de privilèges particulires.
 *
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class SuperUtilisateur extends Utilisateur {

    /**
     * Constructeur de la classe SuperUtilisateur.
     *
     * @param name     le nom du super utilisateur
     * @param adresse  l'adresse mail du super utilisateur
     * @param password le mot de passe du super utilisateur
     * @throws Exception, Exception levée en cas d'adresse incorrecte
     */
    public SuperUtilisateur(String name, String adresse, String password) throws Exception {
        super(name, adresse, password);
    }

    /**
     * Redéfinition du toString.
     *
     * @return "#"+le toString de la classe Utilisateur
     */
    @Override
    public String toString() {
        return "#" + super.toString();
    }
}
