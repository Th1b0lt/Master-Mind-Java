package projet.source.projet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;
import java.util.Scanner;

public class Plateau {
    private ArrayList<Couleur> listeCouleurs;
    private Combinaison[] plateau;
    private  Combinaison codeSecret;
    private boolean fin=false;
    private int difficulty;
    private int nbrCouleurs=8;
    private int nbrPionts=4;
    private boolean memeCouleur=true;
    private int nbrCoups=12;
    private int numTour=0;
    private boolean multi;
   

    public Plateau(boolean multi){
        int choix,difficulty;

        this.multi=multi;
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
        this.plateau= new Combinaison[nbrCoups];

        this.codeSecret= new Combinaison(nbrPionts,nbrCouleurs,memeCouleur,true);
        afficheCouleur();
    }

    public Plateau(int a){
        this.plateau=new Combinaison[nbrCoups];
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
        Combinaison combinaison;
        boolean res;
        Scanner scanner = new Scanner(System.in);
        this.fin=false;
        //Decomenter pour tester
        //System.out.println(" la combinaison est : "+ codeSecret.toString());
        System.out.println("Donnez votre combinaison:\n Tour " + (this.numTour +1 ) + " / " + nbrCoups);
        combinaison = new Combinaison(nbrPionts, nbrCouleurs, memeCouleur);
        ajouterLigne(this.numTour, combinaison);
        this.numTour++; // Incrémente le numéro du tour

    
        while (!fin && this.numTour < nbrCoups) {
            clearConsole();
            System.out.println("Resultat du tour précédent :\n");
            if(difficulty==0){
                res=combinaison.afficheComparefacile(getCodeSecret());
            }
            else{
                res=combinaison.afficheCompareDifficile(getCodeSecret());
            }
            if(res==true){
                fin=true;
                break;
            }
            System.out.println("Plateau actuel :\n" + toString());
            System.out.println("Tour " + (this.numTour + 1) + " / " + nbrCoups);
            if(!multi){
                System.out.println("Appuyez sur 's' pour sauvegarder et quitter, ou appuyez sur Entrée pour continuer :");
                
                String choix = scanner.nextLine();
        
                if ("s".equalsIgnoreCase(choix)) {
                    System.out.println("Entrez le chemin du fichier de sauvegarde :");
                    String chemin = scanner.nextLine();

                    Path path = Paths.get(chemin);
                    if (Files.exists(path)) {
                        System.out.println("Le fichier existe déjà. Voulez-vous le remplacer ? (Oui/Non)");
                        String reponse = scanner.nextLine();
                        if ("Non".equalsIgnoreCase(reponse)) {
                            continue;
                    }
                }

                save(path);
                return 0;
                }
            }
            System.out.println("Donnez votre combinaison :\n");
            afficheCouleur();
           
            combinaison = new Combinaison(nbrPionts, nbrCouleurs, memeCouleur);
            
            
            ajouterLigne(this.numTour, combinaison);
            this.numTour++; // Incrémente le numéro du tour


    

        }
        clearConsole();
        System.out.println("\nPlateau final :\n" + toString());

        if(!fin){
            System.out.println("Vous avez perdu...\n \n La solution étais :  " + getCodeSecret().toString());
        }
        else{
            System.out.println("Bravo vous avez gagné ! La solution étais :  " + getCodeSecret().toString());

        }
        for (int i = 0; i < plateau.length && plateau[i] != null; i++) {
            plateau[i]=null;
        }
        this.codeSecret= new Combinaison(nbrPionts,nbrCouleurs,memeCouleur,true);
        return this.numTour;
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
    public void ajouterLigne(int numTour, Combinaison c){
        this.plateau[numTour]=c;
    }
    public Combinaison getCodeSecret(){
        return codeSecret;
    }

    public void save(Path path) {
        try {
            ArrayList<String> lines = new ArrayList<>();
            
            // Sauvegarde des paramètres du jeu
            lines.add("difficulty=" + difficulty);
            lines.add("nbrCouleurs=" + nbrCouleurs);
            lines.add("nbrPionts=" + nbrPionts);
            lines.add("memeCouleur=" + memeCouleur);
            lines.add("nbrCoups=" + nbrCoups);
            lines.add("numTour="+ numTour);
            
            // Sauvegarde du code secret et des combinaisons du plateau
            String codeSecretStr="";
            for (int j=0; j<nbrPionts;j++){
                codeSecretStr+=codeSecret.getCombinaison()[j].getCouleur().getValue();
            }
            lines.add("CodeSecret=" +codeSecretStr);
            for (int i = 0; i < plateau.length; i++) {
                if (plateau[i] != null) {
                    String combinaisonStr = "";
                    for (int j = 0; j < nbrPionts; j++) {
                        combinaisonStr += plateau[i].getCombinaison()[j].getCouleur().getValue();
                    }
                    lines.add("Combinaison=" + combinaisonStr + "=" + i);
                }
            }
            
    
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(Path path) {
    int index=0;
    try {
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        
        for (String line : lines) {
            String[] parts = line.split("=");
            
            switch (parts[0]) {
                case "difficulty":
                    this.difficulty = Integer.parseInt(parts[1]);
                    System.out.println("Difficulty :  "+this.difficulty);
                    break;
                case "nbrCouleurs":
                    this.nbrCouleurs = Integer.parseInt(parts[1]);
                    System.out.println("nbrcouleur:  "+this.nbrCouleurs);
                    break;
                case "nbrPionts":
                    this.nbrPionts = Integer.parseInt(parts[1]);
                    System.out.println("nbrpion : "+this.nbrPionts);
                    break;
                case "memeCouleur":
                    this.memeCouleur = Boolean.parseBoolean(parts[1]);
                    System.out.println("Meme couleur ? " + this.memeCouleur);
                    break;
                case "nbrCoups":
                    this.nbrCoups = Integer.parseInt(parts[1]);
                    System.out.println("nbrCoups " + this.nbrCoups);
                    break;
                case "numTour":
                    this.numTour=Integer.parseInt(parts[1]);
                    System.out.println("numTour " + this.numTour);
                case "CodeSecret":
                    this.codeSecret = new Combinaison(parts[1]);
                    System.out.println(this.codeSecret.toString());
                    break;
                case "Combinaison":
                     index = Integer.parseInt(parts[2]);
                    if (plateau[index] == null) {
                        plateau[index] = new Combinaison(parts[1]);
                        System.out.println(this.plateau[index].toString());
                    }
                    break;
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Cest la que ca chie");

    }
    System.out.println("La partie a été chargée. Continuons la partie avec " +(nbrCoups-index-1)+ " coups restants.");
    }

    public void setNumTour(int num){
        this.numTour=num;
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
