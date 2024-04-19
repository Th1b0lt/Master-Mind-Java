package projet.source.projet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Plateau {
    private ArrayList<Couleur> listeCouleurs;
    private Combinaison[] plateau;
   

    public Plateau(int nbrPionts,int nbrCoups,int nbrCouleurs, boolean memeCouleur){
        this.plateau= new Combinaison[nbrCoups];

    }

    public void ajouterLigne(int numTour, Combinaison c){
        this.plateau[numTour]=c;
    }
    
    
    @Override
    public String toString() {
        String result ="";
        if(plateau!=null){
            for (int i = 0; i < plateau.length && plateau[i] != null; i++) {
                result+="\nTour numéro "+(i+1)+":    "+plateau[i].toString()+"\n";
            }
        }
        return result;
    }
}
