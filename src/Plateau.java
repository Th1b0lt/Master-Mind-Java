package projet.source.projet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Plateau {
    private ArrayList<Couleur> listeCouleurs;
    private Combinaison[] plateau;
    private final Combinaison codeSecret;
   

    public Plateau(int nbrPionts,int nbrCoups,int nbrCouleurs, boolean memeCouleur){
        this.plateau= new Combinaison[nbrCoups];
        this.codeSecret= new Combinaison(nbrPionts,nbrCouleurs,memeCouleur,true);
        //Decomenter pour tester
        System.out.println(" la combinaison est : "+ codeSecret.toString());


    }

    public void ajouterLigne(int numTour, Combinaison c){
        this.plateau[numTour]=c;
    }
    public Combinaison getCodeSecret(){
        return codeSecret;
    }
    
    @Override
    public String toString() {
        String result ="";
        if(plateau!=null){
            for (int i = 0; i < plateau.length && plateau[i] != null; i++) {
                result+="\nTour numéro "+(i+1)+":    "+plateau[i].toString()+ plateau[i].getCompareDifficile(codeSecret)+"\n";
            }
        }
        return result;
    }
}
