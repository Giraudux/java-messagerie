import java.util.LinkedList;
import java.util.List;

/**
 * Classe ListeDiffusion
 * @author Alexis Giraudet, François Hallereau
 * @version 1.0
 */
public class ListeDiffusion extends Compte{

    private List<Compte> comptes;

    public ListeDiffusion() {
        this.comptes = new LinkedList<Compte>();
    }
}
