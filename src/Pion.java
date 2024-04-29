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