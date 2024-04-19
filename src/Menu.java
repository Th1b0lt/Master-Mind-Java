package projet.source.projet;

public class Menu {
    private boolean jouer=true;

    public Menu(){
        while(jouer){
            System.out.println("\n \nBIENVENUE DANS LE JEU DU MASTERMIND\n \n");
            if(System.console().readLine("Voulez vous rappelez les règles du jeu ? (o/n)")=="o"){
                afficherRegle();
            }
            if(System.console().readLine("Partie solo ou multijoueur ? (s/m)")=="m"){
                clearConsole();
                jeuMulti();
            }
            else{
                clearConsole();
                if(System.console().readLine("Voulez vous charger une partie existante ? (o/n)")=="o"){
                    //
                    //TODO
                    //
                }
                else{
                    System.out.println("Nouvelle partie :\n");
                    lancerPartie();
                }
            }
            if(System.console().readLine("Voulez vous refaire une partie ? (o/n)")=="n"){
                jouer=false;
                System.out.println("Fin du jeu, merci pour votre temps !");
            };
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
        int partie1,partie2;
        Jeu j = new Jeu();
        System.out.println("Mode multijoueur, deux parties seront jouées, le joueur avec le moins de coup gagne la partie. Bonne chance\n");
        partie1=j.inGame();
        partie2=j.inGame();
        if(partie1==partie2){
            System.out.println("Egalité bravo à vous deux !");
        }
        else if(partie1>partie2){
            System.out.println("Le premier joueur à gagné, bravo à lui");
        }
        else{
            System.out.println("Le second joueur à gagné, bravo à lui");
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
