package systeme;

import compte.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import tools.EcritureFichier;
import tools.LecteurFichier;

/**
 * Classe systeme.Systeme
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class Systeme {
    //todo: utilisateur unique (identifié par login) ex: set
    private Set<Utilisateur> utilisateurs;
    private Set<ListeDiffusion> listesDiffusion;

    public Systeme(String adresseRoot, String passwordRoot)
    {
        utilisateurs = new LinkedHashSet<Utilisateur>();
        listesDiffusion = new LinkedHashSet<ListeDiffusion>();
        LecteurFichier lecteur = new LecteurFichier();
    	File sys_dir = new File("Systeme");
    	System.out.println(sys_dir.getAbsolutePath());
    	if(sys_dir.exists() && sys_dir.isDirectory())
    	{
    		lecteur.ouvrir("Systeme/utilisateurs.txt");
    		String ligne;
    		String json="";
    		//on récupère les utilisateurs
    		while((ligne=lecteur.lireLigne())!=null){
    			json+=ligne;
    		}
    		lecteur.fermer();
    		JSONArray array = new JSONArray(json);
    		for(int i =0;i<array.length();i++){
    			JSONObject o = array.getJSONObject(i);
    			if(o.getBoolean("admin"))
    				utilisateurs.add(new SuperUtilisateur(o.getString("login"),o.getString("adresse"),o.getString("password")));
    			else
    				utilisateurs.add(new Utilisateur(o.getString("login"),o.getString("adresse"),o.getString("password")));
    		}
    		//on récupère les listes de diffusions
    		lecteur.ouvrir("Systeme/listes.txt");
    		json ="";
    		while((ligne=lecteur.lireLigne())!=null){
    			json+=ligne;
    		}
    		lecteur.fermer();
    		if(!json.equals("")){
	    		array = new JSONArray(json);
	    		for(int i =0;i<array.length();i++){
	    			JSONObject o = array.getJSONObject(i);
	    			ListeDiffusion liste = new ListeDiffusion(o.getString("adresse"),o.getBoolean("restreint"));
	    			JSONArray comptes =o.getJSONArray("comptes");
	    			for(int j =0;j<comptes.length();j++){
	    				JSONObject c = comptes.getJSONObject(j);
	    				if(c.getBoolean("isUser")){
	    					Utilisateur compteu;
	    					if((compteu=obtenirUtilisateur(c.getString("adresse")))!=null);
	    						liste.ajouterCompte(compteu);
	    				}
	    				else{
	    					ListeDiffusion comptel;
	    					if((comptel=obtenirListeDiffusion(c.getString("adresse")))!=null);
	    						liste.ajouterCompte(comptel);
	    				}
	    			}
	    			listesDiffusion.add(liste);
	    		}
    		}
    	}
    	else
    	{
    		sys_dir.mkdir();
    		File sys_user = new File("Systeme/utilisateurs.txt");
    		File sys_list = new File("Systeme/listes.txt");
	        utilisateurs.add(new SuperUtilisateur("root",adresseRoot,passwordRoot));
	        try {
				sys_user.createNewFile();
				sys_list.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        EcritureFichier ecriture = new EcritureFichier();
	        try {
				ecriture.ouvrir(sys_user.getCanonicalPath());
				ecriture.ecrire(new JSONArray().put(new JSONObject().put("admin", true).put("login", "root").put("adresse", adresseRoot).put("password",passwordRoot)).toString());
				ecriture.fermer();
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

    public boolean addUtilisateur(Utilisateur utilisateur, Utilisateur superUtilisateur)
    {
    	if(!utilisateurs.contains(utilisateur))
    	{
	        if(superUtilisateur instanceof SuperUtilisateur)
	        {
	            if(utilisateur instanceof SuperUtilisateur)
	            {
	                if(superUtilisateur.getLogin().equals("root"))
	                {	
	                	ecrireUtilisateur(utilisateur,true);
	                    return utilisateurs.add(utilisateur);
	                }
	            }
	            else
	            {
	            	ecrireUtilisateur(utilisateur,false);
	                return utilisateurs.add(utilisateur);
	            }
	        }
    	}
        return false;
    }

    public boolean supprimerUtilisateur(Utilisateur utilisateur, Utilisateur superUtilisateur)
    {
        if(superUtilisateur instanceof SuperUtilisateur)
        {
            if(utilisateur instanceof SuperUtilisateur)
            {
                if(superUtilisateur.getLogin().equals("root"))
                {
                    return utilisateurs.remove(utilisateur);
                }
            }
            else
            {
                return utilisateurs.remove(utilisateur);
            }
        }
        return false;
    }

    public Utilisateur obtenirUtilisateur(String adresse)
    {
    	for(Utilisateur u : utilisateurs){
    		if(u.getAdresse().equals(adresse))
    			return u;
    	}
    	return null;
    }
    
    //todo mettre en fr les noms des méthodes
    public boolean addListeDiffusion(ListeDiffusion listeDiffusion)
    {
        return listesDiffusion.add(listeDiffusion);
    }

    public boolean supprimerListeDiffusion(ListeDiffusion listeDiffusion,Utilisateur utilisateur)
    {
        if(utilisateur instanceof SuperUtilisateur || listeDiffusion.isCreateur(utilisateur)){
            return listesDiffusion.remove(listeDiffusion);
        }
        return false;
    }

    public ListeDiffusion obtenirListeDiffusion(String adresse)
    {
    	for(ListeDiffusion l : listesDiffusion){
    		if(l.getAdresse().equals(adresse))
    			return l;
    	}
    	return null;
    }
    
    public boolean abonnerCompte(ListeDiffusion listeDiffusion,Compte compte, Utilisateur utilisateur)
    {
        if(utilisateur.equals(compte) || utilisateur instanceof SuperUtilisateur || listeDiffusion.isCreateur(utilisateur) )
        {
            return listeDiffusion.ajouterCompte(compte);
        }
        return false;
    }

    public boolean desabonnerCompte(ListeDiffusion listeDiffusion,Compte compte,Utilisateur utilisateur)
    {
        if(utilisateur.equals(compte) || utilisateur instanceof SuperUtilisateur || listeDiffusion.isCreateur(utilisateur) )
        {
            return listeDiffusion.supprimerCompte(compte);
        }
        return false;
    }
    
    public void ecrireUtilisateur(Utilisateur utilisateur,boolean admin)
    {
    	File sys_user = new File("Systeme/utilisateurs.txt");
    	LecteurFichier lecteur = new LecteurFichier();
		try {
			lecteur.ouvrir(sys_user.getCanonicalPath());
			String ligne;
			String json="";
			//on récupère les utilisateurs
			while((ligne=lecteur.lireLigne())!=null){
				json+=ligne;
			}
			lecteur.fermer();
			sys_user.delete();
			sys_user.createNewFile();
	    	JSONArray array = new JSONArray(json);
	    	EcritureFichier ecriture = new EcritureFichier();
			ecriture.ouvrir("Systeme/utilisateurs.txt");
			ecriture.ecrire(array.put(new JSONObject().put("admin", admin).put("login",utilisateur.getLogin()).put("adresse", utilisateur.getAdresse()).put("password",utilisateur.getPassword())).toString());
			ecriture.fermer();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }


}
