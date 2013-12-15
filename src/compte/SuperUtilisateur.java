package compte;

/**
 * Classe compte.SuperUtilisateur
 *
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class SuperUtilisateur extends Utilisateur {

    /**
     * @param name
     * @param login
     * @param adresse
     * @param password
     */
    public SuperUtilisateur(String name, String login, String adresse, String password) {
        super(name, login, adresse, password);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "#" + super.toString();
    }
}
