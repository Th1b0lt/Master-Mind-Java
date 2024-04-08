package Projet.source.projet;

public enum Couleur{
    ROUGE(1, "\u001b[31m") ,
    BLEU(2, "\u001B[34m"),
    VERT(3, "\u001B[32m"),
    JAUNE(4, "\u001B[33m"),
    VIOLET(5, "\u001B[35m"),
    WHITE(6, "\u001B[37m"),
    CYAN(7, "\u001B[36m"),
    NOIR(8, "\u001B[30m");
    private final int value;
    private final String codeCouleur;


    private Couleur(int value, String codeCouleur) {
        this.value = value;
        this.codeCouleur = codeCouleur;
    }

    // Méthode pour obtenir la valeur numérique associée à la couleur
    public int getValue() {
        return value;
    }

    // Méthode pour obtenir le code ANSI de la couleur
    public String getCodeCouleur() {
        return codeCouleur;
    }

    public static Couleur getCouleurByValue(int value) {
        for (Couleur couleur : Couleur.values()) {
            if (couleur.value == value) {
                return couleur;
            }
        }
        // Si aucune couleur correspondante n'est trouvée, renvoyer null ou gérer l'erreur selon le cas
        return null;
    }
     // Méthode pour afficher la couleur dans la console
     public void afficherTexteEnCouleur(String texte) {
        System.out.println(codeCouleur + texte + "\u001b[0m");
    }
}
