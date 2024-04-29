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

public class JeuMulti{
    int nbrPartie=0;
    int nbrJoueur=10;
    Plateau j;   
    int[] scores;
    String[] noms;
    int nbrTour=0;
    int joueurActuel=0;

    //Méthode pour lancer une partie multijoueur
    public JeuMulti(){
        System.out.println("Mode multijoueur\n \n");
        this.j = new Plateau(true);

        boolean validInput2 = false;
        boolean validInput1=false;
        while (this.nbrPartie<1) {
            try {
                this.nbrPartie = Integer.parseInt(System.console().readLine("Combien de partie pour vous départager ?\n"));
        
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
        while ( this.nbrJoueur<2 ||this.nbrJoueur>10|| !validInput1) {
            try {
                this.nbrJoueur = Integer.parseInt(System.console().readLine("Combien de Joueur veulent jouer (nombre entre 2 et 10 inclus) ?\n"));
                validInput1=true;
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
        this.scores= new int[this.nbrJoueur];
        this.noms = new String[this.nbrJoueur]; // Pour stocker les noms des joueurs
        clearConsole();
        for (int k=0;k<this.nbrJoueur;k++){
            validInput2 = false;
            while (!validInput2) {
                try {
                    noms[k] = (System.console().readLine("Pseudo du joueur "+(k+1)+"?\n"));
                    validInput2 = true;
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer une chaine de caractère.");
                }
            }
        }
        
    }
    public JeuMulti(Path path){
        this.scores= new int[this.nbrJoueur];
        this.noms = new String[this.nbrJoueur];
        this.j =new Plateau();
        this.j.setMulti();
        loadMulti(path);
    }
    public void enJeuMulti(){
        Scanner scanner = new Scanner(System.in);
        for (int i=this.nbrTour;i<this.nbrPartie;i++){
            this.nbrTour=i;
            for (int k=this.joueurActuel;k<this.nbrJoueur;k++){
                this.joueurActuel=k;
                System.out.println("TOUR DE "+noms[k].toUpperCase()+" \n \n");
                j.afficheCouleur();
                scores[k]+=j.inGame();
                j.setNumTour(0);
                if (!(i==nbrPartie-1 && k==nbrJoueur-1)){
                    System.out.println("Appuyez sur 's' pour sauvegarder et quitter, ou appuyez sur Entrée pour continuer :");
                    
                    String choix = scanner.nextLine();
            
                    if ("s".equalsIgnoreCase(choix)) {
                        System.out.println("Entrez le chemin du fichier de sauvegarde :");
                        String chemin = scanner.nextLine();//Recupere le chemin

                        Path path = Paths.get(chemin); //creer un path
                        if (Files.exists(path)) {
                            System.out.println("Le fichier existe déjà. Voulez-vous le remplacer ? (Oui/Non)");
                            String reponse = scanner.nextLine();
                            if ("Non".equalsIgnoreCase(reponse)) {
                                continue;
                        }
                    }

                    saveMulti(path); //Ecriture dans le fichier
                    return ;
                    }
                }
                clearConsole();
                System.out.println(noms[k]+" a fini son tour !\n\n");
                wait(2000);
                clearConsole();
        
            }
            this.joueurActuel=0;
        
        }
        clearConsole();
        //Affichage du classement des joueurs
        System.out.println("Classement des joueurs :");
        countdown(3000);
        clearConsole();
        System.out.println("Classement des joueurs :");
        for (int i = 0; i < nbrJoueur; i++) {
            int minIndex = 0;
            int minScore = Integer.MAX_VALUE;

        
            for (int z = 0; z < nbrJoueur; z++) {
                if (scores[z] < minScore) {
                    minScore = scores[z];
                    minIndex = z;
                }
            }

            System.out.println((nbrJoueur-i) + ". " + noms[minIndex] + " - Score: " + scores[minIndex]);
            scores[minIndex] = Integer.MAX_VALUE; 
            wait(1000);
        }

    }
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    public static void countdown(int ms) {
        try {
            System.out.println("3 !\r");
            Thread.sleep(ms/3);
            System.out.println("2 !\r");
            Thread.sleep(ms/3);
            System.out.println("1 !\r");
            Thread.sleep(ms/3);

        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


    public void saveMulti(Path path){
        try {
            ArrayList<String> lines = j.enregistre();

            // Sauvegarde des paramètres du jeu multijoueur
            lines.add("nbrPartie=" + nbrPartie);
            lines.add("nbrJoueur=" + nbrJoueur);
            lines.add("nbrTour=" + nbrTour);
            lines.add("joueurActuel=" + joueurActuel+1);

            // Sauvegarde des noms et des scores des joueurs
            for (int i = 0; i < nbrJoueur; i++) {
                lines.add("nomJoueur=" + noms[i] + "=" + i);
                lines.add("scoreJoueur=" + scores[i] + "=" + i);
            }
            
            Files.write(path, lines, StandardCharsets.UTF_8);
            //j.save(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMulti(Path path) {
        int index=0;
        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));//Recuperation du fichier
            
            for (String line : lines) {
                String[] parts = line.split("=");//on separe avec le signe "=" ecrit dans save
                
                switch (parts[0]) {//Set tous les paramètres du jeu présent dans la sauvegarde, ainsi que le plateau
                    case "nbrPartie":
                        this.nbrPartie = Integer.parseInt(parts[1]);
                        System.out.println("nbrPartie :  "+this.nbrPartie);
                        break;
                    case "nbrJoueur":
                        this.nbrJoueur = Integer.parseInt(parts[1]);
                        System.out.println("nbrJoueur:  "+this.nbrJoueur);
                        break;
                    case "nbrTour":
                        this.nbrTour = Integer.parseInt(parts[1]);
                        System.out.println("nbrTour : "+this.nbrTour);
                        break;
                    case "joueurActuel":
                        this.joueurActuel = Integer.parseInt(parts[1]);
                        System.out.println("JoueurActuel" + this.joueurActuel);
                        break;
                    case "nomJoueur":
                        index=Integer.parseInt(parts[2]);
                        if (this.noms[index]==null){
                            this.noms[index]=parts[1];
                        }
                        break;
                    case "scoreJoueur":
                        index = Integer.parseInt(parts[2]);
                        if (this.scores[index] == 0) {
                            this.scores[index] =Integer.parseInt(parts[1]);
                        
                        }
                        break;
                }
            }
            this.j.load(path);
    
        } catch (IOException e) {
            e.printStackTrace(); //erreur de fichier
    
        }
        //Affiche la partie courante.
    
        }
        private void clearConsole() {
            final String ESC = "\033[";
            System.out.print (ESC + "2J");
            System.out.print (ESC + "0;0H");
            System.out.flush();
        }

    
}