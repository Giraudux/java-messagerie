package messagerie;

import compte.Compte;
import compte.Utilisateur;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * La classe Message représente un message sous la forme: sujet, date, contenu, émetteur et destinataires.
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
     * Constructeur de la classe Message, crée un nouveau message avec un émetteur, un sujet, des destinataires et un contenu (initialise la date au moment de la création du message).
     *
     * @param emetteur      l'émetteur du message
     * @param destinataires les destinataires du message
     * @param sujet         le sujet du message
     * @param contenu       le contenu du message
     */
    public Message(Utilisateur emetteur, Collection<Compte> destinataires, String sujet, String contenu) {
        this.destinataires = new LinkedHashSet<Compte>(destinataires);
        this.emetteur = emetteur;
        this.sujet = sujet;
        this.contenu = contenu;
        date = Calendar.getInstance();
    }

    /**
     * Retourne les destinataires du message.
     *
     * @return les destinataires du message
     */
    public Set<Compte> getDestinataires() {
        return this.destinataires;
    }

    /**
     * Retourne l'émetteur du message.
     *
     * @return l'émetteur du message
     */
    public Utilisateur getEmetteur() {
        return emetteur;
    }

    /**
     * Retourne le sujet du message.
     *
     * @return le sujet du message
     */
    public String getSujet() {
        return sujet;
    }

    /**
     * Retourne la date de création du message.
     *
     * @return la date de création du message
     */
    public String getDate() {
        return date.get(Calendar.HOUR)+":"+date.get(Calendar.MINUTE)+" "+date.get(Calendar.DAY_OF_MONTH)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR);
    }

    /**
     * Retourne l'ensemble des informations du message sous forme de chaîne de caractères.
     *
     * @return le message sous forme de chaîne de caractères
     */
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Sujet: ").append(sujet).append("\nDe: <").append(emetteur.getAdresse()).append(">\nDate: ").append(getDate()).append("\nPour:");
        for (Compte compte : destinataires) {
            res.append(" ").append(compte.getAdresse());
        }
        res.append("\n\n").append(contenu);
        return res.toString();
    }
}
