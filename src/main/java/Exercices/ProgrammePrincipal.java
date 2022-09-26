package Exercices;

import Exercices.Astres.Astres;
import Exercices.Astres.Planete;
import Exercices.Horloge.Horloge;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_O;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_X;

public class ProgrammePrincipal {

    public static void main(String[] args) {
        double NbJour = 0;
        int TailleFenetre = 712;
        int TypeHorloge = 3;
        String EchelleTemps = "Jour";

        //Configuration de la zone de dessin

        //Résolultion de la fenêtre
        StdDraw.setCanvasSize(TailleFenetre,TailleFenetre);

        //Echelle de dessin
        StdDraw.setScale(-TailleFenetre,TailleFenetre);

        //Nettoyage de la zone de dessin par précaution
        StdDraw.clear(Color.black);

        //Fin de configuration

        //Initialisation de l'horloge
        Horloge Temps = new Horloge();

        //Création des options
        IhmOption FenetreOption = new IhmOption();
        FenetreOption.Creation();

        //Création du système solaire
        Planete Mercure = new Planete("ImgAstres/Mercure.png", "Mercure", 100, 10, 10, 88);

        Planete Venus = new Planete("ImgAstres/Venus.png", "Vénus", 150, 20, 20, 225);

        Planete Terre = new Planete("ImgAstres/terre.png", "Terre", 200, 30, 30, 365);

        Planete Mars = new Planete("ImgAstres/Mars.png", "Mars", 250, 20, 20, 686);

        Planete Jupiter = new Planete("ImgAstres/Jupiter.png", "Jupiter", 350, 50, 50, 4332);

        Planete Saturne = new Planete("ImgAstres/Saturne.png", "Saturne", 450, 60, 25,10759);

        Planete Uranus = new Planete("ImgAstres/Uranus.png", "Uranus", 550, 30, 30, 30685);

        Planete Neptune = new Planete("ImgAstres/Neptune.png", "Neptune", 650, 30, 30, 60266);

        Astres Soleil = new Astres("ImgAstres/soleil.png", "Soleil", 0, 100, 105);
        //Fin de création

        //Option à activer pour effectuer des animations
        StdDraw.enableDoubleBuffering();

        //Lancement de l'horloge
        Temps.start();

        //Détermination du début du programme en jour afin de synchroniser les caluls selon la période de lancement
        double NbJourDebut = TimeUnit.SECONDS.convert(Temps.getTimeInMillis(), TimeUnit.MILLISECONDS) / 86400.0 ;

        //Lancement de la simulation
        while (true) {

            switch (TypeHorloge) {
                case 1 -> {
                    Temps.RenitiliserEchelleTemps();
                    Temps.SecondeEnMinute(FenetreOption.getNombreMinute(), FenetreOption.getNombreSeconde());
                    EchelleTemps = "Minute";
                }
                case 2 -> {
                    Temps.RenitiliserEchelleTemps();
                    Temps.SecondeEnHeure(FenetreOption.getNombreHeure(), FenetreOption.getNombreSeconde());
                    EchelleTemps = "Heure";
                }
                case 3 -> {
                    Temps.RenitiliserEchelleTemps();
                    Temps.SecondeEnJour(FenetreOption.getNombreJour(), FenetreOption.getNombreSeconde());
                    EchelleTemps = "Jour";
                }
                case 4 -> {
                    Temps.RenitiliserEchelleTemps();
                    Temps.SecondeEnMois(FenetreOption.getNombreMois(), FenetreOption.getNombreSeconde());
                    EchelleTemps = "Mois";
                }
                case 5 -> {
                    Temps.RenitiliserEchelleTemps();
                    Temps.SecondeEnAnnee(FenetreOption.getNombreAnnee(), FenetreOption.getNombreSeconde());
                    EchelleTemps = "Année";
                }
            }

            //Si la flèche du bas est presser, on effectue un dézoom
            if (StdDraw.isKeyPressed(VK_DOWN)) {
                if (TailleFenetre <= 712 && TailleFenetre >= -712) {
                    TailleFenetre += 15;
                    StdDraw.setScale(-TailleFenetre, TailleFenetre);
                }
            }

            //Si la flèche du haut est presser, on effectue un zoom
            if (StdDraw.isKeyPressed(VK_UP)) {
                if (TailleFenetre > 0) {
                    TailleFenetre -= 15;
                    StdDraw.setScale(-TailleFenetre, TailleFenetre);
                }
            }

            //Décélération du temps
            if (StdDraw.isKeyPressed(VK_LEFT)) {
                if (TypeHorloge > 1 && TypeHorloge <= 5) {
                    TypeHorloge -= 1;
                }
            }

            //Accélération du temps
            if (StdDraw.isKeyPressed(VK_RIGHT)) {
                if (TypeHorloge >= 1 && TypeHorloge < 5) {
                    TypeHorloge += 1;
                }
            }

            //Accès à la fenêtre des paramètres
            if (StdDraw.isKeyPressed(VK_O) && StdDraw.hasNextKeyTyped() ) {
                if(StdDraw.nextKeyTyped() != 'O'){

                    //OPresser passe à "true"
                    FenetreOption.setOPresser(true);

                    //Création de la fenêtre des options
                    FenetreOption.Affichage();
                    //Tant que la fenêtre n'est pas fermé, l'horloge et la simulation est en pause
                    while (FenetreOption.isOPresser()) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        StdDraw.pause(1);
                    }
                    //Réinitialisation du nombre de jour au début de la simulation
                    NbJourDebut = TimeUnit.SECONDS.convert(Temps.getTimeInMillis(), TimeUnit.MILLISECONDS) / 86400.0;
                }
            }

            //Quitter l'application
            if(StdDraw.isKeyPressed(VK_X)){
                System.exit(0);
            }

            //Détermination du nombre de jour passé selon l'horloge virtuel
            NbJour = (TimeUnit.SECONDS.convert(Temps.getTimeInMillis(), TimeUnit.MILLISECONDS) / 86400.0) - NbJourDebut;

            //Dessinage sur l'écran en offscreen

            //Affichage du fond
            StdDraw.picture(0, 0, "ImgAstres/fondEtoile.jpg", 2524, 1424);

            Soleil.dessine();
            Mercure.dessine(NbJour);
            Venus.dessine(NbJour);
            Terre.dessine(NbJour);
            Mars.dessine(NbJour);
            Jupiter.dessine(NbJour);
            Saturne.dessine(NbJour);
            Uranus.dessine(NbJour);
            Neptune.dessine(NbJour);

            //Affichage de nombre d'année passée
            StdDraw.text(0, 680, "Années = " + Temps.getYear());

            //Affichage de l'échelle de temps
            StdDraw.text(-530, 680, "Echelle de temps : " + EchelleTemps);

            //Affichage d'un accès à une interface
            StdDraw.text(0, -690, "Appuyez sur o pour afficher les options !");

            //Affichage de la touche pour quitter
            StdDraw.text(530, 680, "Quitter : Appuyez sur x");

            //Affichage de l'écran offscreen, ce qui fait le fait passer en onscreen
            StdDraw.show();
            //Petit délai pour bien effectuer l'affichage
            StdDraw.pause(30);
            //Nettoyage afin de redéssiner mais au jour n+1
            StdDraw.clear(Color.black);
        }
    }
}