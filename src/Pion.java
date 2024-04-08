package Projet.source.projet;
<<<<<<< HEAD


=======
>>>>>>> 570c023055558a4d5c81e8766e32042b46b98d9a
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