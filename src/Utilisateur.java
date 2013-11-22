import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Classe Utilisateur
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Utilisateur extends Compte{
    protected String login, password;
    protected BoiteMessage boiteReception;
    protected Set<ListeDiffusion> listesDiffusion;

    /**
     * Constructeur de la classe Utilisateur
     */
    public Utilisateur(String login, String adresse, String password) {
        super(adresse);
        this.login = login;
        this.password = password;
        this.boiteReception = new BoiteMessage();
        listesDiffusion = new LinkedHashSet<ListeDiffusion>();
    }

    public boolean envoyerMessage(Message message, Compte compte)
    {
        if(compte instanceof Utilisateur)
        {
            ((Utilisateur) compte).recevoirMessage(message);
        }
        else if(compte instanceof ListeDiffusion)
        {
            for(Utilisateur utilisateur : compte.getUtilisateurs())
            {
                utilisateur.recevoirMessage(message);
            }
        }
        else//todo: a revoir
        {
            return false;
        }
        return true;
    }

    public void recevoirMessage(Message message)
    {
        boiteReception.addMessage(message);
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilisateur)) return false;

        Utilisateur that = (Utilisateur) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        //if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return login+'\n'+password;
    }*/

    //ajouter/supprimer liste de diffusion
}

