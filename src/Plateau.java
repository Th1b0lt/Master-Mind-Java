package Projet.source.plateau;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Plateau {
    private ArrayList<Couleur> listeCouleurs;


    public Plateau(int nbrCouleur){
        ArrayList<Couleur> listeComplete = new ArrayList<>(Arrays.asList(Couleur.values()));
        Collections.shuffle(listeComplete);
        listeCouleurs = new ArrayList<>(listeComplete.subList(0, nbrCouleur));
        Pion test=new Pion(5,Couleur.ROUGE);
    }


    @Override
    public String toString(){

    }
}
