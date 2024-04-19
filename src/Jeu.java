package projet.source.projet;

import java.util.ArrayList;

public class Jeu {
    private Plateau p;
    private boolean fin=false;
    private int difficulty;
    private int nbrCouleurs=8;
    private int nbrPionts=4;
    private boolean memeCouleur=true;
    private int nbrCoups=12;
    private int numTour=0;
    private final Combinaison codeSecret;



    public Jeu(){
        int choix,difficulty;

         difficulty=Integer.parseInt(System.console().readLine("Choisissez la difficulté(taper le nombre correspondant):\n" + //
                        " Facile:0 \n" + //
                        " Normal:1 \n" + //
                        " Difficile:2\n" + //
                        " Très difficile:3\n" + //
                        " Personnalisé:4\n"));
        System.out.println("\n");
        this.difficulty=difficulty;
        switch(difficulty){
            case 0:
                this.nbrCouleurs=6;
                this.memeCouleur=false;
                break;
            case 1:
                break;
            case 2:
                this.nbrCoups=10;
                break;
            case 3:
                this.nbrPionts=5;
                break;
            default:
                System.out.println("Vous avez choisi de personnaliser la difficulté a votre guise:\n");
                choix=Integer.parseInt(System.console().readLine("Choisissez le nombre de couleur(6 ou 8) :"));
                while(choix<6 || choix>8){
                    choix=Integer.parseInt(System.console().readLine("Choisissez le nombre de couleur(6 ou 8) :"));
                }
                this.nbrCouleurs=choix;
                choix=Integer.parseInt(System.console().readLine("Choisissez le nombre de pions a devinez(4 ou 5) :"));
                while(choix!=4 && choix!=5){
                    choix=Integer.parseInt(System.console().readLine("Choisissez le nombre de pions a devinez(4 ou 5) :"));
                }
                this.nbrPionts=choix;
                choix=Integer.parseInt(System.console().readLine("Choisissez votre nombre de coups(10 ou 12) :"));
                while(choix<10 || choix>12){
                    choix=Integer.parseInt(System.console().readLine("Choisissez votre nombre de coups(10 ou 12) :"));
                }
                this.nbrCoups=choix;
                choix=Integer.parseInt(System.console().readLine("Choisissez si les points peuvent avoir la meme couleur, 0 pour non 1 pour oui :"));
                while(choix!=1 && choix!=0){
                    choix=Integer.parseInt(System.console().readLine("Choisissez si les points peuvent avoir la meme couleur, 0 pour non 1 pour oui :"));
                }
                if(choix==0){
                    this.memeCouleur=false;
                }
                break;

        }
        this.codeSecret= new Combinaison(nbrPionts,nbrCouleurs,memeCouleur,true);
        this.p = new Plateau(nbrPionts, nbrCoups, nbrCouleurs, memeCouleur);

        afficheCouleur();
    }
    private void clearConsole() {
        final String ESC = "\033[";
        System.out.print (ESC + "2J");
        System.out.print (ESC + "0;0H");
        System.out.flush();
    }
    public int inGame(){
            Combinaison combinaison;
            boolean res;
            System.out.println("Tour " + (numTour +1 ) + " / " + nbrCoups);
            combinaison = new Combinaison(nbrPionts, nbrCouleurs, memeCouleur);
            p.ajouterLigne(numTour, combinaison);
            numTour++; // Incrémente le numéro du tour

        
            while (!fin && numTour < nbrCoups) {
                clearConsole();
                System.out.println("Resultat du tour précédent :\n");
                if(difficulty==0){
                    res=combinaison.afficheComparefacile(codeSecret);
                }
                else{
                    res=combinaison.afficheCompareDifficile(codeSecret);
                }
                if(res==true){
                    fin=true;
                    break;
                }
                System.out.println("Plateau actuel :\n" + p.toString());
                System.out.println("Tour " + (numTour + 1) + " / " + nbrCoups);
                System.out.println("Donnez votre combinaison :\n");
                afficheCouleur();
        
                combinaison = new Combinaison(nbrPionts, nbrCouleurs, memeCouleur);
        
        
                p.ajouterLigne(numTour, combinaison);
                numTour++; // Incrémente le numéro du tour


        

            }
            clearConsole();
            if(!fin){
                System.out.println("Vous avez perdu...\n \n La solution étais :  " + codeSecret.toString());
            }
            else{
                System.out.println("Bravo vous avez gagné !" );

            }
            System.out.println("\nPlateau final :\n" + p.toString());

            return numTour;
        }
    



    public void afficheCouleur(){
        if(nbrCouleurs==6){
            System.out.println("Voici les choix de couleur possibles (il vous faudra entrer le nombre correspondant) , les couleurs sont  :\n" + //
        "1: \u001b[31m\u2B24  " + //
        "\u001B[37m2: \u001B[34m\u2B24  " + //
        "\u001B[37m3: \u001B[32m\u2B24\n" + //
        "\u001B[37m4: \u001B[33m\u2B24  " + //
        "\u001B[37m5: \u001B[35m\u2B24  " + //
        "\u001B[37m6: \033[95m\u2B24\u001B[37m \n");
        }
        else{
            System.out.println("Voici les choix de couleur possibles (il vous faudra entrer le nombre correspondant) , les couleurs sont :\n" + //
        "1: \u001b[31m\u2B24  " + //
        "\u001B[37m2: \u001B[34m\u2B24  " + //
        "\u001B[37m3: \u001B[32m\u2B24  " + //
        "\u001B[37m4: \u001B[33m\u2B24\n" + //
        "\u001B[37m5: \u001B[35m\u2B24  " + //
        "\u001B[37m6: \033[95m\u2B24\u001B[37m  " + //
        "\u001B[37m7: \u001B[36m\u2B24  "+ //
        "\u001B[37m8: \u001B[37m\u2B24 \n");
        }
    }
}
