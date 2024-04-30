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
    private Combinaison[] plateau;
    private Combinaison codeSecret;
    private int difficulty;
    private int nbrCouleurs=8;
    private int nbrPionts=4;
    private boolean memeCouleur=true;
    private int nbrCoups=12;
    private int numTour=0;
    private boolean multi;
   
    //Constructeur d'un plateau
    public Plateau(boolean multi){
        int choix,difficulty;

        this.multi=multi;
        //Choix de la difficulté
        difficulty=obtenirChoix(0,4,"Choisissez la difficulté(taper le nombre correspondant):\n" + //
                        " Facile:0 \n" + //
                        " Normal:1 \n" + //
                        " Difficile:2\n" + //
                        " Très difficile:3\n" + //
                        " Personnalisé:4\n");
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
        System.out.println(afficheCouleur());

    }

    //Constructeur d'un plateau par défault
    public Plateau(){
        this.plateau=new Combinaison[nbrCoups];
    }

    

    public int inGame(){
        Combinaison combinaison;
        boolean res;
        boolean fin=false;
        Scanner scanner = new Scanner(System.in);
        //Decomenter pour tester
        //System.out.println(" la combinaison est : "+ codeSecret.toString());
        System.out.println("Donnez votre combinaison:\n Tour " + (this.numTour +1 ) + " / " + nbrCoups);
        combinaison = new Combinaison(nbrPionts, nbrCouleurs, memeCouleur);
        ajouterLigne(this.numTour, combinaison);
        this.numTour++; // Incrémente le numéro du tour
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
        }

        while (!fin && this.numTour < nbrCoups) {

            System.out.println("Plateau actuel :\n" + toString());
            System.out.println("Tour " + (this.numTour + 1) + " / " + nbrCoups);
            if(!multi){
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

                save(path); //Ecriture dans le fichier
                return 0;
                }
            }
            System.out.println("Donnez votre combinaison :\n");
            System.out.println(afficheCouleur());
           
            combinaison = new Combinaison(nbrPionts, nbrCouleurs, memeCouleur); 
            
            
            ajouterLigne(this.numTour, combinaison);
            this.numTour++; // Incrémente le numéro du tour
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

    

        }
        clearConsole();
        System.out.println("\nPlateau final :\n" + toString());

        if(!fin){
            System.out.println("Vous avez perdu...\n \n La solution était :  " + getCodeSecret().toString());
        }
        else{
            System.out.println("Bravo vous avez gagné ! La solution était :  " + getCodeSecret().toString());

        }
        for (int i = 0; i < plateau.length && plateau[i] != null; i++) {
            plateau[i]=null;//Retour du plateau à null
        }
        this.codeSecret= new Combinaison(nbrPionts,nbrCouleurs,memeCouleur,true);//creation d'une nouveau code secret pour la prochaine partie (pour le multi)
        return this.nbrCoups-this.numTour;
    }

    //Méthode pour ajouter une ligne au plateau
    public void ajouterLigne(int numTour, Combinaison c){
        this.plateau[numTour]=c;
    }
    //getter pour le code secret
    private Combinaison getCodeSecret(){
        return codeSecret;
    }

    public ArrayList<String> enregistre() {
        ArrayList<String> lines = new ArrayList<>(); //Liste qui sera ecrit dans le fichier
            
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

        for (int i = 0; i < plateau.length; i++) {//Parcours de chaque combinaison entré dans la partie
            if (plateau[i] != null) {
                String combinaisonStr = "";
                for (int j = 0; j < nbrPionts; j++) {
                    combinaisonStr += plateau[i].getCombinaison()[j].getCouleur().getValue();//Ecriture de chaque valeur de la combinaison
                }
                lines.add("Combinaison=" + combinaisonStr + "=" + i);//ecriture de la combinaison puis du numéro de la combinaison
            }
        }
        return lines;
      
    }
    public void save(Path path){
        ArrayList<String> lines=enregistre();
        try{
        Files.write(path, lines, StandardCharsets.UTF_8);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void load(Path path) {
    int index=0;
    try {
        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));//Recuperation du fichier
        
        for (String line : lines) {
            String[] parts = line.split("=");//on separe avec le signe "=" ecrit dans save
            
            switch (parts[0]) {//Set tous les paramètres du jeu présent dans la sauvegarde, ainsi que le plateau
                case "difficulty":
                    this.difficulty = Integer.parseInt(parts[1]);
                    System.out.println("Difficulty :  "+this.difficulty);
                    break;
                case "nbrCouleurs":
                    this.nbrCouleurs = Integer.parseInt(parts[1]);
                    //System.out.println("nbrcouleur:  "+this.nbrCouleurs);
                    break;
                case "nbrPionts":
                    this.nbrPionts = Integer.parseInt(parts[1]);
                    //System.out.println("nbrpion : "+this.nbrPionts);
                    break;
                case "memeCouleur":
                    this.memeCouleur = Boolean.parseBoolean(parts[1]);
                    //System.out.println("Meme couleur ? " + this.memeCouleur);
                    break;
                case "nbrCoups":
                    this.nbrCoups = Integer.parseInt(parts[1]);
                    //System.out.println("nbrCoups " + this.nbrCoups);
                    break;
                case "numTour":
                    this.numTour=Integer.parseInt(parts[1]);
                    //System.out.println("numTour " + this.numTour);
                case "CodeSecret":
                    this.codeSecret = new Combinaison(parts[1]);
                    //System.out.println(this.codeSecret.toString());
                    break;
                case "Combinaison":
                     index = Integer.parseInt(parts[2]);
                    if (plateau[index] == null) {
                        plateau[index] = new Combinaison(parts[1]);
                        //System.out.println(this.plateau[index].toString());
                    }
                    break;
            }
        }

    } catch (IOException e) {
        e.printStackTrace(); //erreur de fichier

    }
   
    System.out.println("Plateau actuel :\n" + toString());
    System.out.println(afficheCouleur());
    
    }

    public void setNumTour(int num){
        this.numTour=num;
    }
    public void setMulti(){
        this.multi=true;
    }

    //Méthode pour afficher les listes de couleurs selon la difficulté
    public String afficheCouleur(){
        String resultat="";
        resultat+="Voici les choix de couleur possibles (il vous faudra entrer le nombre correspondant) , les couleurs sont :\n";
        for(int i=1;i<this.nbrCouleurs+1;i++){
            Couleur color=Couleur.getCouleurByValue(i);
            resultat+=i+": "+color.afficherTexteEnCouleur("\u2B24")+"  ";
            if(nbrCouleurs/2==i){
               resultat+="\n";
            }
        }
        return resultat;
    }

    //Méthode pour vérifier que c'est un entier
    private int obtenirEntier(String message) {
        while (true) {
            try {
                return Integer.parseInt(System.console().readLine(message));
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un entier valide.");
            }
        }
    }
    
    //Méthode pour vérifier que c'est choix
    private int obtenirChoix(int min, int max, String message) {
        int choix;
        do {
            choix = obtenirEntier(message);
        } while (choix < min || choix > max);
        return choix;
    }

    //Méthode pour clear la console
    private void clearConsole() {
        final String ESC = "\033[";
        System.out.print (ESC + "2J");
        System.out.print (ESC + "0;0H");
        System.out.flush();
    }
    
    //Redéfinitionde toString pour afficher le plateau
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
