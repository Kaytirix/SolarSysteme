package Exercices.Astres;

import edu.princeton.cs.algs4.*;

public class Astres {

    //Attribut
    protected String cheminIMG;
    protected String nom;
    protected int distanceDuCentre;
    protected int tailleImgX;
    protected int tailleImgY;

    //Construteur
    public Astres(String cheminIMG, String nom, int distanceDuCentre, int tailleImgX, int tailleImgY) {
        this.cheminIMG = cheminIMG;
        this.nom = nom;
        this.distanceDuCentre = distanceDuCentre;
        this.tailleImgX = tailleImgX;
        this.tailleImgY = tailleImgY;
    }

    //Méthode qui simule l'évolution de l'astre
    public void dessine(){

        //Formule qui calcul les coordonnées de l'Astre
        double x1 = this.distanceDuCentre * Math.cos(Math.toRadians(0 )) ;
        double y1 = this.distanceDuCentre * Math.sin(Math.toRadians(0 )) ;

        //Affiche l'image de l'astre selon les coordonées, le chemin de l'image et sa taille
        StdDraw.picture(x1, y1, this.cheminIMG, this.tailleImgX, this.tailleImgY);

        //Affiche le nom de l'astre
        StdDraw.setPenRadius(0.010);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(x1, y1+50, this.nom);

    }

}
