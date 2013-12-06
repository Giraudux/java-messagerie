package compte;

import messagerie.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Classe compte.Utilisateur
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Utilisateur extends Compte{
    protected String login, password;
    protected BoiteMessage boiteReception;
    protected Set<ListeDiffusion> listesDiffusion;

    /**
     * Constructeur de la classe compte.Utilisateur
     */
    public Utilisateur(String login, String adresse, String password) {
        super(adresse);
        this.login = login;
        this.password = password;
        this.boiteReception = new BoiteMessage();
        listesDiffusion = new LinkedHashSet<ListeDiffusion>();
    }

	public String getLogin(){
		return login;
	} 

    public void envoyerMessage(Message message)
    {
		Compte compte = message.getDestinataires();
        for(Utilisateur utilisateur : compte.getUtilisateurs())
        {
            utilisateur.recevoirMessage(message);
        }
    }

    public void recevoirMessage(Message message)
    {
        boiteReception.addMessage(message);
    }

	public String listerMessages(){
		return "Bonjour "+login+",\n"+this.boiteReception.listerMessages();
	}

    @Override
    public Set<Utilisateur> getUtilisateurs() {
        Set<Utilisateur> res = new LinkedHashSet<Utilisateur>();
        res.add(this);
        return res;
    }

    @Override
    public String toString()
    {
        return login+" : "+adresse;
    }

    public boolean ajouterListeDiffusion(ListeDiffusion listeDiffusion)
    {
        return listesDiffusion.add(listeDiffusion);
    }

    public boolean ajouterCompteListeDiffusion(ListeDiffusion listeDiffusion, Compte compte){
        return listeDiffusion.ajouterCompte(compte);
    }

    public boolean supprimerCompteListeDiffusion(ListeDiffusion listeDiffusion,Compte compte){
        //todo: tester si createur de la liste
        if(listesDiffusion.contains(listeDiffusion))
        {
            return listeDiffusion.supprimerCompte(compte);
        }
        else
        {
            return false;
        }
    }

    public boolean supprimerListeDiffusion(ListeDiffusion listeDiffusion)
    {
        return listesDiffusion.remove(listeDiffusion);
    }

    @Override
    public boolean contient(Compte compte)
    {
        return equals(compte);
    }
}

