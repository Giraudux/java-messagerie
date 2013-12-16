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
     * @param name
     * @param login
     * @param adresse
     * @param password
     */
    public Utilisateur(String name, String login, String adresse, String password) {
        super(adresse);
        this.name = name;
        this.login = login;
        this.password = password;
        this.boiteReception = new BoiteMessage();
    }

    /**
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param message
     */
    public static void envoyerMessage(Message message) {
        Set<Utilisateur> destinataires = new LinkedHashSet<Utilisateur>();
        for (Compte compte : message.getDestinataires()) {
            if (compte instanceof Utilisateur) {
                destinataires.add((Utilisateur) compte);
            } else if (compte instanceof ListeDiffusion) {
                Set<Utilisateur> tmp = new LinkedHashSet<Utilisateur>();
                if ((!((ListeDiffusion) compte).isRestreint()) || (message.getEmetteur() instanceof SuperUtilisateur) || (compte.contient(message.getEmetteur()))) {
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
     * @param message
     */
    public void recevoirMessage(Message message) {
        boiteReception.addMessage(message);
    }

    /**
     * @return
     */
    public String listerMessages() {
        return "Bonjour " + login + ",\n" + this.boiteReception.listerMessages();
    }

    /**
     * @return
     */
    @Override
    public Set<Utilisateur> getUtilisateurs() {
        Set<Utilisateur> res = new LinkedHashSet<Utilisateur>();
        res.add(this);
        return res;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return login + " : " + adresse;
    }

    /**
     * @param compte
     * @return
     */
    @Override
    public boolean contient(Compte compte) {
        return equals(compte);
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilisateur)) return false;
        if (!super.equals(o)) return false;

        Utilisateur that = (Utilisateur) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;

        return true;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Integer.parseInt(Integer.toString(adresse.hashCode()+login.hashCode()));
    }
}
