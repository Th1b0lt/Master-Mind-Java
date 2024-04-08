
package Projet.source.projet;
import java.util.Scanner;

import java.util.ArrayList;

public class Combinaison{
    private Pion combinaison [];
    private final int taille;
    public Combinaison(int taille){
        this.taille=taille;
        combinaison= new Pion [taille];
        Scanner scanner = new Scanner(System.in);
        for (int i=0;i<taille;i++){
            //Input utilisateur pour creer des pions
            System.out.println("Couleur du pion numéro " + (i + 1) + ": ");
            // Assuming Pion class has constructor Pion(String input) to initialize with user input
            combinaison[i] = new Pion(scanner.nextLine());
        }
    }
    private Object[] Compare(Combinaison otherCombinaison){
        int wellPlaced=0;
        int goodColor=0;
        boolean alreadySeen[] = new boolean[this.taille];
        ArrayList<Pion> pionsBienPlacees = new ArrayList<>();
        ArrayList<Pion> bonneCouleur= new ArrayList<>();
        for (int i=0;i<this.taille;i++){
            if (this.combinaison[i].equals(otherCombinaison.combinaison[i])){
                wellPlaced++;
                alreadySeen[i]=true;
                pionsBienPlacees.add(this.combinaison[i]);
            }
            else {
                for (int j=0;j<this.taille;j++){
                    if (!alreadySeen[j] && this.combinaison[i].getCouleur().equals(otherCombinaison.combinaison[j].getCouleur())){
                        goodColor++;
                        alreadySeen[j]=true;
                        bonneCouleur.add(this.combinaison[i]);
                        break;
                    }
                }
            }
        }
        Object[] result = new Object[4];
        result[0] = pionsBienPlacees;
        result[1] = bonneCouleur;
        result[2] = wellPlaced;
        result[3] = goodColor;
        
        return result;
        
    }

    public void afficheCompareDifficile(Combinaison otherCombinaison){
        Object affichage[]=Compare(otherCombinaison);
        System.out.println("Nombre de pions bien placés: " + affichage[2]);
        System.out.println("Nombre de bonnes couleurs (mal placées): " + affichage[3]);
    }
    public void afficheComparefacile(Combinaison otherCombinaison){
        Object affichage[]=Compare(otherCombinaison);
        System.out.println("Liste des pions bien placés : "+ affichage[0]);
        System.out.println("Pions de La bonne couleur : "+affichage[1]);
        System.out.println("Nombre de pions bien placés: " + affichage[2]);
        System.out.println("Nombre de bonnes couleurs (mal placées): " + affichage[3]);
    }

    @Override
    public String toString(){
        String retour="";
        for (int i=0;i<taille;i++){
            retour+="|" + combinaison[i].getCouleur()+"• \\u001B[37m|";
        }
        return retour;
    }
}
