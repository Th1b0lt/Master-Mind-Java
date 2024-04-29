package projet.source.projet;

public class Pion{
    private Couleur c;
    
    //Constructeur de Pion
    public Pion(Couleur c){
        this.c=c;
    }

    //Getteur de la couleur du pion
    public Couleur getCouleur(){
        return this.c;
    }

    //Méthode pour colorier du texte
    public String colorie(){
        return this.c.getCodeCouleur();
    }
    //Méthode pour comparer deux pion
    public int compareTo(Pion pion1){
        if(pion1.getCouleur() == this.getCouleur()){
            return 1;
        }
        else{
            return 0;
        }
    }
    //Redéfinition du equals pour les pions (pour permettre l'utilisation de contains)
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Pion)) {
            return false;
        }
        Pion other = (Pion) obj;
        // Comparez ici les attributs pertinents pour déterminer l'égalité
        return this.c.equals(other.c);
    }

    //Redéfinition de toString pour afficher un pion
    @Override
    public String toString(){
        return "Couleur : "+this.c;
    }
    

}