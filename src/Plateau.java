package projet.source.projet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Plateau {
    private ArrayList<Couleur> listeCouleurs;
    private final Combinaison codeSecret;
    private Combinaison[] plateau;
    private int difficulty;
    private int nbrCouleurs=8;
    private int nbrPionts=4;
    private boolean memeCouleur=true;
    private int nbrCoups=12;

    public Plateau(){
        int choix,difficulty;
        ArrayList<Couleur> listeComplete = new ArrayList<>(Arrays.asList(Couleur.values()));
        Collections.shuffle(listeComplete);
        difficulty=Integer.parseInt(System.console().readLine("Choisissez la difficulté(taper le nombre correspondant):\n" + //
                        " Facile:0 \n" + //
                        " Normal:1 \n" + //
                        " Difficile:2\n" + //
                        " Très difficile:3\n" + //
                        " Personnalisé:4\n"));
        this.difficulty=difficulty;
        switch(difficulty){
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
            default:
                choix=Integer.parseInt(System.console().readLine("Choisissez le nombre de couleur(6 ou 8) :"));
                while(choix<6 || choix>8){
                    choix=Integer.parseInt(System.console().readLine("Choisissez le nombre de couleur(6 ou 8) :"));
                }
                nbrCouleurs=choix;
                choix=Integer.parseInt(System.console().readLine("Choisissez le nombre de pions a devinez(4 ou 5) :"));
                while(choix!=4 && choix!=5){
                    choix=Integer.parseInt(System.console().readLine("Choisissez le nombre de pions a devinez(4 ou 5) :"));
                }
                nbrPionts=choix;
                choix=Integer.parseInt(System.console().readLine("Choisissez votre nombre de coups(10 ou 12) :"));
                while(choix<10 || choix>12){
                    choix=Integer.parseInt(System.console().readLine("Choisissez votre nombre de coups(10 ou 12) :"));
                }
                nbrCoups=choix;
                choix=Integer.parseInt(System.console().readLine("Choisissez si les points peuvent avoir la meme couleur, 0 pour non 1 pour oui :"));
                while(choix!=1 && choix!=0){
                    choix=Integer.parseInt(System.console().readLine("Choisissez si les points peuvent avoir la meme couleur, 0 pour non 1 pour oui :"));
                }
                if(choix==0){
                    memeCouleur=false;
                }
                break;

        }
        listeCouleurs = new ArrayList<>(listeComplete.subList(0, nbrCouleurs));
        this.codeSecret= new Combinaison(nbrPionts);
        this.plateau= new Combinaison[nbrCoups];

    }
    public void afficheCouleur(){
        if(nbrCouleurs==6){
            System.out.println("Choisissez la couleur des points (il faudra entrer le nombre correspondant) , les couleurs sont  :\n" + //
        "1: \u001b[31m\u2B24\n" + //
        "\u001B[37m2: \u001B[34m\u2B24\n" + //
        "\u001B[37m3: \u001B[32m\u2B24\n" + //
        "\u001B[37m4: \u001B[33m\u2B24\n" + //
        "\u001B[37m5: \u001B[35m\u2B24\n" + //
        "\u001B[37m6: \033[95m\u2B24\u001B[37m \n");
        }
        else{
            System.out.println("Choisissez la couleur des points (il faudra entrer le nombre correspondant) , les couleurs sont :\n" + //
        "1: \u001b[31m\u2B24\n" + //
        "\u001B[37m2: \u001B[34m\u2B24\n" + //
        "\u001B[37m3: \u001B[32m\u2B24\n" + //
        "\u001B[37m4: \u001B[33m\u2B24\n" + //
        "\u001B[37m5: \u001B[35m\u2B24\n" + //
        "\u001B[37m6: \u001B[37m\u2B24\n"+ //
        "\u001B[37m7: \u001B[36m\u2B24\n"+ //
        "\u001B[37m8: \033[95m\u2B24\u001B[37m \n");
        }
    }
    
    @Override
    public String toString(){
        String result="";
        for(int i=0;i<plateau.length;i++){
            result+=plateau[i].toString()+"\n";
        }
        return result;

    }
    
   
}
