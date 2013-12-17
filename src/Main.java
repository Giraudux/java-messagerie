import compte.*;
import messagerie.*;
import systeme.*;

/**
 * Classe Main
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Main {
	
	public static void main(String[] args) {
        Utilisateur u1,u2;
		try {
			u1 = new Utilisateur("francois","francois.hallereau@systeme.fr","azerty");
			System.out.println(u1.getLogin());
			u2 = new Utilisateur("bob","jksdffffbhffh","azet");
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
        
    }
}
