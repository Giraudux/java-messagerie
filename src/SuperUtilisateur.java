/**
 * Classe SuperUtilisateur
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class SuperUtilisateur extends Utilisateur {

    public SuperUtilisateur(String adresse, String password) {
        super("root" , adresse, password);
    }
}
