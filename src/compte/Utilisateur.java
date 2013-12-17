package compte;

import messagerie.*;

import javax.rmi.CORBA.Util;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Classe compte.Utilisateur
 *
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Utilisateur extends Compte {
    protected String name, login, password;
    protected BoiteMessage boiteReception;

    /**
     * Constructeur de la classe Utilisateur
     * @param name, le nom de l'utilisateur
     * @param adresse, l'adresse mail de l'utilisateur
     * @param password, le mot de passe de l'utilisateur
     * @throws Exception, Exception levée en cas d'adresse incorrecte 
     */
    public Utilisateur(String name, String adresse, String password) throws Exception {
        super(adresse);
        this.name = name;
        setLogin();
        this.password = password;
        this.boiteReception = new BoiteMessage();
    }

    /**
     * Accesseur de l'attribut login
     * @return login, le login de l'utilisateur
     */
    public String getLogin() {
        return login;
    }
    
    /**
     * Modificateur du login
     * Le login est défini à partir de l'adresse mail
     */
    public void setLogin() {
    	login = adresse.substring(0,adresse.lastIndexOf("@"));
    }

    /**
     * Accesseur de l'attribut name
     * @return name, le nom de l'utilisateur
     */
    public String getName() {
        return name;
    }   
    
    /**
     * Accesseur de l'attribut password
	 * @return password, le mot de passe de l'utilisateur 
	 */
	public String getPassword() {
		return password;
	}

	/**
     * envoie un message à tous les utilisateurs spécifié dans le message
     * @param message, le message à envoyer
     */
    public static void envoyerMessage(Message message) {
        Set<Utilisateur> destinataires = new LinkedHashSet<Utilisateur>();
        for (Compte compte : message.getDestinataires()) {
            if (compte instanceof Utilisateur) {
                destinataires.add((Utilisateur) compte);
            } else if (compte instanceof ListeDiffusion) {
                Set<Utilisateur> tmp = new LinkedHashSet<Utilisateur>();
                if ((!((ListeDiffusion) compte).estRestreint()) || (message.getEmetteur() instanceof SuperUtilisateur) || (compte.contient(message.getEmetteur()))) {
                    destinataires.addAll(compte.getUtilisateurs());
                }
                tmp.remove(message.getEmetteur());
                destinataires.addAll(tmp);
            }
        }

        for (Utilisateur utilisateur : destinataires) {
            utilisateur.recevoirMessage(message);
        }
    }

    /**
     * Méthode qui réceptionne un message et l'ajoute à la boite de réception
     * @param message, le message reçu
     */
    public void recevoirMessage(Message message) {
        boiteReception.ajouterMessage(message);
    }

    /**
     * Méthode qui liste les intitulés des messages présent dans la boite de réception
     * @return une chaine de caractère contenant les intitulés
     */
    public String listerMessages() {
        return "Bonjour " + login + ",\n" + this.boiteReception.listerMessages();
    }

    /**
     * Méthode utilisé dans la récursion des listes de diffusion
     * @return l'utilisateur
     */
    @Override
    public Set<Utilisateur> getUtilisateurs() {
        Set<Utilisateur> res = new LinkedHashSet<Utilisateur>();
        res.add(this);
        return res;
    }
    

    /**
     * affiche le login et l'adresse de l'utilisateur
     * @return une chaine de caractère
     */
    @Override
    public String toString() {
        return login + " : " + adresse;
    }

    /**
     * teste si le compte passé en paramètre est égal
     * @param compte
     * @return un booléen, true si c'est le compte, false sinon
     */
    @Override
    public boolean contient(Compte compte) {
        return equals(compte);
    }

}
