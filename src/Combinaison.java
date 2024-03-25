
package Projet.source.combinaison;
import java.util.Scanner;


public class Combinaison{
    public Pion combinaison [];
    protected static final int taille;
    public Combinaison(int taille){
        this.taille=taille;
        combinaison= new Pion [taille];
        Scanner scanner = new Scanner(System.in);
        for (int i=0;i<taille;i++){
            //Input utilisateur pour creer des pions
            System.out.println("Couleur du pion " + (i + 1) + ": ");
            // Assuming Pion class has constructor Pion(String input) to initialize with user input
            combinaison[i] = new Pion(i,scanner.nextLine());
        }
    }
    public int[] Compare(Combinaison otherCombinaison){
        int wellPlaced=0;
        int goodColor=0;
        private boolean alreadySeen [] =new boolean[this.taille];
        int retour[]=new int[2];
        for (int i=0;i<this.taille;i++){
            if (this.combinaison[i].equals(otherCombinaison.combinaison[i])){
                wellPlaced++;
                alreadySeen[i]=true;
            }
            else {
                for (int j=0;j<this.taille;j++){
                    if (!alreadySeen[j] && this.combinaison[i].getCouleur().equals(otherCombinaison.combinaison[j].getCouleur())){
                        goodColor++;
                        alreadySeen[j]=true;
                        break;
                    }
                }
            }
        }
        retour[0]=wellPlaced;
        retour[1]=goodColor;
        return retour;
    }

    public void afficheCompare(Combinaison otherCombinaison){
        int affichage[]=Compare(otherCombinaison);
        System.out.println("Nombre de pions bien placés: " + affichage[0]);
        System.out.println("Nombre de bonnes couleurs (mal placées): " + affichage[1]);
    }
    @Override
    public String toString(){
        for (int i=0;i<taille;i++){
            System.out.println("|" + combinaison[i].getCouleur()+"|");
        }
    }
}