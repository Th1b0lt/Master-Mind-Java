package projet.source.projet;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Combinaison{
    private Pion combinaison [];
    private final int taille;
    
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

    public Combinaison(int taille,int nbrCouleurs, boolean memeCouleur, boolean random){
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
    public Combinaison(String strCombinaison) {
        this.taille = strCombinaison.length();
        this.combinaison = new Pion[taille];
        for (int i = 0; i < taille; i++) {
            int value = Integer.parseInt(Character.toString(strCombinaison.charAt(i)));
            this.combinaison[i] = new Pion(Couleur.getCouleurByValue(value));
        }
    }
    public Combinaison(Pion[] combinaison,int taille ){
        this.taille=taille;
        this.combinaison=combinaison;
    }
    private Object[] compare(Combinaison otherCombinaison){
        int nbrBienPlace = 0;
        int nbrBonneCouleur = 0;
        ArrayList<Integer> dejaVu = new ArrayList<>();
        ArrayList<Pion> pionsBienPlacees = new ArrayList<>();
        ArrayList<Pion> bonneCouleur = new ArrayList<>();
    
        // Trouver les pions bien placés
        for (int i = 0; i < this.taille; i++){
            if (this.combinaison[i].compareTo(otherCombinaison.combinaison[i]) == 1){
                nbrBienPlace++;
                pionsBienPlacees.add(this.combinaison[i]);
                dejaVu.add(i);
            }
        }
    
        // Trouver les pions de la bonne couleur mais mal placés
        for (int i = 0; i < this.taille; i++){
            if (!dejaVu.contains(i)){
                for (int j = 0; j < this.taille; j++){
                    if (i != j && !dejaVu.contains(j) && 
                        this.combinaison[i].getCouleur().equals(otherCombinaison.combinaison[j].getCouleur())){
                        nbrBonneCouleur++;
                        bonneCouleur.add(this.combinaison[i]);
                        dejaVu.add(j);
                        break;
                    }
                }
            }
        }
    
        Object[] result = new Object[4];
        result[0] = pionsBienPlacees;
        result[1] = bonneCouleur;
        result[2] = nbrBienPlace;
        result[3] = nbrBonneCouleur;
    
        return result;
    }
   
    

    public boolean afficheCompareDifficile(Combinaison otherCombinaison){
        Object comparaison[]=compare(otherCombinaison);
        System.out.println("Nombre de pions bien placés: " + comparaison[2]);
        System.out.println("Nombre de bonnes couleurs mal placées: " + comparaison[3]);
        if((int)comparaison[2]==taille){
            return true;
        }
        return false;
    }
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

    public String getCompareDifficile(Combinaison otherCombinaison){
        Object comparaison[]=compare(otherCombinaison);
        return " Nombre de pion(s) bien placé(s) : " + comparaison[2] + " Nombre de bonnes couleurs mal placées : " + comparaison[3];
    }
    public Pion[] getCombinaison(){
        return combinaison;
    }
    @Override
    public String toString(){
        String retour="";
        
        for (int i=0;i<taille;i++){
            retour+="| " + combinaison[i].colorie()+"\u2B24 \u001B[37m|" ;
        }
        return retour;
    }

}
 