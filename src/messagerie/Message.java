package messagerie;

import compte.Compte;
import compte.Utilisateur;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Classe messagerie.Message
 *
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Message {
    private Set<Compte> destinataires;
    private Utilisateur emetteur;
    private String sujet;
    private String contenu;
    private Calendar date;

    /**
     * @param emetteur
     * @param destinataires
     * @param sujet
     * @param contenu
     */
    public Message(Utilisateur emetteur, Collection<Compte> destinataires, String sujet, String contenu) {
        this.destinataires = new LinkedHashSet<Compte>(destinataires);
        this.emetteur = emetteur;
        this.sujet = sujet;
        this.contenu = contenu;
        date = Calendar.getInstance();
    }

    /**
     * @return
     */
    public Set<Compte> getDestinataires() {
        return this.destinataires;
    }

    /**
     * @return
     */
    public Utilisateur getEmetteur() {
        return emetteur;
    }

    /**
     * @return
     */
    public String getSujet() {
        return sujet;
    }

    /**
     * @return
     */
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Sujet: ").append(sujet).append("\nDe: <").append(emetteur.getAdresse()).append(">\nDate: ").append(date.toString()).append("\nPour:");
        for (Compte compte : destinataires) {
            res.append(" ").append(compte.getAdresse());
        }
        res.append("\n\n").append(contenu);
        return res.toString();
    }
}
