package tools;
import java.io.*;


public class EcritureFichier {
	BufferedWriter writer;
	
	public EcritureFichier(){
		writer = null;
	}

	public void ouvrir(String chemin) throws IOException{
	    writer = new BufferedWriter( new FileWriter(chemin));
	   
	}

	public void ecrire(String texte) throws IOException{
		writer.write(texte);
	}

	public void fermer() throws IOException{
        writer.close( );
	}
}