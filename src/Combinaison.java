package projet.source.projet;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Combinaison{
    private Pion combinaison [];
    private final int taille;
    
    //Constructeur de combinaison
    public Combinaison(int taille,int nbrCouleurs,boolean memeCouleur){
        this.taille=taille;
        int couleurValue;
        combinaison= new Pion [taille];
        Scanner scanner = new Scanner(System.in);
        if(memeCouleur==true){
            for (int i=0;i<taille;i++){
                //Input utilisateur pour creer des pions
                System.out.println("Couleur du pion " + (i + 1) + ": ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Veuillez entrer un nombre.");
                    scanner.next(); // Consommez l'entrée incorrecte
                }
                couleurValue=scanner.nextInt();
                while(couleurValue<1 || couleurValue>nbrCouleurs){
                    System.out.println("CHOISSISSEZ UN NUMERO DE COULEUR VALIDE ! \n Compris entre 1 et le maximum disponible");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Veuillez entrer un nombre valide.");
                        scanner.next(); // Consommez l'entrée incorrecte
                    }
                    couleurValue=scanner.nextInt();
                }
                combinaison[i] = new Pion(Couleur.getCouleurByValue(couleurValue));
            }
        }
        else{
            Pion newPion;
            boolean bon=false;
            for (int i=0;i<taille;i++){
                System.out.println("Couleur du pion " + (i + 1) + ": ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Veuillez entrer un nombre valide.");
                    scanner.next(); // Consommez l'entrée incorrecte
                }
                couleurValue=scanner.nextInt();
                while(couleurValue<1 || couleurValue>nbrCouleurs){
                    System.out.println("CHOISSISSEZ UN NUMERO DE COULEUR VALIDE ! \n Compris entre 1 et le maximum disponible");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Veuillez entrer un nombre valide.");
                        scanner.next(); // Consommez l'entrée incorrecte
                    }
                    couleurValue=scanner.nextInt();

                }
                newPion = new Pion(Couleur.getCouleurByValue(couleurValue));
                while(!(bon)){
                    bon=true;
                    for(int j=0;j<i;j++){
                        if(newPion.compareTo(combinaison[j])==1){
                            bon=false;
                        }
                    }
                    if(bon==false){
                        System.out.println("VOUS NE POUVEZ PAS CHOISIR DEUX FOIS LA MEME COULEUR\nCouleur du pion " + (i + 1) + ": ");
                        couleurValue=scanner.nextInt();
                        while(couleurValue<1 || couleurValue>nbrCouleurs){
                            System.out.println("CHOISSISSEZ UN NUMERO DE COULEUR VALIDE ! \n Compris entre 1 et le maximu disponible");
                            while (!scanner.hasNextInt()) {
                                System.out.println("Veuillez entrer un nombre valide.");
                                scanner.next(); // Consommez l'entrée incorrecte
                            }
                            couleurValue=scanner.nextInt();
                            }
                        newPion = new Pion(Couleur.getCouleurByValue(couleurValue));
                    }
                }
                combinaison[i] = newPion;
                bon=false;
            }

        }
    }

    //Constructeur combinaison pour le code secret(la combinaison est aléatoire)
    public Combinaison(int taille,int nbrCouleurs, boolean memeCouleur, boolean random){
        //On s'assure d'utiliser le bon constructeur
        if(!(random)){
            throw new IllegalArgumentException("mauvais constructeur ! Random doit etre vrai\n");
        }
        else{
            Random randomNumbers = new Random();
            this.taille=taille;
            combinaison= new Pion [taille];
            if(memeCouleur==true){
                for (int i=0;i<taille;i++){
                    combinaison[i] = new Pion(Couleur.getCouleurByValue(randomNumbers.nextInt(nbrCouleurs)+1));
                }
            }
            else{
                Pion newPion;
                boolean bon=false;
                for (int i=0;i<taille;i++){
                    newPion=new Pion(Couleur.getCouleurByValue(randomNumbers.nextInt(nbrCouleurs)+1));
                    while(!(bon)){
                        newPion=new Pion(Couleur.getCouleurByValue(randomNumbers.nextInt(nbrCouleurs)+1));
                        bon=true;
                        for(int j=0;j<i;j++){
                            if(newPion.compareTo(combinaison[j])==1){
                                bon=false;
                            }
                        }
                    }
                    combinaison[i] = newPion;
                    bon=false;
                }

            }
        }
    }

    //Constructeur pour les fichiers de sauvegarde
    public Combinaison(String strCombinaison) {
        this.taille = strCombinaison.length();
        this.combinaison = new Pion[taille];
        for (int i = 0; i < taille; i++) {
            int value = Integer.parseInt(Character.toString(strCombinaison.charAt(i)));
            this.combinaison[i] = new Pion(Couleur.getCouleurByValue(value));
        }
    }

   

    //Méthode pour comparer une combinaison avec le code secret
    private Object[] compare(Combinaison otherCombinaison){
        int nbrBienPlace = 0;
        int nbrBonneCouleur = 0;
        ArrayList<Pion> comparateur = new ArrayList<>();

        //On crée un tableau pour stocker la position des pions déja traiter(pour eviter de les compter deux fois)
        ArrayList<Pion> pasVu = new ArrayList<>();
        for (int i = 0; i < this.taille; i++){
            pasVu.add(this.combinaison[i]);
            comparateur.add(otherCombinaison.combinaison[i]);
        }
        
        ArrayList<Pion> pionsBienPlacees = new ArrayList<>();
        ArrayList<Pion> bonneCouleur = new ArrayList<>();
    
        // Trouver les pions bien placés
        for (int i = 0; i < this.taille; i++){
            //On compare si deux pion placés au même endroit on la meme couleur si oui on passe la condition
            if (this.combinaison[i].compareTo(otherCombinaison.combinaison[i])==1){
                nbrBienPlace++;
                pionsBienPlacees.add(this.combinaison[i]);
                comparateur.remove(otherCombinaison.combinaison[i]);
                pasVu.remove(this.combinaison[i]);
            }
        }
    
        // Trouver les pions de la bonne couleur mais mal placés
        for (int i = 0; i < this.taille; i++){
            //On compare si deux pion  on la meme couleur et non pas déja était vu si oui on passe la condition
            if (pasVu.contains(otherCombinaison.combinaison[i]) && comparateur.contains(otherCombinaison.combinaison[i])){
                nbrBonneCouleur++;
                bonneCouleur.add(otherCombinaison.combinaison[i]);
                comparateur.remove(otherCombinaison.combinaison[i]);
                pasVu.remove(otherCombinaison.combinaison[i]); 
            }
        }
         //On stocke les différentes informations voulu, pionsBienPlacees et bonneCouleur ne servent que pour le mode facile
        Object[] result = new Object[4];
        result[0] = pionsBienPlacees;
        result[1] = bonneCouleur;
        result[2] = nbrBienPlace;
        result[3] = nbrBonneCouleur;
        //On renvoie le résultat de la comparaison
        return result;
    }
   
    
    //Méthode permettant d'afficher le résultat de la comparaison pour les modes de difficultés autre que facile
    public boolean afficheCompareDifficile(Combinaison otherCombinaison){
        Object comparaison[]=compare(otherCombinaison);
        System.out.println("Nombre de pions bien placés: " + comparaison[2]);
        System.out.println("Nombre de bonnes couleurs mal placées: " + comparaison[3]);
        if((int)comparaison[2]==taille){
            return true;
        }
        return false;
    }

    //Méthode permettant d'afficher le résultat de la comparaison pour le mode facile
    public boolean afficheComparefacile(Combinaison otherCombinaison){
        Object comparaison[]=compare(otherCombinaison);
        System.out.println("Liste des pions bien placés : "+ comparaison[0]);
        System.out.println("Pions de La bonne couleur : "+comparaison[1]);
        System.out.println("Nombre de pions bien placés: " + comparaison[2]);
        System.out.println("Nombre de bonnes couleurs mal placées: " + comparaison[3]);
        if((int)comparaison[2]==taille){
            return true;
        }
        return false;
    }
    //getteur de a comparaison difficuile
    public String getCompareDifficile(Combinaison otherCombinaison){
        Object comparaison[]=compare(otherCombinaison);
        return " Nombre de pion(s) bien placé(s) : " + comparaison[2] + " Nombre de bonnes couleurs mal placées : " + comparaison[3];
    }
    //getteur de la combinaison
    public Pion[] getCombinaison(){
        return combinaison;
    }

    //On redéfini la méthode toString pour afficher le plateau de combinaison
    @Override
    public String toString(){
        String retour="";
        
        for (int i=0;i<taille;i++){
            retour+="| " + combinaison[i].colorie()+"\u2B24 \u001B[37m|" ;
        }
        return retour;
    }

}
 