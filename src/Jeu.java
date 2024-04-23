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
                this.nbrCouleurs = obtenirChoix(6, 8, "Choisissez le nombre de couleur(6 ou 8) :");
                this.nbrPionts = obtenirChoix(4, 5, "Choisissez le nombre de pions à deviner(4 ou 5) :");
                this.nbrCoups = obtenirChoix(10, 12, "Choisissez votre nombre de coups(10 ou 12) :");
                int couleurChoix = obtenirChoix(0, 1, "Choisissez si les points peuvent avoir la même couleur, 0 pour non 1 pour oui :");
                this.memeCouleur = (couleurChoix == 1);
            break;
        }
     
        afficheCouleur();
    }

    private int obtenirEntier(String message) {
        while (true) {
            try {
                return Integer.parseInt(System.console().readLine(message));
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un entier valide.");
            }
        }
    }
    
    private int obtenirChoix(int min, int max, String message) {
        int choix;
        do {
            choix = obtenirEntier(message);
        } while (choix < min || choix > max);
        return choix;
    }
  
    private void clearConsole() {
        final String ESC = "\033[";
        System.out.print (ESC + "2J");
        System.out.print (ESC + "0;0H");
        System.out.flush();
    }
    public int inGame(){
        int numTour=0;
        this.p = new Plateau(nbrPionts, nbrCoups, nbrCouleurs, memeCouleur);
        Combinaison combinaison;
        boolean res;
        
        System.out.println("Donnez votre combinaison:\n Tour " + (numTour +1 ) + " / " + nbrCoups);
        combinaison = new Combinaison(nbrPionts, nbrCouleurs, memeCouleur);
        p.ajouterLigne(numTour, combinaison);
        numTour++; // Incrémente le numéro du tour

    
        while (!fin && numTour < nbrCoups) {
            clearConsole();
            System.out.println("Resultat du tour précédent :\n");
            if(difficulty==0){
                res=combinaison.afficheComparefacile(p.getCodeSecret());
            }
            else{
                res=combinaison.afficheCompareDifficile(p.getCodeSecret());
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
            System.out.println("Vous avez perdu...\n \n La solution étais :  " + p.getCodeSecret().toString());
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
