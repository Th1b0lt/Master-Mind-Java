package projet.source.projet;

public class Pion{
    private Couleur c;

    public Pion(Couleur c){
        this.c=c;
    }

    public Couleur getCouleur(){
        return this.c;
    }

    public String colorie(){
        return this.c.getCodeCouleur();
    }
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


    @Override
    public String toString(){
        return "Couleur : "+this.c;
    }
    public int compareTo(Pion pion1){
        if(pion1.getCouleur() == this.getCouleur()){
            return 1;
        }
        else{
            return 0;
        }
    }

}