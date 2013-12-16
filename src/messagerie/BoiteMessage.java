package messagerie;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * La classe BoiteMessage permet de stoquer les messages des utilisateurs.
 * Il est possible d'ajouter, afficher un message et de trier et lister l'ensemble des messages.
 *
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class BoiteMessage {
    public static final Comparator<Message> ORDRE_DATES_CROISSANTES = new DatesCroissantesComparator();
    public static final Comparator<Message> ORDRE_DATES_DECROISSANTES = new DatesDecroissantesComparator();
    public static final Comparator<Message> ORDRE_NOMS_EMETTEURS_CROISSANTS = new NomsEmetteursCroissantsComparator();
    public static final Comparator<Message> ORDRE_NOMS_EMETTEURS_DECROISSANTS = new NomsEmetteursDecroissantsComparator();
    List<Message> messages;

    /**
     * Constructeur de la classe BoiteMessage, crée une nouvelle boîte de méssages vide.
     */
    public BoiteMessage() {
        messages = new LinkedList<Message>();
    }

    /**
     * Ajoute un message à la boîte.
     *
     * @param message le message à ajouter
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Retourne la liste les messages de la boîte sous la forme "| De: émetteur du message | Date: date d'envoi | Sujet: sujet du message".
     *
     * @return la liste des messages de la boîte
     */
    public String listerMessages() {
        String liste = "Bienvenue dans votre boite de réception.\n";
        if (!messages.isEmpty()) {
            int i = 0;
            for (Message m : messages) {
                liste += i + "| De: " + m.getEmetteur().getAdresse() + " | Date: " + m.getDate().toString() + " | Sujet: " + m.getSujet() + "\n";
                i++;
            }
            liste += "Fin des messages.\n";
        } else {
            liste += "Vous n'avez aucun message.\n";
        }
        return liste;
    }

    /**
     * Retourne le ième message de la boîte.
     *
     * @param i indice du message à retourner
     * @return ième message de la boîte
     */
    public String afficherMessage(int i) {
        try {
            return messages.get(i).toString();
        } catch (IndexOutOfBoundsException e) {
            return "Ce message n'existe pas.";
        }
    }

    /**
     * Tri les message de la boîte selon l'ordre (Comparator) passé en paramètre.
     *
     * @param comparator l'ordre de tri
     */
    public void trierMessages(Comparator<Message> comparator) {
        Collections.sort(messages, comparator);
    }

    public static class DatesCroissantesComparator implements Comparator<Message> {
        @Override
        public int compare(Message message1, Message message2) {
            return message1.getDate().compareTo(message2.getDate());
        }
    }

    public static class DatesDecroissantesComparator implements Comparator<Message> {
        @Override
        public int compare(Message message1, Message message2) {
            return message2.getDate().compareTo(message1.getDate());
        }
    }

    public static class NomsEmetteursCroissantsComparator implements Comparator<Message> {
        @Override
        public int compare(Message message1, Message message2) {
            return message1.getEmetteur().getName().compareTo(message2.getEmetteur().getName());
        }
    }

    public static class NomsEmetteursDecroissantsComparator implements Comparator<Message> {
        @Override
        public int compare(Message message1, Message message2) {
            return message1.getEmetteur().getName().compareTo(message2.getEmetteur().getName());
        }
    }
}
