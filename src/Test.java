package projet.source.projet;


public class Test{
    public static void main(String[] args){
        Couleur r= Couleur.getCouleurByValue(1) ;
        //System.out.println(String.format("%stest\u001B[37m\n",r.getCodeCouleur()));
        Plateau p= new Plateau();
        p.afficheCouleur();
    }
}