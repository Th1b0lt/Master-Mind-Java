package Projet.source.projet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Plateau {
    private ArrayList<Couleur> listeCouleurs;
   // private final Combinaison codeSecret;
   // private Combinaison[] plateau;
    private int d;
    private int nbrCouleurs=8;
    private int nbrPionts=4;
    private boolean memeCouleur=true;
    private int nbrCoups=12;

    public Plateau(){
        int choix,d;
        ArrayList<Couleur> listeComplete = new ArrayList<>(Arrays.asList(Couleur.values()));
        Collections.shuffle(listeComplete);
        d=Integer.parseInt(System.console().readLine("Choisissez la difficulté(taper le nombre correspondant)\n" + //
                        ": Facile:0 \n" + //
                        " Normal:1 \n" + //
                        " Difficile:2\n" + //
                        " Très difficile:3\n" + //
                        " Personnalisé:4\n"));
        this.d=d;
        switch(d){
            case 0:
                nbrCouleurs=6;
                memeCouleur=false;
                break;
            case 2:
                nbrCoups=10;
                break;
            case 3:
                nbrPionts=5;
                break;
            case 4:
                nbrCouleurs=Integer.parseInt(System.console().readLine("Choisissez le nombre de couleur(6 ou 8) :"));
                nbrPionts=Integer.parseInt(System.console().readLine("Choisissez le nombre de pions a devinez(4 ou 5) :"));
                nbrCoups=Integer.parseInt(System.console().readLine("Choisissez votre nombre de coups(10 ou 12) :"));
                choix=Integer.parseInt(System.console().readLine("Choisissez si les points peuvent avoir la meme couleur, 0 pour non 1 pour oui :"));
                if(choix==0){
                    memeCouleur=false;
                }
                break;

        }
        listeCouleurs = new ArrayList<>(listeComplete.subList(0, nbrCouleurs));

    }


   
}
