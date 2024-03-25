package Projet.source.couleur;

public class Pion{
    private Couleur c;
    private int position;

    public Pion(int position,Couleur c){
        this.c=c;
        this.position=position;
    }

    public Couleur getCouleur(){
        return this.c;
    }
    public int getPosition(){
        return this.position;
    }


}