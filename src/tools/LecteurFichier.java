package tools;
import java.io.*;

/**
 * Classe permettant de lire un fichier texte ligne par ligne.
 * 
 * Le code suivant permet de lire un livre depuis un fichier "fLivre.txt" :
 * <code>
 * LecteurFichier lecteur = new LecteurFichier();
 * if (lecteur.ouvrir("fLivre.txt")) {
 * 	  String ligne;
 *    while ((ligne = lecteur.lireLigne()) != null) {
 *        // ici le traitement de la ligne
 *    }
 *    lecteur.fermer();
 * }
 * else {
 *    // erreur d'ouverture du fichier, fichier non accessible sur le disque
 * }
 * </code>
 * @author Laurent Granvilliers
 * @since 16 septembre 2013
 */
public class LecteurFichier {
	private BufferedReader reader;	// object servant à lire un fichier

	/**
	 * Constructeur.
	 */
	public LecteurFichier() {
		reader = null;
	}

	/**
	 * Ouverture d'un fichier texte.
	 * @param nomFichier nom du fichier.
	 * @return true si l'ouverture a réussi, false sinon.
	 */
	public boolean ouvrir(String nomFichier) {
		try {
			FileReader fr = new FileReader(nomFichier);
			reader = new BufferedReader(fr);
			return true;
		}
		catch(IOException ex) {
			return false;
		}
	}

	/**
	 * Lecture de la prochaine ligne dans le fichier ouvert par le dernier
	 * appel à la fonction ouvrir().
	 * @return la prochaine ligne prise dans le fichier ouvert, ou la référence
	 * null si le fichier a été parcouru en totalité ou an cas d'erreur de lecture.
	 */
	public String lireLigne() {
		try {
			return reader.readLine();
		}
		catch(IOException ex) {
			return null;
		}
	}

	/**
	 * Fermeture du fichier à effectuer après lecture de son contenu.
	 */
	public void fermer() {
		if (reader != null) {
			try {
				reader.close();
			}
			catch(IOException ex) {
			}
		}
	}
}
