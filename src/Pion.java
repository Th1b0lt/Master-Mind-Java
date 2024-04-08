package projet.source.projet;

public class Pion{
    private Couleur c;

    public Pion(Couleur c){
        this.c=c;
    }

    public Couleur getCouleur(){
        return this.c;
    }


    @Override
    public String toString(){
        return "Couleur : "+this.c;
    }

}