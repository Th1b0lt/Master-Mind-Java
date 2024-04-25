package projet.source.projet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class Menu {
    private boolean jouer=true;

    public Menu() {
        System.out.println("\n \nBIENVENUE DANS LE JEU DU MASTERMIND\n \n");
        while (jouer) {
            String reponse;
            
            reponse = System.console().readLine("Voulez-vous rappeler les règles du jeu ? (o/n)");
            while (!reponse.equalsIgnoreCase("o") && !reponse.equalsIgnoreCase("n")) {
                System.out.println("Veuillez répondre par 'o' pour oui ou 'n' pour non.");
                reponse = System.console().readLine("Voulez-vous rappeler les règles du jeu ? (o/n)");
            }
            if (reponse.equalsIgnoreCase("o")) {
                afficherRegle();
            }
    
            reponse = System.console().readLine("Partie solo ou multijoueur ? (s/m)");
            while (!reponse.equalsIgnoreCase("s") && !reponse.equalsIgnoreCase("m")) {
                System.out.println("Veuillez répondre par 's' pour solo ou 'm' pour multijoueur.");
                reponse = System.console().readLine("Partie solo ou multijoueur ? (s/m)");
            }
            if (reponse.equalsIgnoreCase("m")) {
                clearConsole();
                jeuMulti();
            } else {
                clearConsole();
                reponse = System.console().readLine("Voulez-vous charger une partie existante ? (o/n)");
                while (!reponse.equalsIgnoreCase("o") && !reponse.equalsIgnoreCase("n")) {
                    System.out.println("Veuillez répondre par 'o' pour oui ou 'n' pour non.");
                    reponse = System.console().readLine("Voulez-vous charger une partie existante ? (o/n)");
                }
                if (reponse.equalsIgnoreCase("o")) {
                    //
                    //TODO
                    //
                } else {
                    System.out.println("Nouvelle partie :\n");
                    lancerPartie();
                }
            }
    
            reponse = System.console().readLine("Voulez-vous refaire une partie ? (o/n)");
            while (!reponse.equalsIgnoreCase("o") && !reponse.equalsIgnoreCase("n")) {
                System.out.println("Veuillez répondre par 'o' pour oui ou 'n' pour non.");
                reponse = System.console().readLine("Voulez-vous refaire une partie ? (o/n)");
            }
            if (reponse.equalsIgnoreCase("n")) {
                jouer = false;
                System.out.println("Fin du jeu, merci pour votre temps !");
            }
        }
    }
    

    private void clearConsole() {
        final String ESC = "\033[";
        System.out.print (ESC + "2J");
        System.out.print (ESC + "0;0H");
        System.out.flush();
    }

    public int lancerPartie(){
        int nbrCoupsJouer;
        Jeu j= new Jeu();
        nbrCoupsJouer=j.inGame();
        return nbrCoupsJouer;
    }

    public void jeuMulti(){
        int nbrPartie=0,score1=0,score2=0;
        System.out.println("Mode multijoueur\n \n");
        boolean validInput = false;
        while (!validInput) {
            try {
                nbrPartie = Integer.parseInt(System.console().readLine("Combien de partie pour vous départager ?\n"));
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
        clearConsole();
        Jeu j = new Jeu();
        clearConsole();
        for(int i=0;i<nbrPartie;i++){
            if(i>0){
                System.out.println("Le Joueur 2 a fini son tour !\n\n");
            }
            System.out.println("TOUR DU JOUEUR 1 \n \n");
            score1+=j.inGame();
            System.out.println("Le Joueur 1 a fini son tour !\n\nTOUR DU JOUEUR 2 \n \n");
            score2+=j.inGame();
            wait(50);
            clearConsole();
        }
        clearConsole();
        if(score1==score2){
            System.out.println("Egalité bravo à vous deux ! Le score est de "+score1+"-"+score2);
        }
        else if(score1<score2){
            System.out.println("Le premier joueur a gagné, bravo à lui Le score est de "+score1+"-"+score2);
        }
        else{
            System.out.println("Le second joueur a gagné, bravo à lui Le score est de "+score1+"-"+score2);
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
    public void afficherRegle(){
        System.out.println("Résumé des règles du Mastermind :\n" + //
        
        "Le Mastermind est un jeu de logique qui se joue avec des pions colorés. Voici comment jouer :\n \n" + //
        
           " Objectif : Découvrir la combinaison secrète de couleurs cachée par l'ordinateur.\n \n" +//
           " Matériel : Le jeu comprend un plateau de jeu avec des rangées où placer vos propositions de combinaisons et des pions de différentes couleurs.\n"+//
           " Combinaison secrète : L'ordinateur choisit une combinaison secrète de pions colorés que le joueur doit deviner.\n" + //
           " Propositions : Le joueur propose une combinaison de pions en choisissant les couleurs pour chaque emplacement.\n \n" + //
           " Évaluation : L'ordinateur évalue chaque proposition et fournit des indications :\n" +//
                "Pions bien placés : Les pions de la proposition qui sont à la bonne place dans la combinaison secrète.\n"+//
                "Bonnes couleurs mal placées : Les pions de la proposition qui sont de la bonne couleur mais mal placés.\n \n"+//
                "Si la partie ce déroule en mode facile des indications supplémentaires seront dispensées(notamment quel piont sont bien placées ou quel sont les bonnes couleurs).\n"+//
            "Nombre de tours : Le joueur a un nombre limité de tours pour deviner la combinaison secrète.(entre 8 et 12)\n \n" +//
            "Victoire : Le joueur gagne s'il parvient à deviner la combinaison secrète dans le nombre de tours imparti.\n" + //
            "Défaite : Le joueur perd s'il n'arrive pas à deviner la combinaison secrète dans le nombre de tours imparti.\n");
    }
}
