package Exercices.Astres;

import edu.princeton.cs.algs4.*;

import java.awt.*;

public class Planete extends Astres{

    //Attribut
    private final double periodeRevolution;

    //Construteur
    public Planete(String cheminIMG, String nom, int distanceDuCentre, int tailleImgX,int tailleImgY, int periodeRevolution) {
        super(cheminIMG, nom, distanceDuCentre, tailleImgX, tailleImgY);
        this.periodeRevolution = periodeRevolution;
    }

    //Méthode qui simule l'évolution de la planète
    public void dessine(double nombreJour){

        //Formule qui calcul les coordonnées de la planètre selon la distance du centre (rayon) et l'angle entre deux jour de la planète
        double x1 = this.distanceDuCentre * Math.cos(Math.toRadians(360/ this.periodeRevolution * nombreJour) );
        double y1 = this.distanceDuCentre * Math.sin(Math.toRadians(360 / this.periodeRevolution * nombreJour) );

        //Affiche l'image de l'astre selon les coordonées, le chemin de l'image et sa taille
        StdDraw.picture(x1, y1, this.cheminIMG, this.tailleImgX, this.tailleImgY);

        //Affiche le nom de la planète
        StdDraw.setPenRadius(0.010);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(x1, y1+50, this.nom, 335);

        //Dessine l'orbite de la planète
        StdDraw.setPenRadius(0.00005);
        StdDraw.setPenColor(Color.white);
        StdDraw.circle(0, 0, this.distanceDuCentre);

    }

}
